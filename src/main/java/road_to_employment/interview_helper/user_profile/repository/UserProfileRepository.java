package road_to_employment.interview_helper.user_profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import road_to_employment.interview_helper.user.entity.User;
import road_to_employment.interview_helper.user_profile.entity.UserProfile;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUser(User user);
    Optional<UserProfile> findByNickname(String nickname);
}
