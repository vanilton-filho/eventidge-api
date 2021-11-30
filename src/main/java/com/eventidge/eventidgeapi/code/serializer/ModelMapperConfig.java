package com.eventidge.eventidgeapi.code.serializer;

import com.eventidge.eventidgeapi.api.v1.model.AddressModel;
import com.eventidge.eventidgeapi.api.v1.model.input.AddressModelInput;
import com.eventidge.eventidgeapi.domain.model.Address;
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
                (dest, value) -> dest.setCity(value)
        );
        addressToAddressModel.<String>addMapping(
                src -> src.getCity().getState().getName(),
                (dest, value) -> dest.setState(value)
        );


        return modelMapper;
    }

}
