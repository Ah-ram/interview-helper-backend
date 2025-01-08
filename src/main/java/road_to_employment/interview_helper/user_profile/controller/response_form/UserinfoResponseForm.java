package road_to_employment.interview_helper.user_profile.controller.response_form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import road_to_employment.interview_helper.user_profile.service.response.UserinfoResponse;

@Getter
@RequiredArgsConstructor
public class UserinfoResponseForm {
    private final String email;
    private final String picture;
    private final String nickname

    public static UserinfoResponseForm from(UserinfoResponse response) {
        return new UserinfoResponseForm(
                response.getEmail(),
                response.getPicture(),
                response.getNickname());
    }
}
