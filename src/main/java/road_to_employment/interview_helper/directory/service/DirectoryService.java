package road_to_employment.interview_helper.directory.service;


import road_to_employment.interview_helper.directory.entity.Directory;


public interface DirectoryService {
    boolean checkDirectoryDuplicate(String name, Library library);
    Directory createDirectory(String name, Library library);
}
