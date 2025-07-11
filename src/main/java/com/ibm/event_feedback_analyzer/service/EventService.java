package com.ibm.event_feedback_analyzer.service;

import com.ibm.event_feedback_analyzer.dto.EventRequestDto;
import com.ibm.event_feedback_analyzer.dto.EventResponseDto;

import java.util.List;

public interface EventService {
    EventResponseDto createEvent(EventRequestDto event);
    List<EventResponseDto> getAllEvents();
    EventResponseDto getEventById(int id);
}
