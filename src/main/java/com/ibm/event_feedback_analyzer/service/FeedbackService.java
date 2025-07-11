package com.ibm.event_feedback_analyzer.service;

import com.ibm.event_feedback_analyzer.dto.FeedbackRequestDto;
import com.ibm.event_feedback_analyzer.dto.FeedbackResponseDto;

import java.util.List;
import java.util.Map;

public interface FeedbackService {
    FeedbackResponseDto submitFeedback(int eventId, FeedbackRequestDto feedbackRequestDto);
    List<FeedbackResponseDto> returnFeedbackForEvent(int eventId);
    Map<String, Long> getSentimentSummary(int eventId);
}
