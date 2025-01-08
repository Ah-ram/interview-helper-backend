package road_to_employment.interview_helper.user_profile.service.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import road_to_employment.interview_helper.user_profile.entity.UserProfile;

@Getter
@RequiredArgsConstructor
public class UserinfoResponse {
    private final String email;
    private final String picture;
    private final String nickname;

    public static UserinfoResponse from(UserProfile userProfile) {
        return new UserinfoResponse(userProfile.getEmail(), userProfile.getPicture(), userProfile.getNickname());
    }
}
