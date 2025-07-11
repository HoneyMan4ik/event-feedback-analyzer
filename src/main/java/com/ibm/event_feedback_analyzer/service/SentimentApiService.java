package com.ibm.event_feedback_analyzer.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;

import org.springframework.http.HttpHeaders;

import java.util.Collections;
import java.util.Map;

@Service
public class SentimentApiService {

    private final RestTemplate restTemplate;

    @Value("${huggingface.api.key}")
    private String apiKey;

    private final String API_URL = "https://api-inference.huggingface.co/models/cardiffnlp/twitter-roberta-base-sentiment";

    public SentimentApiService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    private static final Map<String,String> LABEL_MAPPING = Map.of(
            "LABEL_0", "negative",
            "LABEL_1", "neutral",
            "LABEL_2", "positive"
    );

    public String analyzeSentiment(String text){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(apiKey);

        String requestJson = "{\"inputs\": \"" + text + "\"}";
        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(API_URL, request, String.class);

        JSONArray jsonArray = new JSONArray(response.getBody());
        JSONArray innerArray = jsonArray.getJSONArray(0);

        String bestLabel = "";
        double bestScore = 0;

        for (int i = 0; i < innerArray.length(); i++) {
            JSONObject obj = innerArray.getJSONObject(i);
            String label = obj.getString("label");
            double score = obj.getDouble("score");

            if(score > bestScore){
                bestLabel = label;
                bestScore = score;
            }
        }
        return LABEL_MAPPING.getOrDefault(bestLabel, "unknown");
    }
}
