package ru.kpfu.itis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.entity.Car;

import java.util.List;

/**
 * Created by katemrrr on 12.05.17.
 */


//@Transactional(propagation = Propagation.MANDATORY)
public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findAll();

}
