package com.eventidge.eventidgeapi.api.v1.controller;

import com.eventidge.eventidgeapi.api.v1.model.UserModel;
import com.eventidge.eventidgeapi.api.v1.model.input.UserModelInput;
import com.eventidge.eventidgeapi.api.v1.serializers.UserSerializer;
import com.eventidge.eventidgeapi.domain.model.user.User;
import com.eventidge.eventidgeapi.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(userSerializer.toModel(user));
    }

    @PostMapping("/people")
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel create(@RequestBody UserModelInput userModelInput) {
        User user = userSerializer.toDomainObject(userModelInput);
        User userCreated = userService.save(user);
        UserModel userModel = userSerializer.toModel(userCreated);
        return userModel;
    }

}
