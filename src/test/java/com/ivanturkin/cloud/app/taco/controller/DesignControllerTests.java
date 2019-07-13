package com.ivanturkin.cloud.app.taco.controller;

import com.ivanturkin.cloud.app.taco.repository.IngredientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = DesignTacoController.class)
public class DesignControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IngredientRepository ingredientRepository;

    @Test
    public void shouldSendDesignPage_whenValidGetRequest() throws Exception {
        mockMvc.perform(get("/design"))
                .andExpect(view().name("design"))
                .andExpect(content().string(
                        containsString("Design your Taco!")))
                .andExpect(status().isOk());
    }

}
