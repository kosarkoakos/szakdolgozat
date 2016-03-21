package com.szakdolgozat.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Ákos on 2016.03.21..
 */
@Entity
@Table(name="Addresses")
public class Address implements Serializable {

    @GeneratedValue
    @Id
    @Column(name = "ADDRESS_ID")
    private Long id;

    @Column(name = "ZIPCODE")
    private String zipCode;

    @Column(name = "CITY")
    private String city;

    @Column(name = "HOUSENUMBER")
    private String houseNumber;

    @Column(name = "FLOOR")
    private String floor;

    @Column(name = "DOOR")
    private String door;

    @OneToMany(mappedBy = "address")
    private List<Order> orders;

    private Address(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
