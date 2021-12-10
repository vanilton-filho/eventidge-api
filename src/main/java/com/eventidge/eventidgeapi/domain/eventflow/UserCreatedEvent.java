package com.eventidge.eventidgeapi.domain.eventflow;

import com.eventidge.eventidgeapi.domain.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreatedEvent {

    private User user;
}
