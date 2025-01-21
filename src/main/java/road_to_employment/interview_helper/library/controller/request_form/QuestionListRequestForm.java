package road_to_employment.interview_helper.library.controller.request_form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import road_to_employment.interview_helper.library.service.request.QuestionListRequest;

@Getter
@RequiredArgsConstructor
public class QuestionListRequestForm {
    private final String userToken;
    private final String directoryName;
    private final int categoryIndex;

    public QuestionListRequest toQuestionListRequest() {
        return new QuestionListRequest(this.directoryName, this.categoryIndex);
    }
}
