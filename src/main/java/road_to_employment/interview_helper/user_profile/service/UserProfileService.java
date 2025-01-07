package road_to_employment.interview_helper.user_profile.service;

import road_to_employment.interview_helper.user.entity.User;
import road_to_employment.interview_helper.user_profile.entity.UserProfile;
import road_to_employment.interview_helper.user_profile.service.response.UserinfoResponse;

public interface UserProfileService {
    UserProfile createUserProfile(String name, String email, String picture, User user);
    UserinfoResponse findByUser(User user);
}
