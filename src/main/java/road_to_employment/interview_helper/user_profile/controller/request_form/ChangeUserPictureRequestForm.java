package road_to_employment.interview_helper.user_profile.controller.request_form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChangeUserPictureRequestForm {
    private final String userToken;
    private final String imageUrl;
}
