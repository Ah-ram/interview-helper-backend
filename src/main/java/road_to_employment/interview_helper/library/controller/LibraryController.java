package road_to_employment.interview_helper.library.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import road_to_employment.interview_helper.common.request_form.UserTokenRequestForm;
import road_to_employment.interview_helper.library.controller.request_form.DirectoryCreateRequestForm;
import road_to_employment.interview_helper.library.controller.response_form.DirectoryResponseForm;
import road_to_employment.interview_helper.library.entity.Library;
import road_to_employment.interview_helper.library.service.LibraryService;
import road_to_employment.interview_helper.library.service.response.DirectoryResponse;
import road_to_employment.interview_helper.oauth.service.RedisService;
import road_to_employment.interview_helper.user.entity.User;
import road_to_employment.interview_helper.user.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/library")
public class LibraryController {
    private final LibraryService libraryService;
    private final UserService userService;
    private final RedisService redisService;

    @PostMapping("/check-directory-name-duplicate")
    public String checkDirectoryNameDuplicate(@RequestBody DirectoryCreateRequestForm directoryCreateRequestForm) {
        String userToken = directoryCreateRequestForm.getUserToken();
        String directoryName = directoryCreateRequestForm.getName();
        String value = redisService.getValueByKey(userToken);
        Long userId = Long.valueOf(value);
        User user = userService.findById(userId);
        Library library = libraryService.findLibraryByUserId(userId);

        String response = libraryService.checkDirectoryNameDuplicate(directoryName, library);

        return response;
    }

    @PostMapping("/create-directory")
    public DirectoryResponseForm createDirectory(@RequestBody DirectoryCreateRequestForm directoryCreateRequestForm) {
        String userToken = directoryCreateRequestForm.getUserToken();
        String directoryName = directoryCreateRequestForm.getName();
        String value = redisService.getValueByKey(userToken);
        Long userId = Long.valueOf(value);
        User user = userService.findById(userId);
        Library library = libraryService.findLibraryByUserId(userId);

        DirectoryResponse response = libraryService.createDirectory(directoryName, library);

        return DirectoryResponseForm.from(response);
    }

    @PostMapping("/list-directory")
    public List<DirectoryResponseForm> listDirectory(@RequestBody UserTokenRequestForm userTokenRequestForm) {
        String userToken = userTokenRequestForm.getUserToken();
        String value = redisService.getValueByKey(userToken);
        Long userId = Long.valueOf(value);
        User user = userService.findById(userId);
        Library library = libraryService.findLibraryByUserId(userId);

        List<DirectoryResponse> response = libraryService.listDirectory(library);

        return response.stream().map(DirectoryResponseForm::from).collect(Collectors.toList());
    }
}
