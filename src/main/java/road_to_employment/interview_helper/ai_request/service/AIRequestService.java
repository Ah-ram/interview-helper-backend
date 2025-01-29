package road_to_employment.interview_helper.ai_request.service;

import road_to_employment.interview_helper.ai_request.service.request.AIRequest;

public interface AIRequestService {
    boolean aiRequestToFastAPI(Long userId, AIRequest aiRequest);
}
