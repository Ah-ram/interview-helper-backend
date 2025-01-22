package road_to_employment.interview_helper.library.service.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class QuestionListRequest {
    private final String directoryName;
    private final int categoryIndex;
}
