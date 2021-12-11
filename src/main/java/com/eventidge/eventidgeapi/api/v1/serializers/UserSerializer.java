package com.eventidge.eventidgeapi.api.v1.serializers;

import com.eventidge.eventidgeapi.api.v1.model.UserModel;
import com.eventidge.eventidgeapi.api.v1.model.UserOrgModel;
import com.eventidge.eventidgeapi.api.v1.model.input.UserOrganizationModelInput;
import com.eventidge.eventidgeapi.api.v1.model.input.UserPersonModelInput;
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

    public UserModel toUserPersonModel(User user) {
        return modelMapper.map(user, UserModel.class);
    }

    public UserOrgModel toUserOrgModel(User user) {
        return modelMapper.map(user, UserOrgModel.class);
    }

    public List<UserModel> toCollectionModel(List<User> users) {
        return users.stream()
                .map(this::toUserPersonModel)
                .collect(Collectors.toList());
    }

    // TODO: Refatorar para termos apenas uma classe pai para ambas UserPersonModelInput e UserOrganizationModelInput

    public User toDomainUserPersonObject(UserPersonModelInput userModelInput) {
        return modelMapper.map(userModelInput, User.class);
    }

    public User toDomainUserOrgObject(UserOrganizationModelInput userOrganizationModelInput) {
        return modelMapper.map(userOrganizationModelInput, User.class);
    }
}
