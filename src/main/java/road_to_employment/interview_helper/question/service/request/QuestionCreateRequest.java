package road_to_employment.interview_helper.question.service.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import road_to_employment.interview_helper.directory.entity.Directory;
import road_to_employment.interview_helper.question.entity.Category;
import road_to_employment.interview_helper.question.entity.Question;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class QuestionCreateRequest {
    private final List<String> titleList;
    private final int categoryIndex;
}
