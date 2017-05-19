package ru.kpfu.itis.service;

import ru.kpfu.itis.entity.Order;

import java.util.List;

/**
 * Created by katemrrr on 12.05.17.
 */
public interface OrderService {

    Order save(Order order);

    void delete(Order order);

    List<Order> findAll();

}
