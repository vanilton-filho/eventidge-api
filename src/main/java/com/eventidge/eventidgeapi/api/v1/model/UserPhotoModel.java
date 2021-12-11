package com.eventidge.eventidgeapi.api.v1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPhotoModel {

    private Long id;
    private String fileName;
    private String contentType;
    private Long length;

}
