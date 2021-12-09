package com.eventidge.eventidgeapi.api.v1.serializers;

import com.eventidge.eventidgeapi.api.v1.model.EventModel;
import com.eventidge.eventidgeapi.api.v1.model.input.EventModelInput;
import com.eventidge.eventidgeapi.domain.model.event.Event;
import com.eventidge.eventidgeapi.domain.model.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventSerializer {

    @Autowired
    public ModelMapper modelMapper;

    public EventModel toModel(Event event) {
        return modelMapper.map(event, EventModel.class);
    }

    public List<EventModel> toCollectionModel(List<Event> events) {
        return events.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Event toDomainObject(EventModelInput eventModelInput) {
        return modelMapper.map(eventModelInput, Event.class);
    }

}
