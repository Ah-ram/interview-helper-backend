package road_to_employment.interview_helper.library.service;

import road_to_employment.interview_helper.library.entity.Library;
import road_to_employment.interview_helper.user.entity.User;

public interface LibraryService {
    Library createLibrary(User user);
}
