package road_to_employment.interview_helper.directory.service;


import road_to_employment.interview_helper.directory.entity.Directory;

import java.util.List;

public interface DirectoryService {
    boolean checkDirectoryDuplicate(String name, Library library);
    Directory createDirectory(String name, Library library);
    List<Directory> listDirectory(Library library);
}
