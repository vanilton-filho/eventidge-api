package com.eventidge.eventidgeapi.core.serializer;

import com.eventidge.eventidgeapi.api.v1.model.AddressModel;
import com.eventidge.eventidgeapi.api.v1.model.MeetupModel;
import com.eventidge.eventidgeapi.api.v1.model.UserWinnerModel;
import com.eventidge.eventidgeapi.api.v1.model.input.UserPersonWithPasswordInput;
import com.eventidge.eventidgeapi.domain.model.meetup.Meetup;
import com.eventidge.eventidgeapi.domain.model.location.Address;
import com.eventidge.eventidgeapi.domain.model.meetup.MeetupPrizeDraw;
import com.eventidge.eventidgeapi.domain.model.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        // Address to AddressModel
        var addressToAddressModel = modelMapper.createTypeMap(Address.class, AddressModel.class);

        addressToAddressModel.<String>addMapping(
                src -> src.getCity().getName(),
                AddressModel::setCity
        );
        addressToAddressModel.<String>addMapping(
                src -> src.getCity().getState().getName(),
                AddressModel::setState
        );

        // UserWithPasswordInput to User
        var userWithPasswordInputToUser = modelMapper.createTypeMap(UserPersonWithPasswordInput.class, User.class);

        userWithPasswordInputToUser.<String>addMapping(
                UserPersonWithPasswordInput::getCpf,
                ((dest, value) -> dest.getPerson().getNaturalPerson().setCpf(value))
        );

        // MeetupPrizeDraw to UserWinnerModel
        var meetupPrizeDrawToUserWinnerModel = modelMapper.createTypeMap(MeetupPrizeDraw.class, UserWinnerModel.class);

        meetupPrizeDrawToUserWinnerModel.<String>addMapping(
                src -> src.getUser().getPerson().getName(),
                UserWinnerModel::setName
        );

        meetupPrizeDrawToUserWinnerModel.<String>addMapping(
                src -> src.getUser().getEmail(),
                UserWinnerModel::setEmail
        );

        meetupPrizeDrawToUserWinnerModel.<String>addMapping(
                src -> src.getUser().getPhone(),
                UserWinnerModel::setPhone
        );

        return modelMapper;
    }

}
