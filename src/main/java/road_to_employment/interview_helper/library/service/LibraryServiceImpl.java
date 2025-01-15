package road_to_employment.interview_helper.library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import road_to_employment.interview_helper.directory.entity.Directory;
import road_to_employment.interview_helper.directory.repository.DirectoryRepository;
import road_to_employment.interview_helper.library.entity.Library;
import road_to_employment.interview_helper.library.repository.LibraryRepository;
import road_to_employment.interview_helper.library.service.response.DirectoryResponse;
import road_to_employment.interview_helper.user.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableJpaRepositories("road_to_employment.interview_helper.library")
public class LibraryServiceImpl implements LibraryService {
    private final LibraryRepository libraryRepository;
    private final DirectoryRepository directoryRepository;

    @Override
    public Library createLibrary(User user) {
        Library library = new Library(user);
        Library savedLibrary = libraryRepository.save(library);
        Directory directory = Directory.builder()
                .name("default")
                .library(library)
                .build();
        directoryRepository.save(directory);

        return savedLibrary;
    }

    @Override
    public boolean checkDirectoryNameDuplicate(String name, Library library) {
        return !directoryRepository.existsByLibraryAndName(library, name);
    }

    @Override
    public DirectoryResponse createDirectory(String name, Library library) {
        Directory directory = Directory.builder()
                .name(name)
                .library(library)
                .build();
        Directory savedDirectory = directoryRepository.save(directory);

        return DirectoryResponse.from(savedDirectory);
    }

    @Override
    public List<DirectoryResponse> listDirectory(Library library) {
        List<Directory> directoryList = directoryRepository.findByLibrary(library);

        return directoryList.stream().map(DirectoryResponse::from).collect(Collectors.toList());
    }
}
