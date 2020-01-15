package com.ivanturkin.cloud.app.taco.repository;

import com.ivanturkin.cloud.app.taco.domain.Order;
import com.ivanturkin.cloud.app.taco.domain.User;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

    List<Order> findByUserOrderByPlacedDateDesc(User user, Pageable pageable);
}
