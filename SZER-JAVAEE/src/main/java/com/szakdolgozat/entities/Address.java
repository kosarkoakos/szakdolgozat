package com.szakdolgozat.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ákos on 2016.03.21..
 */
@Entity
@Table(name="ADDRESSES")
public class Address implements Serializable {

    @GeneratedValue
    @Id
    @Column(name = "ADDRESS_ID")
    private Long id;

    @Column(name = "ZIPCODE", nullable = false, length = 4)
    private String zipCode;

    @Column(name = "CITY", nullable = false, length = 99)
    private String city;

    @Column(name = "STREET", nullable = false, length = 99)
    private String street;

    @Column(name = "HOUSENUMBER", nullable = false, length = 19)
    private String houseNumber;

    @Column(name = "FLOOR", nullable = true, length = 19)
    private String floor;

    @Column(name = "DOOR", nullable = true, length = 19)
    private String door;

    @OneToMany(mappedBy = "address",cascade = CascadeType.ALL)
    private List<Order> orders;

    public Address(){
        orders=new ArrayList<>();
    }

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


}
