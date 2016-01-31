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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Ákos
 */
@Entity
@Table(name="Services")
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
}
