package road_to_employment.interview_helper.user_profile.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import road_to_employment.interview_helper.oauth.service.RedisService;
import road_to_employment.interview_helper.user.entity.User;
import road_to_employment.interview_helper.user.service.UserService;
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
}
