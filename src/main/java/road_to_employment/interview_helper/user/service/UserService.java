package road_to_employment.interview_helper.user.service;

import road_to_employment.interview_helper.user.entity.User;

public interface UserService {
    User findById(Long id);
    User findByProviderId(String providerId);
    User create(String provider, String providerId, String registerId);
}
