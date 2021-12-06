package com.eventidge.eventidgeapi.core.serializer;

import com.eventidge.eventidgeapi.api.v1.model.AddressModel;
import com.eventidge.eventidgeapi.api.v1.model.UserModel;
import com.eventidge.eventidgeapi.api.v1.model.input.UserModelInput;
import com.eventidge.eventidgeapi.domain.model.location.Address;
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

        // UserModelInput to User
        var userModelInputToUser = modelMapper.createTypeMap(UserModelInput.class, User.class);

        userModelInputToUser.<String>addMapping(
                UserModelInput::getCpf,
                (dest, value) -> dest.getPerson().getNaturalPerson().setCpf(value)
        );


        return modelMapper;
    }

}
