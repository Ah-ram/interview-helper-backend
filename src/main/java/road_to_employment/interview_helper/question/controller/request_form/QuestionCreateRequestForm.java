package road_to_employment.interview_helper.question.controller.request_form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import road_to_employment.interview_helper.question.service.request.QuestionCreateRequest;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class QuestionCreateRequestForm {
    private final String userToken;
    private final List<String> titleList;
    private final int categoryIndex;

    public QuestionCreateRequest toQuestionCreateRequest() {
        return new QuestionCreateRequest(
                this.titleList,
                this.categoryIndex);
    }
}
