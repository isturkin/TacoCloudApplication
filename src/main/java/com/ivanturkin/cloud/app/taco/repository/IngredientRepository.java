package com.ivanturkin.cloud.app.taco.repository;

import com.ivanturkin.cloud.app.taco.domain.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();
    Ingredient findOne(String id);
    Ingredient save(Ingredient ingredient);

}
