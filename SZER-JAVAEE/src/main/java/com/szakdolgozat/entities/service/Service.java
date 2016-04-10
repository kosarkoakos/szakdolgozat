package com.szakdolgozat.entities.service;

import com.szakdolgozat.entities.Order;
import com.szakdolgozat.entities.ServicePack;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Ákos on 2016.02.20..
 */
@Entity
@Table(name="SERVICES" )
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "SERVICETYPE")
public class Service implements Serializable{
    @Id
    @GeneratedValue
    @Column(name="SERVICE_ID")
    private Long serviceId;

    @Column(name="NAME", nullable = false, length = 99)
    private String name;

    @Column(name="PRICE", nullable = false)
    private Integer price;

    @Column(name="DESCRIPTION", nullable = false, length = 255)
    private String description;

    @ManyToMany(mappedBy = "parts")
    private List<ServicePack> containerServicePacks;

    @ManyToMany(mappedBy = "services")
    private List<Order> containerOrders;

    @Column(name="LOYALTY", nullable = false)
    private int loyalty;

    public Service(){

    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ServicePack> getContainerServicePacks() {
        return containerServicePacks;
    }

    public void setContainerServicePacks(List<ServicePack> containerServicePacks) {
        this.containerServicePacks = containerServicePacks;
    }

    public List<Order> getContainerOrders() {
        return containerOrders;
    }

    public void setContainerOrders(List<Order> containerOrders) {
        this.containerOrders = containerOrders;
    }

    public int getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(int loyalty) {
        this.loyalty = loyalty;
    }
}
