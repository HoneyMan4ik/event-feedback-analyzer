package com.ibm.event_feedback_analyzer.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FeedbackResponseDto {
    private int id;
    private String text;
    private String sentiment;
    private LocalDateTime timestamp;
}
