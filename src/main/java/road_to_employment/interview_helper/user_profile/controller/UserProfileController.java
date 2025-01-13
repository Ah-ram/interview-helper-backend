package road_to_employment.interview_helper.user_profile.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import road_to_employment.interview_helper.oauth.service.RedisService;
import road_to_employment.interview_helper.user.entity.User;
import road_to_employment.interview_helper.user.service.UserService;
import road_to_employment.interview_helper.user_profile.controller.request_form.ChangeUserNicknameRequestForm;
import road_to_employment.interview_helper.user_profile.controller.request_form.ChangeUserPictureRequestForm;
import road_to_employment.interview_helper.user_profile.controller.request_form.CheckNicknameDuplicateRequestForm;
import road_to_employment.interview_helper.user_profile.controller.request_form.UserinfoRequestForm;
import road_to_employment.interview_helper.user_profile.controller.response_form.UserinfoResponseForm;
import road_to_employment.interview_helper.user_profile.service.UserProfileService;
import road_to_employment.interview_helper.user_profile.service.response.UserinfoResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user-profile")
public class UserProfileController {
    private final UserProfileService userProfileService;
    private final RedisService redisService;
    private final UserService userService;

    @PostMapping("/userinfo")
    public UserinfoResponseForm getUserinfo(@RequestBody UserinfoRequestForm userinfoRequestForm) {
        String userToken = userinfoRequestForm.getUserToken();
        String value = redisService.getValueByKey(userToken);
        Long userId = Long.valueOf(value);
        User user = userService.findById(userId);
        UserinfoResponse response = userProfileService.findByUser(user);
        log.info("controller -> getUserInfo() picture: {}", response.getPicture());

        return UserinfoResponseForm.from(response);
    }

    @PostMapping("/check-nickname-duplicate")
    public boolean checkNicknameDuplicate(@RequestBody CheckNicknameDuplicateRequestForm checkNicknameDuplicateRequestForm) {
        String userToken = checkNicknameDuplicateRequestForm.getUserToken();
        String value = redisService.getValueByKey(userToken);
        String nickname = checkNicknameDuplicateRequestForm.getNickname();

        boolean response = userProfileService.isNicknameDuplicated(nickname);

        return response;
    }

    @PutMapping("/change-picture")
    public String changeUserPicture(@RequestBody ChangeUserPictureRequestForm changeUserPictureRequestForm) {
        String userToken = changeUserPictureRequestForm.getUserToken();
        String imageUrl = changeUserPictureRequestForm.getImageUrl();
        log.info("controller -> imageUrl: {}", imageUrl);
        String value = redisService.getValueByKey(userToken);
        Long userId = Long.valueOf(value);
        User user = userService.findById(userId);

        String response = userProfileService.changeUserPicture(user, imageUrl);

        return response;
    }

    @PutMapping("/change-nickname")
    public boolean changeUserNickname(@RequestBody ChangeUserNicknameRequestForm changeUserNicknameRequestForm) {
        String userToken = changeUserNicknameRequestForm.getUserToken();
        String nickname = changeUserNicknameRequestForm.getNickname();
        String value = redisService.getValueByKey(userToken);
        Long userId = Long.valueOf(value);
        User user = userService.findById(userId);

        boolean response = userProfileService.changeUserNickname(user, nickname);

        return response;
    }
}
