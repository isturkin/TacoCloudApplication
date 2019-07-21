package com.ivanturkin.cloud.app.taco.repository;

import com.ivanturkin.cloud.app.taco.domain.Order;

public interface OrderRepository {

    Order save(Order order);
}
