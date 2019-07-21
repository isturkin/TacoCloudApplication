package com.ivanturkin.cloud.app.taco.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivanturkin.cloud.app.taco.domain.Order;
import com.ivanturkin.cloud.app.taco.domain.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderTacoInserter;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbc) {
        this.orderInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("orders")
                .usingGeneratedKeyColumns("id");

        this.orderTacoInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("orders_tacos");
    }

    @Override
    public Order save(Order order) {
        long orderId = saveOrderDetails(order);
        List<Taco> tacos = order.getTacos();

        for (Taco taco : tacos) {
            saveTacoToOrder(taco, orderId);
        }

        return order;
    }

    private long saveOrderDetails(Order order) {
        Map<String, Object> values = new HashMap<>();
        values.put("deliveryName", order.getName());
        values.put("deliveryStreet", order.getStreet());
        values.put("deliveryCity", order.getCity());
        values.put("deliveryState", order.getState());
        values.put("deliveryZip", order.getZip());
        values.put("ccNumber", order.getCcNumber());
        values.put("ccExpiration", order.getCcExpiration());
        values.put("ccCVV", order.getCcCVV());
        values.put("placedDate", new Date());

        return orderInserter.executeAndReturnKey(values).longValue();
    }

    private void saveTacoToOrder(Taco taco, long orderId) {
        Map<String, Object> values = new HashMap<>();
        values.put("orderId", orderId);
        values.put("tacoId", taco.getId());
        orderTacoInserter.execute(values);
    }

}
