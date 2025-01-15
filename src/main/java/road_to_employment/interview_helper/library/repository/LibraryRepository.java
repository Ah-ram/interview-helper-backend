package road_to_employment.interview_helper.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import road_to_employment.interview_helper.library.entity.Library;

public interface LibraryRepository extends JpaRepository<Library, Long> {
}
