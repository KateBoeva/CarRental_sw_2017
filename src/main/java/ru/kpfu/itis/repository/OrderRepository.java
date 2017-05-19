package ru.kpfu.itis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.entity.Order;

import java.util.List;

/**
 * Created by katemrrr on 12.05.17.
 */

//@Transactional(propagation = Propagation.MANDATORY)
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAll();

}
