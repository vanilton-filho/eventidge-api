package com.eventidge.eventidgeapi.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EventModelInput {

    @NotBlank
    private String tag;

    @NotBlank
    private String name;

    private String description;

}
