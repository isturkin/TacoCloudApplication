package com.ivanturkin.cloud.app.taco.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Taco {

    @NotNull
    @Size(min = 5, message = "Name must contains at least 5 characters")
    private String name;

    @Size(min = 1, message = "You must choose ate least 1 ingredient")
    private List<String> ingredients;

}
