package road_to_employment.interview_helper.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import road_to_employment.interview_helper.directory.entity.Directory;
import road_to_employment.interview_helper.question.entity.Category;
import road_to_employment.interview_helper.question.entity.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
