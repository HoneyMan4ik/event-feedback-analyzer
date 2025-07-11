package com.ibm.event_feedback_analyzer.repository;

import com.ibm.event_feedback_analyzer.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
