package road_to_employment.interview_helper.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import road_to_employment.interview_helper.library.entity.Library;
import road_to_employment.interview_helper.user.entity.User;

import java.util.Optional;

public interface LibraryRepository extends JpaRepository<Library, Long> {
    Optional<Library> findByUserId(User user);
}
