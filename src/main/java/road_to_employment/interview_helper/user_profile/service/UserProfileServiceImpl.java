package road_to_employment.interview_helper.user_profile.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import road_to_employment.interview_helper.amazon_s3.service.AmazonS3Service;
import road_to_employment.interview_helper.user.entity.User;
import road_to_employment.interview_helper.user_profile.entity.UserProfile;
import road_to_employment.interview_helper.user_profile.repository.UserProfileRepository;
import road_to_employment.interview_helper.user_profile.service.response.UserinfoResponse;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableJpaRepositories("road_to_employment.interview_helper.user_profile")
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final AmazonS3Service amazonS3Service;

    @Value("${aws.bucket-name}")
    private String BUCKET_NAME;

    @Value("${aws.aws-region}")
    private String AWS_REGION;

    @Override
    public UserProfile createUserProfile(String email, String picture, String nickname, User user) {
        UserProfile userProfile = new UserProfile(email, picture, nickname, user);
        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserinfoResponse findByUser(User user) {
        Optional<UserProfile> maybeUserProfile = userProfileRepository.findByUser(user);

        UserProfile userProfile = maybeUserProfile
                .orElseThrow(() -> new RuntimeException("해당 사용자 정보를 찾을 수 없습니다!"));

        return UserinfoResponse.from(userProfile);
    }

    @Override
    public boolean isNicknameDuplicated(String nickname) {
        Optional<UserProfile> maybeUserProfile = userProfileRepository.findByNickname(nickname);

        return maybeUserProfile.isPresent();
    }

    @Transactional
    @Override
    public String changeUserPicture(User user, String imageUrl) {
        Optional<UserProfile> maybeUserProfile = userProfileRepository.findByUser(user);

        if (maybeUserProfile.isEmpty()) {
            throw new RuntimeException("해당 사용자 정보를 찾을 수 없습니다!");
        }

        UserProfile userProfile = maybeUserProfile.get();
        String[] fileList = userProfile.getPicture().split("/");
        String fileName = fileList[3];
        String decodedFileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8);

        if (imageUrl != null && !imageUrl.isEmpty()) {
            String encodedFileName = URLEncoder.encode(imageUrl, StandardCharsets.UTF_8);
            String encodedImageUrl = String.format("https://%s.s3.%s.amazonaws.com/%s", BUCKET_NAME, AWS_REGION, encodedFileName);
            userProfile.setPicture(encodedImageUrl);
            amazonS3Service.deleteFile(decodedFileName);
            userProfileRepository.save(userProfile);
            return encodedImageUrl;
        }

        throw new RuntimeException("이미지 URL이 잘못되었습니다!");
    }

    @Override
    public boolean changeUserNickname(User user, String nickname) {
        Optional<UserProfile> maybeUserProfile = userProfileRepository.findByUser(user);

        if (maybeUserProfile.isEmpty()) {
            return false;
        }

        UserProfile userProfile = maybeUserProfile.get();
        userProfile.setNickname(nickname);
        userProfileRepository.save(userProfile);

        return true;
    }
}
