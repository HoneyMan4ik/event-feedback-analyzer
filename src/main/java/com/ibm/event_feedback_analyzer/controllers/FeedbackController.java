package com.ibm.event_feedback_analyzer.controllers;

import com.ibm.event_feedback_analyzer.dto.FeedbackRequestDto;
import com.ibm.event_feedback_analyzer.dto.FeedbackResponseDto;
import com.ibm.event_feedback_analyzer.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/events/{id}")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping("/feedback")
    public FeedbackResponseDto submitFeedback(@PathVariable int id, @RequestBody FeedbackRequestDto feedbackRequestDto){
        return feedbackService.submitFeedback(id, feedbackRequestDto);
    }

    @GetMapping("/summary")
    public Map<String, Long> getSentimentSummary(@PathVariable int id){
        return  feedbackService.getSentimentSummary(id);
    }
}
