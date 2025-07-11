package com.ibm.event_feedback_analyzer.controllers;

import com.ibm.event_feedback_analyzer.dto.EventRequestDto;
import com.ibm.event_feedback_analyzer.dto.EventResponseDto;
import com.ibm.event_feedback_analyzer.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    public EventResponseDto createEvent(@RequestBody EventRequestDto eventRequestDto){
        return eventService.createEvent(eventRequestDto);
    }

    @GetMapping
    public List<EventResponseDto> getAllEvents(){
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventResponseDto getEventById(@PathVariable int id){
        return eventService.getEventById(id);
    }
}
