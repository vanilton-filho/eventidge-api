package com.eventidge.eventidgeapi.api.v1.controller;

import com.eventidge.eventidgeapi.api.v1.model.MeetupModel;
import com.eventidge.eventidgeapi.api.v1.model.input.EventModelInput;
import com.eventidge.eventidgeapi.api.v1.serializers.MeetupSerializer;
import com.eventidge.eventidgeapi.domain.model.meetup.Meetup;
import com.eventidge.eventidgeapi.domain.model.user.User;
import com.eventidge.eventidgeapi.domain.service.MeetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/meetups")
public class MeetupController {

    @Autowired
    private MeetupService meetupService;

    @Autowired
    private MeetupSerializer meetupSerializer;

    @GetMapping("/{tagOrCode}")
    public ResponseEntity<MeetupModel> getByTagOrCode(@RequestParam("type") String type, @PathVariable String tagOrCode) {
        Meetup meetup  = null;
        if (type.equals("tag")) {
            meetup = meetupService.getByTag(tagOrCode);
        } else if (type.equals("code")) {
            meetup = meetupService.getByCode(tagOrCode);
        }

        var meetupModel = meetupSerializer.toModel(meetup);
        return ResponseEntity.ok(meetupModel);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MeetupModel> create(@RequestBody @Valid EventModelInput eventModelInput) {
        Meetup meetup = meetupSerializer.toDomainObject(eventModelInput);

        meetup.setResponsible(new User());
        meetup.getResponsible().setId(1L); // TODO: Deixaremos fixo por enquanto até impl segurança na API
        Meetup meetupCreated = meetupService.save(meetup);

        return ResponseEntity.ok(meetupSerializer.toModel(meetupCreated));
    }

}
