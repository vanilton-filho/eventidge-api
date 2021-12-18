package com.eventidge.eventidgeapi.api.v1.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class UserWinnerModel {

    private String code;
    private String prize;
    private String tag;
    private String name;
    private String email;
    private String phone;
    private OffsetDateTime createdAt;

}
