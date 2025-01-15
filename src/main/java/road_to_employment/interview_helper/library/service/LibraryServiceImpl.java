package road_to_employment.interview_helper.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import road_to_employment.interview_helper.library.entity.Library;
import road_to_employment.interview_helper.library.repository.LibraryRepository;
import road_to_employment.interview_helper.user.entity.User;
import road_to_employment.interview_helper.user.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@EnableJpaRepositories("road_to_employment.interview_helper.library")
public class LibraryServiceImpl implements LibraryService {
    private final LibraryRepository libraryRepository;
    private final UserRepository userRepository;

    @Override
    public Library createLibrary(User user) {
        Library library = new Library(user);
        return this.libraryRepository.save(library);
    }

    @Override
    public Library findLibraryByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 Id로 사용자를 찾을 수 없습니다!"));

        return libraryRepository.findByUserId(user)
                .orElseThrow(() -> new IllegalArgumentException("해당 Id로 라이브러리를 찾을 수 없습니다!"));
    }
}
