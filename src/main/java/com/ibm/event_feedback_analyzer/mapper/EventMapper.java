package com.ibm.event_feedback_analyzer.mapper;

import com.ibm.event_feedback_analyzer.dto.EventRequestDto;
import com.ibm.event_feedback_analyzer.dto.EventResponseDto;
import com.ibm.event_feedback_analyzer.models.Event;

public class EventMapper {
    public static Event toEntity(EventRequestDto dto){
        Event event = new Event();
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        return event;
    }
    public static EventResponseDto toDto(Event event){
        EventResponseDto dto = new EventResponseDto();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        return dto;
    }
}
