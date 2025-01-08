package road_to_employment.interview_helper.user_profile.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import road_to_employment.interview_helper.user.entity.User;
import road_to_employment.interview_helper.user_profile.entity.UserProfile;
import road_to_employment.interview_helper.user_profile.repository.UserProfileRepository;
import road_to_employment.interview_helper.user_profile.service.response.UserinfoResponse;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableJpaRepositories("road_to_employment.interview_helper.user_profile")
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;

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
}
