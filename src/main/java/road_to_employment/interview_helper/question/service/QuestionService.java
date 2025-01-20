package road_to_employment.interview_helper.question.service;

import road_to_employment.interview_helper.library.entity.Library;
import road_to_employment.interview_helper.question.service.request.QuestionCreateRequest;
import road_to_employment.interview_helper.question.service.response.QuestionCreateResponse;

import java.util.List;

public interface QuestionService {
    List<QuestionCreateResponse> createQuestion(Library library, QuestionCreateRequest questionCreateRequest);
}
