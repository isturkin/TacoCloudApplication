package com.ivanturkin.cloud.app.taco.controller;

import com.ivanturkin.cloud.app.taco.domain.Ingredient;
import com.ivanturkin.cloud.app.taco.domain.Order;
import com.ivanturkin.cloud.app.taco.domain.Taco;
import com.ivanturkin.cloud.app.taco.repository.IngredientRepository;
import com.ivanturkin.cloud.app.taco.repository.TacoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        fillModelWithIngredientList(model);

        model.addAttribute("design", new Taco());

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Taco taco, Errors errors, Model model,
                                @ModelAttribute("order") Order order) {
        if (errors.hasErrors()) {
            fillModelWithIngredientList(model);
            log.error("Errors occurred during processing your design: " + errors);
            return "design";
        }

        Taco saved = tacoRepository.save(taco);
        order.getTacos().add(saved);

        log.info("Processing design: " + saved);
        return "redirect:/orders/current";
    }

    private void fillModelWithIngredientList(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(i -> ingredients.add(i));

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream()
                .filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }
}
