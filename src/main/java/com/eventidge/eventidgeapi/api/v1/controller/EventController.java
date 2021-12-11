package com.eventidge.eventidgeapi.api.v1.controller;

import com.eventidge.eventidgeapi.api.v1.model.EventModel;
import com.eventidge.eventidgeapi.api.v1.model.input.EventModelInput;
import com.eventidge.eventidgeapi.api.v1.serializers.EventSerializer;
import com.eventidge.eventidgeapi.domain.model.event.Event;
import com.eventidge.eventidgeapi.domain.model.user.User;
import com.eventidge.eventidgeapi.domain.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventSerializer eventSerializer;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EventModel> create(@RequestBody @Valid EventModelInput eventModelInput) {
        Event event = eventSerializer.toDomainObject(eventModelInput);

        event.setResponsible(new User());
        event.getResponsible().setId(1L); // TODO: Deixaremos fixo por enquanto até impl segurança na API
        Event eventCreated = eventService.save(event);

        return ResponseEntity.ok(eventSerializer.toModel(eventCreated));
    }

}
