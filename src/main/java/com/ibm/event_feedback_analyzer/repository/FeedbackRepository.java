package com.ibm.event_feedback_analyzer.repository;

import com.ibm.event_feedback_analyzer.models.Event;
import com.ibm.event_feedback_analyzer.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findByEvent(Event event);
}
