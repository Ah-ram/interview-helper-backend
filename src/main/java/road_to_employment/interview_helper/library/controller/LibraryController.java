package road_to_employment.interview_helper.library.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import road_to_employment.interview_helper.common.request_form.UserTokenRequestForm;
import road_to_employment.interview_helper.library.controller.request_form.DirectoryRequestForm;
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
    public String checkDirectoryNameDuplicate(@RequestBody DirectoryRequestForm directoryRequestForm) {
        String userToken = directoryRequestForm.getUserToken();
        String directoryName = directoryRequestForm.getName();
        String value = redisService.getValueByKey(userToken);
        Long userId = Long.valueOf(value);
        User user = userService.findById(userId);
        Library library = libraryService.findLibraryByUserId(userId);

        String response = libraryService.checkDirectoryNameDuplicate(directoryName, library);

        return response;
    }

    @PostMapping("/create-directory")
    public DirectoryResponseForm createDirectory(@RequestBody DirectoryRequestForm directoryRequestForm) {
        String userToken = directoryRequestForm.getUserToken();
        String directoryName = directoryRequestForm.getName();
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

    @PutMapping("/change-directory-name/{directoryId}")
    public String changeDirectoryName(@PathVariable("directoryId") Long directoryId, @RequestBody DirectoryRequestForm directoryRequestForm) {
        String userToken = directoryRequestForm.getUserToken();
        String directoryName = directoryRequestForm.getName();
        String value = redisService.getValueByKey(userToken);
        Long userId = Long.valueOf(value);
        User user = userService.findById(userId);
        Library library = libraryService.findLibraryByUserId(userId);
        String response = libraryService.changeDirectoryName(directoryId, directoryName, library);

        return response;
    }

    @DeleteMapping("/delete-directory/{directoryId}")
    public Boolean deleteDirectory(@PathVariable("directoryId") Long directoryId, @RequestBody UserTokenRequestForm userTokenRequestForm) {
        String userToken = userTokenRequestForm.getUserToken();
        String value = redisService.getValueByKey(userToken);
        Long userId = Long.valueOf(value);
        User user = userService.findById(userId);
        Library library = libraryService.findLibraryByUserId(userId);

        Boolean isDeleted = libraryService.deleteDirectory(directoryId, library);

        if (!isDeleted) {
            throw new RuntimeException("디렉토리가 존재하지 않거나 이미 삭제되었습니다.");
        }

        return true;
    }
}
