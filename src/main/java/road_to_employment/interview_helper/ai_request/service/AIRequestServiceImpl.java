package road_to_employment.interview_helper.ai_request.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import road_to_employment.interview_helper.ai_request.dto.AIRequestDto;
import road_to_employment.interview_helper.ai_request.service.request.AIRequest;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AIRequestServiceImpl implements AIRequestService {
    @Value("${ai-request.uri}")
    private String aiRequestUri;

    @Override
    public boolean aiRequestToFastAPI(Long userId, AIRequest aiRequest) {
        String userToken = aiRequest.getUserToken();
        int command = aiRequest.getCommand();
        Optional<List<Object>> data = aiRequest.getData();

        WebClient webClient = WebClient.builder().build();
        List<Object> requestData = new java.util.ArrayList<>(List.of());
        requestData.add(userToken);
        requestData.add(userId);
        data.ifPresent(requestData::addAll);

        AIRequestDto aiRequestDto = new AIRequestDto(command, requestData);

        boolean response = webClient.post()
                .uri(aiRequestUri)
                .bodyValue(aiRequestDto)
                .retrieve()
                .bodyToMono(boolean.class)
                .flux()
                .toStream()
                .findFirst()
                .orElse(false);

        return response;
    }
}

