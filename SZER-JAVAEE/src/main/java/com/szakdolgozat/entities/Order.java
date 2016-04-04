package com.szakdolgozat.entities;

import com.szakdolgozat.entities.person.Customer;
import com.szakdolgozat.entities.service.Service;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Ákos on 2016.02.20..
 */
@Entity
@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue
    @Column(name="ORDER_ID")
    private Long orderId;

    @Column(name="ORDERDATE")
    private Date orderDate;

    @ManyToMany
    @JoinTable(name = "JND_ORDERS_SERVICEPACKS",
            joinColumns = @JoinColumn(name = "ORDER_ID_FK"),
            inverseJoinColumns = @JoinColumn(name = "SERVICEPACK_ID_FK"))
    private List<ServicePack> servicePacks;

    @ManyToMany
    @JoinTable(name = "JND_ORDERS_SERVICES",
            joinColumns = @JoinColumn(name = "ORDER_ID_FK"),
            inverseJoinColumns = @JoinColumn(name = "SERVICE_ID_FK"))
    private List<Service> services;

    @ManyToOne
    @JoinColumn(name = "USER_ID_FK", referencedColumnName = "USER_ID")
    private Customer subscriber;

    @OneToMany(mappedBy="order",cascade = CascadeType.ALL)
    private List<Bill> bills;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID_FK", referencedColumnName = "ADDRESS_ID")
    private Address address;

    public Order(){

    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<ServicePack> getServicePacks() {
        return servicePacks;
    }

    public void setServicePacks(List<ServicePack> servicePacks) {
        this.servicePacks = servicePacks;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Customer getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Customer subscriber) {
        this.subscriber = subscriber;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


}
