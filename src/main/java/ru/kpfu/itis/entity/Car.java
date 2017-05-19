package ru.kpfu.itis.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by katemrrr on 12.05.17.
 */

//Одна запись это:
//        *марка и модель автомобиля (одно поле)
//        *год выпуска
//        *пробег
//        *мощность двигателя (л.с.)
//        *стоимость часовой аренды


@Entity
@Table(name = "cars")
public class Car implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private int price;

    @Column
    private int run;

    @Column
    private int power;

    @Column
    private int year;

    public Car() {}

    public Car(String name, int price, int run, int power, int year){
        this.name = name;
        this.price = price;
        this.run = run;
        this.power = power;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
