package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.entity.Order;
import ru.kpfu.itis.repository.OrderRepository;

import javax.annotation.PostConstruct;
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

    @PostConstruct
    public void generateTestData() {
        save(new Order("Боев", "Сергей", "Петрович", "Kia Sportage", "89872846360", "23.06.2017", "25.06.2017"));
        save(new Order("Иванов", "Дмитрий", "Александрович", "BMW 5X", "89274560937", "12.07.2017", "23.07.2017"));
        save(new Order("Кашапов", "Азат", "Робертович", "Mercedes Benz", "89172837465", "15.07.2017", "23.07.2017"));
        save(new Order("Усачев", "Артемий", "Юрьевич", "Hundai Solaris", "89274385955", "17.08.2017", "23.08.2017"));
        save(new Order("Боев", "Петр", "Васильевич", "Chevrolet Lacetti", "89872984736", "20.08.2017", "23.08.2017"));
        save(new Order("Евстафьев", "Михаил", "Евгеньевич", "Kia Rio", "89173894059", "23.09.2017", "23.09.2017"));
        save(new Order("Нуруллина", "Лия", "Радиковна", "Volkswagen Passat", "89197364892", "27.09.2017", "23.09.2017"));
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
