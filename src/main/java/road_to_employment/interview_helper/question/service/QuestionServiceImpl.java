package road_to_employment.interview_helper.question.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import road_to_employment.interview_helper.directory.entity.Directory;
import road_to_employment.interview_helper.directory.repository.DirectoryRepository;
import road_to_employment.interview_helper.library.entity.Library;
import road_to_employment.interview_helper.question.entity.Category;
import road_to_employment.interview_helper.question.entity.Question;
import road_to_employment.interview_helper.question.repository.QuestionRepository;
import road_to_employment.interview_helper.question.service.request.QuestionCreateRequest;
import road_to_employment.interview_helper.question.service.response.QuestionCreateResponse;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final DirectoryRepository directoryRepository;

    @Override
    public List<QuestionCreateResponse> createQuestion(Library library, QuestionCreateRequest questionCreateRequest) {
        List<String> titleList = questionCreateRequest.getTitleList();
        int categoryIndex = questionCreateRequest.getCategoryIndex();
        Category category = Category.values()[categoryIndex];
        Directory directory = directoryRepository.findFirstByLibraryOrderById(library);
        List<Question> questionList = titleList.stream().map(
                title -> new Question.builder()
                        .title(title)
                        .category(category)
                        .directory(directory)
                        .build()
        ).collect(Collectors.toList());
        List<Question> savedQuestionList = questionRepository.saveAll(questionList);

        return savedQuestionList.stream().map(QuestionCreateResponse::from).collect(Collectors.toList());
    }
}
