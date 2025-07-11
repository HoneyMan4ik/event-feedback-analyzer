package com.ibm.event_feedback_analyzer.service.impl;

import com.ibm.event_feedback_analyzer.dto.EventRequestDto;
import com.ibm.event_feedback_analyzer.dto.EventResponseDto;
import com.ibm.event_feedback_analyzer.exceptions.EventNotFoundException;
import com.ibm.event_feedback_analyzer.mapper.EventMapper;
import com.ibm.event_feedback_analyzer.models.Event;
import com.ibm.event_feedback_analyzer.repository.EventRepository;
import com.ibm.event_feedback_analyzer.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @Override
    public EventResponseDto createEvent(EventRequestDto event) {
        Event eventToSave = EventMapper.toEntity(event);
        Event savedEvent = eventRepository.save(eventToSave);
        return EventMapper.toDto(savedEvent);
    }

    @Override
    public List<EventResponseDto> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(EventMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public EventResponseDto getEventById(int id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + id));
        return EventMapper.toDto(event);
    }
}
