package com.ivanturkin.cloud.app.taco.controller;

import com.ivanturkin.cloud.app.taco.configuration.properties.OrderProperties;
import com.ivanturkin.cloud.app.taco.domain.Order;
import com.ivanturkin.cloud.app.taco.domain.User;
import com.ivanturkin.cloud.app.taco.repository.OrderRepository;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepository;
    private OrderProperties orderProperties;

    public OrderController(OrderRepository orderRepository, OrderProperties orderProperties) {
        this.orderRepository = orderRepository;
        this.orderProperties = orderProperties;
    }

    @GetMapping
    public String ordersForUser(
        @AuthenticationPrincipal User user, Model model) {

        Pageable pageRequest = PageRequest.of(0, orderProperties.getPageSize());
        model.addAttribute("orders",
            orderRepository.findByUserOrderByPlacedDateDesc(user, pageRequest));

        return "orderList";
    }

    @GetMapping("/current")
    public String orderForm(Model model) {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors,
                               SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            log.error("Errors occurred during processing order: {}", errors);
            return "orderForm";
        }

        log.info("Saving order... " + order);
        order.setUser(user);
        orderRepository.save(order);
        sessionStatus.setComplete();

        log.info("Order submitted: " + order);
        return "redirect:/";
    }
}
