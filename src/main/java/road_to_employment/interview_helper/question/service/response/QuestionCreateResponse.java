package road_to_employment.interview_helper.question.service.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import road_to_employment.interview_helper.question.entity.Question;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class QuestionCreateResponse {
    private final Long questionId;
    private final String title;
    private final String category;

    public static QuestionCreateResponse from(Question question) {
        return new QuestionCreateResponse(
                question.getId(),
                question.getTitle(),
                question.getCategory().getValue());
    }
}
