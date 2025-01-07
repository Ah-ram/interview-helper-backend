package road_to_employment.interview_helper.user_profile.service.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import road_to_employment.interview_helper.user_profile.entity.UserProfile;

@Getter
@RequiredArgsConstructor
public class UserinfoResponse {
    private final String name;
    private final String email;
    private final String picture;

    public static UserinfoResponse from(UserProfile userProfile) {
        return new UserinfoResponse(userProfile.getName(), userProfile.getEmail(), userProfile.getPicture());
    }
}
