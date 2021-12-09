package com.eventidge.eventidgeapi.domain.service;

import com.eventidge.eventidgeapi.domain.exception.EventByCodeNotFoundException;
import com.eventidge.eventidgeapi.domain.exception.EventByTagNotFoundException;
import com.eventidge.eventidgeapi.domain.model.event.Event;
import com.eventidge.eventidgeapi.domain.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event getByCode(String code) {
        return eventRepository.findByCode(code)
                .orElseThrow(() -> new EventByCodeNotFoundException(code));
    }

    public Event getByTag(String tag) {
        return eventRepository.findByTag(tag)
                .orElseThrow(() -> new EventByTagNotFoundException(tag));
    }

    public Event save(Event event) {
        return this.eventRepository.save(event);
    }

}
