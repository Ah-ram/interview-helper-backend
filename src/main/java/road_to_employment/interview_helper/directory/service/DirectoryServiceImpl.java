package road_to_employment.interview_helper.directory.service;

import road_to_employment.interview_helper.directory.entity.Directory;
import road_to_employment.interview_helper.directory.repository.DirectoryRepository;



public class DirectoryServiceImpl implements DirectoryService {
    private final DirectoryRepository directoryRepository;

    @Override
    public boolean checkDirectoryDuplicate(String name, Library library) {
        return !directoryRepository.existsByLibraryAndName(library, name);
    }

    @Override
    public Directory createDirectory(String name, Library library) {
        Directory directory = Directory.builder()
                .name(name)
                .library(library)
                .build();

        Directory savedDirectory = directoryRepository.save(directory);
        return savedDirectory;
    }
}
