package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.entity.Car;
import ru.kpfu.itis.repository.CarRepository;

import java.util.List;

/**
 * Created by katemrrr on 12.05.17.
 */


@Service
@Transactional
public class CarServiceImpl implements CarService{

    @Autowired
    CarRepository carRepository;

    // Add few notes

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void delete(Car car) {
        carRepository.delete(car);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }
}
