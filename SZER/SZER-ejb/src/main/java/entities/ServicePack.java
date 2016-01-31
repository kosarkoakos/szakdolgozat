/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.service.Service;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Ákos
 */
@Entity
@Table(name="ServicePacks")
public class ServicePack implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name="SERVICEPACK_ID")
    private Short servicePackId;
    
    @Column(name="NAME")
    private String name;
    
    @Column(name="PRICE")
    private Integer price;
    
    @Column(name="DESCRIPTION")
    private String description;
    
    @ManyToMany
    @JoinTable(name = "jnd_ServicePacks_Services",
            joinColumns = @JoinColumn(name = "SERVICEPACK_ID_FK"),
            inverseJoinColumns = @JoinColumn(name = "SERVICE_ID_FK"))
    private List<Service> parts;
    
    @ManyToMany(mappedBy = "servicePacks")
    private List<Order> containerOrders;
    
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
}
