package com.eventidge.eventidgeapi.api.v1.serializers;

import com.eventidge.eventidgeapi.api.v1.model.MeetupModel;
import com.eventidge.eventidgeapi.api.v1.model.input.EventModelInput;
import com.eventidge.eventidgeapi.domain.model.meetup.Meetup;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MeetupSerializer {

    @Autowired
    public ModelMapper modelMapper;

    public MeetupModel toModel(Meetup meetup) {
        return modelMapper.map(meetup, MeetupModel.class);
    }

    public List<MeetupModel> toCollectionModel(List<Meetup> meetups) {
        return meetups.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Meetup toDomainObject(EventModelInput eventModelInput) {
        return modelMapper.map(eventModelInput, Meetup.class);
    }

}
