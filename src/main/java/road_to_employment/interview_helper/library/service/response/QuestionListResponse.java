package road_to_employment.interview_helper.library.service.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import road_to_employment.interview_helper.question.entity.Question;

@Getter
@RequiredArgsConstructor
public class QuestionListResponse {
    private final Long id;
    private final String title;

    public static QuestionListResponse from(Question question) {
        return new QuestionListResponse(question.getId(), question.getTitle());
    }
}
