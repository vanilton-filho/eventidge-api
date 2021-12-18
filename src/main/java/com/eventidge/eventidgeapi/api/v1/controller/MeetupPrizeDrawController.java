package com.eventidge.eventidgeapi.api.v1.controller;

import com.eventidge.eventidgeapi.api.v1.model.UserWinnerModel;
import com.eventidge.eventidgeapi.api.v1.model.input.MeetupPrizeDrawInput;
import com.eventidge.eventidgeapi.api.v1.serializers.MeetupPrizeDrawSerializer;
import com.eventidge.eventidgeapi.domain.model.meetup.Meetup;
import com.eventidge.eventidgeapi.domain.model.meetup.MeetupPrizeDraw;
import com.eventidge.eventidgeapi.domain.service.MeetupPrizeDrawService;
import com.eventidge.eventidgeapi.domain.service.MeetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/meetups/draws/{meetupTag}")
public class MeetupPrizeDrawController {

    @Autowired
    private MeetupPrizeDrawService meetupPrizeDrawService;

    @Autowired
    private MeetupPrizeDrawSerializer meetupPrizeDrawSerializer;

    @Autowired
    private MeetupService meetupService;

    @GetMapping
    public ResponseEntity<List<UserWinnerModel>> getWinner(@PathVariable String meetupTag) {
        Meetup meetup = meetupService.getByTag(meetupTag);
        List<MeetupPrizeDraw> users = meetupPrizeDrawService.findAllUsersByMeetup(meetup);

        List<UserWinnerModel> userWinnerModel = meetupPrizeDrawSerializer.toCollectionModel(users);

        return ResponseEntity.ok(userWinnerModel);
    }

    @PutMapping
    public ResponseEntity<UserWinnerModel> putWinner(@PathVariable String meetupTag, @RequestBody @Valid MeetupPrizeDrawInput prizeDrawInput) {
        MeetupPrizeDraw winner = meetupPrizeDrawService.getWinnerPrizeDraw(meetupTag, prizeDrawInput.getPrize());
        UserWinnerModel userModel = meetupPrizeDrawSerializer.toModel(winner);

        MeetupPrizeDraw meetupPrizeDraw = meetupPrizeDrawService.findMeetupPrizeDrawByTag(meetupTag);
        userModel.setTag(meetupPrizeDraw.getTag());
        userModel.setCode(meetupPrizeDraw.getCode());

        return ResponseEntity.ok(userModel);
    }

}
