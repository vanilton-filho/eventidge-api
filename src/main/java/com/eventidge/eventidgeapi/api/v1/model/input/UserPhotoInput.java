package com.eventidge.eventidgeapi.api.v1.model.input;

import com.eventidge.eventidgeapi.core.validation.FileContentType;
import com.eventidge.eventidgeapi.core.validation.FileSize;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserPhotoInput {

    @NonNull
    @FileSize(max = "500KB")
    @FileContentType(allowed = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    private MultipartFile photo;

}
