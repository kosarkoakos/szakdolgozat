/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.service;

import entities.Order;
import entities.ServicePack;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Ákos
 */
@Entity
@Table(name="Services")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "SERVICETYPE")
public class Service implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name="SERVICE_ID")
    private Short serviceId;
    
    @Column(name="NAME")
    private String name;
    
    @Column(name="PRICE")
    private Integer price;
    
    @Column(name="DESCRIPTION")
    private String description;
    
    @ManyToMany(mappedBy = "parts")
    private List<ServicePack> containerServicePacks;
    
    @ManyToMany(mappedBy = "services")
    private List<Order> containerOrders;
    
    public Service(){
        
    }

    public Short getServiceId() {
        return serviceId;
    }

    public void setServiceId(Short serviceId) {
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
}
