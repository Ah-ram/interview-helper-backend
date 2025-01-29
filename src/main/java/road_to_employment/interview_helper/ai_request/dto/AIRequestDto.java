package road_to_employment.interview_helper.ai_request.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class AIRequestDto {
    private final int command;
    private final List<Object> data;
}
