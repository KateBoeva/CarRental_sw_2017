package ru.kpfu.itis.service;

import ru.kpfu.itis.entity.Car;

import java.util.List;

/**
 * Created by katemrrr on 12.05.17.
 */
public interface CarService {

    Car save(Car car);

    void delete(Car car);

    List<Car> findAll();

}
