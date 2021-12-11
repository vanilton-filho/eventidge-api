package com.eventidge.eventidgeapi.api.v1.controller;

import com.eventidge.eventidgeapi.api.v1.model.UserModel;
import com.eventidge.eventidgeapi.api.v1.model.UserOrgModel;
import com.eventidge.eventidgeapi.api.v1.model.input.UserOrgWithPasswordInput;
import com.eventidge.eventidgeapi.api.v1.model.input.UserPersonWithPasswordInput;
import com.eventidge.eventidgeapi.api.v1.serializers.UserSerializer;
import com.eventidge.eventidgeapi.domain.model.user.User;
import com.eventidge.eventidgeapi.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSerializer userSerializer;

    @GetMapping
    public ResponseEntity<List<UserModel>> getAll() {
        List<User> users = userService.findAll();
        List<UserModel> userSerialized = userSerializer.toCollectionModel(users);

        return ResponseEntity.ok(userSerialized);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getById(@PathVariable Long id) {
        User user = userService.findOrFail(id);
        return ResponseEntity.ok(userSerializer.toUserPersonModel(user));
    }

    @PostMapping("/people")
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel createUserPerson(@RequestBody @Valid UserPersonWithPasswordInput userModelInput) {
        User user = userSerializer.toDomainUserPersonObject(userModelInput);
        User userCreated = userService.saveUserPerson(user);
        return userSerializer.toUserPersonModel(userCreated);

    }

    @PostMapping("/org")
    @ResponseStatus(HttpStatus.CREATED)
    public UserOrgModel create(@RequestBody @Valid UserOrgWithPasswordInput userOrgWithPasswordInput) {
        User user = userSerializer.toDomainUserOrgObject(userOrgWithPasswordInput);
        User userCreated = userService.saveUserOrg(user);
        return userSerializer.toUserOrgModel(userCreated);
    }

}
