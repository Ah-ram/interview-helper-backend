package road_to_employment.interview_helper.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import road_to_employment.interview_helper.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    Optional<User> findByProviderId(String providerId);
}
