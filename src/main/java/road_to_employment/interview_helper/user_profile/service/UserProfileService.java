package road_to_employment.interview_helper.user_profile.service;

import road_to_employment.interview_helper.user.entity.User;
import road_to_employment.interview_helper.user_profile.entity.UserProfile;
import road_to_employment.interview_helper.user_profile.service.response.UserinfoResponse;

public interface UserProfileService {
    UserProfile createUserProfile(String email, String picture, String nickname, User user);
    UserinfoResponse findByUser(User user);
    boolean isNicknameDuplicated(String nickname);
    boolean changeUserPicture(User user, String imageUrl);
    boolean changeUserNickname(User user, String nickname);
}
