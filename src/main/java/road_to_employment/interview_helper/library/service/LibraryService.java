package road_to_employment.interview_helper.library.service;

import road_to_employment.interview_helper.library.entity.Library;
import road_to_employment.interview_helper.library.service.response.DirectoryResponse;
import road_to_employment.interview_helper.user.entity.User;

import java.util.List;

public interface LibraryService {
    Library createLibrary(User user);
    Library findLibraryByUserId(Long userId);
    String checkDirectoryNameDuplicate(String name, Library library);
    DirectoryResponse createDirectory(String name, Library library);
    List<DirectoryResponse> listDirectory(Library library);
}
