package road_to_employment.interview_helper.question.controller.response_form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import road_to_employment.interview_helper.question.service.response.QuestionCreateResponse;

@Getter
@RequiredArgsConstructor
public class QuestionCreateResponseForm {
    private final String title;
    private final String category;

    public static QuestionCreateResponseForm from(QuestionCreateResponse questionCreateResponse) {
        return new QuestionCreateResponseForm(
                questionCreateResponse.getTitle(),
                questionCreateResponse.getCategory());
    }
}
