package com.Code_Review_System.Service.ServiceImpl;

import com.Code_Review_System.Service.RequestService;
import com.Code_Review_System.iO.AIRequest;
import com.Code_Review_System.iO.AIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class RequestServiceImpl implements RequestService {

    @Value("${gemini.api.url}")
    String url;
    @Value("${gemini.api.key}")
    String key;

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public RequestServiceImpl(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.build();
        this.objectMapper = objectMapper;
    }

    @Override
    public String forword(AIRequest request) {

        // build prompt
        String prompt = convertPrompt(request);

        // call the api..

        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                        Map.of(
                                "parts", new Object[] {

                                        Map.of("text", prompt)
                                })
                }

        );
        String response = webClient.post().uri(url + key).bodyValue(requestBody).retrieve()
                .bodyToMono(String.class)
                .block();

        return extractResponse(response);
    }

    private String extractResponse(String response) {
        try {
            AIResponse geminiResponse = objectMapper.readValue(response, AIResponse.class);
            if (geminiResponse.getCandidates() != null && !geminiResponse.getCandidates().isEmpty()) {
                AIResponse.Candidate firstCandidate = geminiResponse.getCandidates().get(0);
                if (firstCandidate.getContent() != null &&
                        firstCandidate.getContent().getParts() != null &&
                        !firstCandidate.getContent().getParts().isEmpty()) {
                    return firstCandidate.getContent().getParts().get(0).getText();
                }
            }
            return "No content found in response";
        } catch (Exception e) {
            return "Error Parsing: " + e.getMessage();
        }
    }

    private String convertPrompt(AIRequest request) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("You are an expert software developer. Analyze the following code and provide: \n");
        prompt.append("1. A clear and optimized suggested solution.\n");
        prompt.append("2. An alternative approach (if possible).\n");
        prompt.append(
                "3. A detailed explanation comparing the original, suggested, and alternative solutions, highlighting pros, cons, and best use cases.\n\n");
        prompt.append("Here is the code:\n");
        prompt.append(request.getContent());

        return prompt.toString();
    }
}
