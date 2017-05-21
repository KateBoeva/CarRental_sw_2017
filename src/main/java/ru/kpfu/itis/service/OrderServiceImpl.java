package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.entity.Order;
import ru.kpfu.itis.repository.OrderRepository;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

/**
 * Created by katemrrr on 12.05.17.
 */

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Добавить несколько записей
    @PostConstruct
    public void generateTestData() {
        save(new Order("Сергей", "Боев", "Петрович", "Kia Sportage", "89872846360", "23.06.2017", "25.06.2017"));
        save(new Order("Петр", "Боев", "Васильевич", "Chevrolet Lacetti", "89872846360", "20.06.2017", "23.06.2017"));
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
