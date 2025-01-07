package road_to_employment.interview_helper.user_profile.controller.response_form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import road_to_employment.interview_helper.user_profile.service.response.UserinfoResponse;

@Getter
@RequiredArgsConstructor
public class UserinfoResponseForm {
    private final String name;
    private final String email;
    private final String picture;

    public static UserinfoResponseForm from(UserinfoResponse response) {
        return new UserinfoResponseForm(
                response.getName(),
                response.getEmail(),
                response.getPicture());
    }
}
