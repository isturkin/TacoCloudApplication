package com.ivanturkin.cloud.app.taco.api;

import com.ivanturkin.cloud.app.taco.api.exception.NotFoundException;
import com.ivanturkin.cloud.app.taco.domain.Order;
import com.ivanturkin.cloud.app.taco.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static java.util.Objects.nonNull;

@Slf4j
@RequestMapping("/orders")
@RestController
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public void patchOrder(@PathVariable("orderId") Long orderId, @RequestBody Order patch) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            updateOrderFromPatch(order.get(), patch);
        } else {
            throw new NotFoundException("Order not found: " + orderId);
        }
    }

    private void updateOrderFromPatch(Order order, Order patch) {
        if (nonNull(patch.getStreet())) {
            order.setStreet(patch.getStreet());
        }
        if (nonNull(patch.getState())) {
            order.setState(patch.getState());
        }
        if (nonNull(patch.getCity())) {
            order.setCity(patch.getCity());
        }
        if (nonNull(patch.getZip())) {
            order.setZip(patch.getZip());
        }
        if (nonNull(patch.getCcNumber())) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (nonNull(patch.getCcExpiration())) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (nonNull(patch.getCvv())) {
            order.setCvv(patch.getCvv());
        }
        orderRepository.save(order);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException exception) {
            log.error("Order not found: " + orderId);
        }
    }
}
