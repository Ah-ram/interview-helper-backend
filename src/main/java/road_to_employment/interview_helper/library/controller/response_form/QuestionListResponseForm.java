package road_to_employment.interview_helper.library.controller.response_form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import road_to_employment.interview_helper.library.service.response.QuestionListResponse;

@Getter
@RequiredArgsConstructor
public class QuestionListResponseForm {
    private final Long id;
    private final String title;

    public static QuestionListResponseForm from(QuestionListResponse questionListResponse) {
        return new QuestionListResponseForm(questionListResponse.getId(), questionListResponse.getTitle());
    }
}
