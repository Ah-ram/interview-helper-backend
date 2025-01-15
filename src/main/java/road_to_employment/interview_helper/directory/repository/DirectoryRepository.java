package road_to_employment.interview_helper.directory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import road_to_employment.interview_helper.directory.entity.Directory;
import road_to_employment.interview_helper.library.entity.Library;

import java.util.List;


public interface DirectoryRepository extends JpaRepository<Directory, Long> {
    boolean existsByLibraryAndName(Library library, String name);
    List<Directory> findByLibraryIn(Library library);
}
