package com.ivanturkin.cloud.app.taco.controller;

import com.ivanturkin.cloud.app.taco.domain.Ingredient;
import com.ivanturkin.cloud.app.taco.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();

        ingredients.add(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
        ingredients.add(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
        ingredients.add(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
        ingredients.add(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
        ingredients.add(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
        ingredients.add(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
        ingredients.add(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
        ingredients.add(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
        ingredients.add(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
        ingredients.add(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());

        return "design";
    }

    @PostMapping
    public String processDesign(Taco taco) {
        log.info("Processing design: " + taco);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream()
                .filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }
}
