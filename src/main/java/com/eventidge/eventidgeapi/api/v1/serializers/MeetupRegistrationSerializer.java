package com.eventidge.eventidgeapi.api.v1.serializers;

import com.eventidge.eventidgeapi.api.v1.model.MeetupRegistrationModel;
import com.eventidge.eventidgeapi.api.v1.model.input.MeetupRegistrationInput;
import com.eventidge.eventidgeapi.domain.model.meetup.MeetupRegistration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MeetupRegistrationSerializer {

    @Autowired
    private ModelMapper modelMapper;

    public MeetupRegistrationModel toModel(MeetupRegistration meetupRegistration) {
        return modelMapper.map(meetupRegistration, MeetupRegistrationModel.class);
    }

    public List<MeetupRegistrationModel> toCollectionModel(List<MeetupRegistration> meetupRegistrations) {
        return meetupRegistrations.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public MeetupRegistration toDomainObject(MeetupRegistrationInput meetupRegistrationInput) {
        return modelMapper.map(meetupRegistrationInput, MeetupRegistration.class);
    }
}
