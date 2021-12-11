package com.eventidge.eventidgeapi.api.v1.controller;

import com.eventidge.eventidgeapi.api.v1.model.UserModel;
import com.eventidge.eventidgeapi.api.v1.model.UserPersonModel;
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
        List<UserModel> usersSerialized = userSerializer.toCollectionModel(users);

        return ResponseEntity.ok(usersSerialized);
    }

    // TODO: Será mais interessante desenvolver busca por query params
    @GetMapping("/orgs")
    public ResponseEntity<List<UserOrgModel>> getAllOrgs() {
        List<User> orgs = userService.findAllOrgs();
        List<UserOrgModel> usersSerialized = userSerializer.toOrgCollectionModel(orgs);

        return ResponseEntity.ok(usersSerialized);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getById(@PathVariable Long id) {
        User user = userService.findOrFail(id);
        return ResponseEntity.ok(userSerializer.toModel(user));
    }

    @PostMapping("/people")
    @ResponseStatus(HttpStatus.CREATED)
    public UserPersonModel createUserPerson(@RequestBody @Valid UserPersonWithPasswordInput userModelInput) {
        User user = userSerializer.toDomainObject(userModelInput);
        User userCreated = userService.save(user);
        return userSerializer.toUserPersonModel(userCreated);
    }

    @PostMapping("/orgs")
    @ResponseStatus(HttpStatus.CREATED)
    public UserOrgModel create(@RequestBody @Valid UserOrgWithPasswordInput userOrgWithPasswordInput) {
        User user = userSerializer.toDomainObject(userOrgWithPasswordInput);
        User userCreated = userService.save(user);
        return userSerializer.toUserOrgModel(userCreated);
    }

}
