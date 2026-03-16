package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.entity.Order;

import java.util.Optional;

public interface OrderService {

    Order save(Order order);

    Order findByNumber(String number);

    Optional<Order> findByNoTaskReserved();
}
