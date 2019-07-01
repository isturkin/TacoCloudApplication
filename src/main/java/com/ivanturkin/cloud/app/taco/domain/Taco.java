package com.ivanturkin.cloud.app.taco.domain;

import lombok.Data;

import java.util.List;

@Data
public class Taco {

    private String name;
    private List<String> ingredients;

}
