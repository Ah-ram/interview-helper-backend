package road_to_employment.interview_helper.question.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import road_to_employment.interview_helper.library.entity.Library;
import road_to_employment.interview_helper.library.service.LibraryService;
import road_to_employment.interview_helper.oauth.service.RedisService;
import road_to_employment.interview_helper.question.controller.request_form.QuestionCreateRequestForm;
import road_to_employment.interview_helper.question.controller.response_form.QuestionCreateResponseForm;
import road_to_employment.interview_helper.question.service.QuestionService;
import road_to_employment.interview_helper.question.service.response.QuestionCreateResponse;
import road_to_employment.interview_helper.user.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;
    private final RedisService redisService;
    private final UserService userService;
    private final LibraryService libraryService;

    @PostMapping("/create")
    public List<QuestionCreateResponseForm> createQuestion(@RequestBody QuestionCreateRequestForm questionCreateRequestForm) {
        String userToken = questionCreateRequestForm.getUserToken();
        String value = redisService.getValueByKey(userToken);
        Long userId = Long.valueOf(value);
        Library library = libraryService.findLibraryByUserId(userId);

        List<QuestionCreateResponse> response = questionService.createQuestion(
                library, questionCreateRequestForm.toQuestionCreateRequest());

        return response.stream().map(QuestionCreateResponseForm::from).collect(Collectors.toList());
    }
}
