package com.szakdolgozat.entities;

import com.szakdolgozat.entities.service.Service;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Ákos on 2016.02.20..
 */
@Entity
@Table(name="SERVICEPACKS")
public class ServicePack implements Serializable{
    @Id
    @GeneratedValue
    @Column(name="SERVICEPACK_ID")
    private Short servicePackId;

    @Column(name="NAME", nullable = false, length = 99)
    private String name;

    @Column(name="PRICE",nullable = false)
    private Integer price;

    @Column(name="DESCRIPTION", nullable = false, length = 99)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "JND_SERVICEPACKS_SERVICES",
            joinColumns = @JoinColumn(name = "SERVICEPACK_ID_FK"),
            inverseJoinColumns = @JoinColumn(name = "SERVICE_ID_FK"))
    private List<Service> parts;

    @ManyToMany(mappedBy = "servicePacks")
    private List<Order> containerOrders;

    @Column(name = "LOYALTY")
    private int loyalty;

    public ServicePack(){

    }

    public Short getServicePackId() {
        return servicePackId;
    }

    public void setServicePackId(Short servicePackId) {
        this.servicePackId = servicePackId;
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

    public List<Service> getParts() {
        return parts;
    }

    public void setParts(List<Service> parts) {
        this.parts = parts;
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
