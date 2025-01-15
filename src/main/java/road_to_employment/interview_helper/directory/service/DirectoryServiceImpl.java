package road_to_employment.interview_helper.directory.service;

import road_to_employment.interview_helper.directory.repository.DirectoryRepository;



public class DirectoryServiceImpl implements DirectoryService {
    private final DirectoryRepository directoryRepository;

    @Override
    public boolean checkDirectoryDuplicate(String name, Library library) {
        return !directoryRepository.existsByLibraryAndName(library, name);
    }
}
