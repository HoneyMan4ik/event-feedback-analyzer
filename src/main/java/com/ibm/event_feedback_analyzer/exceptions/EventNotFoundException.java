package com.ibm.event_feedback_analyzer.exceptions;

public class EventNotFoundException extends RuntimeException{
    public EventNotFoundException(String message){
        super (message);
    }
}
