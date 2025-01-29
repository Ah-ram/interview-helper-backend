package road_to_employment.interview_helper.ai_request.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import road_to_employment.interview_helper.ai_request.controller.request_form.AIRequestForm;
import road_to_employment.interview_helper.ai_request.service.AIRequestService;
import road_to_employment.interview_helper.oauth.service.RedisService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ai-request")
public class AIRequestController {
    private final RedisService redisService;
    private final AIRequestService aiRequestService;

    @PostMapping("/send")
    public boolean aiRequestToFastAPI(@RequestBody AIRequestForm aiRequestForm) {
        String userToken = aiRequestForm.getUserToken();
        Long userId = null;

        try {
            String value = redisService.getValueByKey(userToken);
            userId = Long.valueOf(value);
        } catch (IllegalArgumentException e) {
            log.info("service -> aiRequestToFastAPI() 중 오류 발생: {}", e);
            return false;
        }

        boolean response = aiRequestService.aiRequestToFastAPI(userId, aiRequestForm.toAIRequest());

        return response;
    }
}
