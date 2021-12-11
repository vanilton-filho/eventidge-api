package com.eventidge.eventidgeapi.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserPhotoInput {

    private MultipartFile photo;

}
