package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.entity.Car;
import ru.kpfu.itis.repository.CarRepository;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by katemrrr on 12.05.17.
 */


@Service
public class CarServiceImpl implements CarService{

    @Autowired
    CarRepository carRepository;

    // Add few notes
    @PostConstruct
    public void generateTestData() {
        save(new Car("Kia Rio", 800, 28756, 100, 2014));
        save(new Car("BMW 5X", 1500, 23938, 200, 2015));
        save(new Car("Mercedes Benz", 1300, 18546, 190, 2013));
        save(new Car("Lada Granta", 600, 15853, 90, 2010));
        save(new Car("Hundai Solaris", 900, 19863, 110, 2008));
        save(new Car("Kia Sportage", 1000, 22945, 150, 2016));
        save(new Car("Volkswagen Passat", 1100, 20944, 150, 2007));
        save(new Car("Chevrolet Lacetti", 700, 12934, 100, 2010));
    }

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
