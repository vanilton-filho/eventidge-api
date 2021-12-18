package com.eventidge.eventidgeapi.api.v1.serializers;

import com.eventidge.eventidgeapi.api.v1.model.UserWinnerModel;
import com.eventidge.eventidgeapi.domain.model.meetup.MeetupPrizeDraw;
import com.eventidge.eventidgeapi.domain.model.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MeetupPrizeDrawSerializer {

    @Autowired
    private ModelMapper modelMapper;

    public UserWinnerModel toModel(MeetupPrizeDraw meetupPrizeDraw) {
        return modelMapper.map(meetupPrizeDraw, UserWinnerModel.class);
    }

    public List<UserWinnerModel> toCollectionModel(List<MeetupPrizeDraw> meetupPrizeDraws) {
        return meetupPrizeDraws.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
