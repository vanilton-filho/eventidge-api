package com.eventidge.eventidgeapi.api.v1.serializers;

import com.eventidge.eventidgeapi.api.v1.model.UserModel;
import com.eventidge.eventidgeapi.api.v1.model.UserPersonModel;
import com.eventidge.eventidgeapi.api.v1.model.UserOrgModel;
import com.eventidge.eventidgeapi.api.v1.model.input.UserModelInput;
import com.eventidge.eventidgeapi.domain.model.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserSerializer {

    @Autowired
    private ModelMapper modelMapper;

    public UserModel toModel(User user) {
        return modelMapper.map(user, UserModel.class);
    }

    public UserPersonModel toUserPersonModel(User user) {
        return modelMapper.map(user, UserPersonModel.class);
    }

    public UserOrgModel toUserOrgModel(User user) {
        return modelMapper.map(user, UserOrgModel.class);
    }

    public List<UserModel> toCollectionModel(List<User> users) {
        return users.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public List<UserOrgModel> toOrgCollectionModel(List<User> users) {
        return users.stream()
                .map(this::toUserOrgModel)
                .collect(Collectors.toList());
    }

    public User toDomainObject(UserModelInput userModelInput) {
        return modelMapper.map(userModelInput, User.class);
    }

}
