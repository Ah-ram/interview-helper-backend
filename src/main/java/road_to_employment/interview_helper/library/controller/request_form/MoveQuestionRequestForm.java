package road_to_employment.interview_helper.library.controller.request_form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import road_to_employment.interview_helper.library.service.request.MoveQuestionRequest;

@Getter
@RequiredArgsConstructor
public class MoveQuestionRequestForm {
    private final String userToken;
    private final Long questionId;

    public MoveQuestionRequest toMoveQuestionRequest() {
        return new MoveQuestionRequest(this.questionId);
    }
}
