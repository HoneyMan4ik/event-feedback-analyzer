package com.ibm.event_feedback_analyzer.service.impl;

import com.ibm.event_feedback_analyzer.dto.FeedbackRequestDto;
import com.ibm.event_feedback_analyzer.dto.FeedbackResponseDto;
import com.ibm.event_feedback_analyzer.exceptions.BadRequestException;
import com.ibm.event_feedback_analyzer.exceptions.EventNotFoundException;
import com.ibm.event_feedback_analyzer.mapper.FeedbackMapper;
import com.ibm.event_feedback_analyzer.models.Event;
import com.ibm.event_feedback_analyzer.models.Feedback;
import com.ibm.event_feedback_analyzer.repository.EventRepository;
import com.ibm.event_feedback_analyzer.repository.FeedbackRepository;
import com.ibm.event_feedback_analyzer.service.FeedbackService;
import com.ibm.event_feedback_analyzer.service.SentimentApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final EventRepository eventRepository;
    private final SentimentApiService sentimentApiService;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository,
                               EventRepository eventRepository,
                               SentimentApiService sentimentApiService){
        this.feedbackRepository = feedbackRepository;
        this.eventRepository = eventRepository;
        this.sentimentApiService = sentimentApiService;
    }

    @Override
    public FeedbackResponseDto submitFeedback(int eventId, FeedbackRequestDto feedbackRequestDto) {
        if(feedbackRequestDto.getText() == null || feedbackRequestDto.getText().isBlank()){
            throw new BadRequestException("Feedback test must not be empty");
        }

        Event event = eventRepository.findById(eventId).orElseThrow(()-> new EventNotFoundException("There is no such event with ID: " + eventId));
        String sentiment = sentimentApiService.analyzeSentiment(feedbackRequestDto.getText());

        Feedback feedback = FeedbackMapper.toEntity(feedbackRequestDto, event, sentiment);
        Feedback savedFeedback = feedbackRepository.save(feedback);

        return FeedbackMapper.toDto(savedFeedback);
    }

    @Override
    public List<FeedbackResponseDto> returnFeedbackForEvent(int eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(()-> new EventNotFoundException("There is no such event with ID: " + eventId));
        List<Feedback> feedbackList = feedbackRepository.findByEvent(event);

        return feedbackList.stream().map(FeedbackMapper::toDto).toList();
    }

    @Override
    public Map<String, Long> getSentimentSummary(int eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(()-> new EventNotFoundException("There is no such event with ID: " + eventId));
        List<Feedback> feedbackList = feedbackRepository.findByEvent(event);

        return feedbackList.stream().collect(Collectors.groupingBy(Feedback::getSentiment, Collectors.counting()));
    }
}
