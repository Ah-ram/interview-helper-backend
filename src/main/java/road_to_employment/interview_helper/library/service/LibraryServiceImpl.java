package road_to_employment.interview_helper.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import road_to_employment.interview_helper.library.entity.Library;
import road_to_employment.interview_helper.library.repository.LibraryRepository;
import road_to_employment.interview_helper.user.entity.User;

@Service
@RequiredArgsConstructor
@EnableJpaRepositories("road_to_employment.interview_helper.library")
public class LibraryServiceImpl implements LibraryService {
    private final LibraryRepository libraryRepository;

    @Override
    public Library createLibrary(User user) {
        Library library = new Library(user);
        return this.libraryRepository.save(library);
    }
}
