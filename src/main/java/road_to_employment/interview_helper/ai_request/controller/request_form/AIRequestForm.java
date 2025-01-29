package road_to_employment.interview_helper.ai_request.controller.request_form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Any;
import road_to_employment.interview_helper.ai_request.service.request.AIRequest;

import java.util.List;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class AIRequestForm {
    private final String userToken;
    private final int command;
    private final Optional<List<Object>> data;

    public AIRequest toAIRequest() {
        return new AIRequest(this.userToken, this.command, this.data);
    }
}
