package com.eventidge.eventidgeapi.api.v1.serializers;

import com.eventidge.eventidgeapi.api.v1.model.UserPhotoModel;
import com.eventidge.eventidgeapi.domain.model.user.UserPhoto;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserPhotoSerializer {

    @Autowired
    private ModelMapper modelMapper;

    public UserPhotoModel toModel(UserPhoto userPhoto) {
        return modelMapper.map(userPhoto, UserPhotoModel.class);
    }

}
