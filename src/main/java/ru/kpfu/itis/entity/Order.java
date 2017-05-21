package ru.kpfu.itis.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by katemrrr on 12.05.17.
 */

//Одна бронь это:  
//        * ФИО клиента
//        * контактный телефон клиента
//         * марка и модель автомобиля
//         * дата выдачи автомобиля клиенту 
//        * дата возврата автомобиля


@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String model;

    @Column
    private String surname;

    @Column
    private String patronymic;

    @Column
    private String phone;

    @Column
    private String getting;

    @Column
    private String refunding;

    public Order() {}

    public Order(String surname, String name, String patronymic, String model, String phone, String getting, String refunding) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.model = model;
        this.phone = phone;
        this.getting = getting;
        this.refunding = refunding;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGetting() {
        return getting;
    }

    public void setGetting(String getting) {
        this.getting = getting;
    }

    public String getRefunding() {
        return refunding;
    }

    public void setRefunding(String refunding) {
        this.refunding = refunding;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
