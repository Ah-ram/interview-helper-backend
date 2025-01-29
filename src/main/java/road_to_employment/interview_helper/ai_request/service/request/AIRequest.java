package road_to_employment.interview_helper.ai_request.service.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class AIRequest {
    private final String userToken;
    private final int command;
    private final Optional<List<Object>> data;
}
