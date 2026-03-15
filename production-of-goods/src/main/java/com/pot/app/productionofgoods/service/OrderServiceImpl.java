package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.entity.Order;
import com.pot.app.productionofgoods.repository.jpa.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    @Transactional
    public Order save(Order order) {
        return repository.save(order);
    }

    @Override
    @Transactional
    public Order findByNumber(String number) {
        return repository.findByNumberOrThrow(number);
    }

    @Override
    @Transactional
    public Optional<Order> findByNoTaskReserved() {
        return repository.findFirstByNoTaskReserved();
    }
}
