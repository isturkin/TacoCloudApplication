package com.ivanturkin.cloud.app.taco.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = OrderController.class)
public class OrderControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldShowOrderForm_whenValidGetRequest() throws Exception {
        mockMvc.perform(get("/orders/current"))
                .andExpect(view().name("orderForm"))
                .andExpect(content().string(
                        containsString("Order your Taco creations!")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateOrderAndRedirectToHomePage_whenValidFormDataReceived() throws Exception {
        mockMvc.perform(post("/orders/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "For me")
                .param("city", "San Francisco")
                .param("street", "Any street")
                .param("state", "California")
                .param("zip", "000000")
                .param("ccNumber", "0000000000000000")
                .param("ccExpiration", "01/19")
                .param("ccCVV", "000"))
                .andExpect(view().name("redirect:/"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void shouldShowOrderFormAgainWithErrors_whenInvalidFormDataReceived() throws Exception {
        mockMvc.perform(post("/orders/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "For me"))
                .andExpect(view().name("orderForm"))
                .andExpect(status().isOk());
    }

}
