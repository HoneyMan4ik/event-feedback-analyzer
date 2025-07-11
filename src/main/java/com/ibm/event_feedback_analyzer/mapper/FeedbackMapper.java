package com.ibm.event_feedback_analyzer.mapper;

import com.ibm.event_feedback_analyzer.dto.FeedbackRequestDto;
import com.ibm.event_feedback_analyzer.dto.FeedbackResponseDto;
import com.ibm.event_feedback_analyzer.models.Event;
import com.ibm.event_feedback_analyzer.models.Feedback;

import java.time.LocalDateTime;

public class FeedbackMapper {
    public static Feedback toEntity(FeedbackRequestDto dto, Event event, String sentiment){
        Feedback feedback = new Feedback();
        feedback.setText(dto.getText());
        feedback.setSentiment(sentiment);
        feedback.setTimestamp(LocalDateTime.now());
        feedback.setEvent(event);
        return feedback;
    }
    public static FeedbackResponseDto toDto(Feedback feedback){
        FeedbackResponseDto dto = new FeedbackResponseDto();
        dto.setId(feedback.getId());
        dto.setText(feedback.getText());
        dto.setSentiment(feedback.getSentiment());
        dto.setTimestamp(feedback.getTimestamp());
        return dto;
    }
}