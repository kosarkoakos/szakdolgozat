/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.person.Customer;
import entities.service.Service;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Ákos
 */
@Entity
@Table(name="Orders")
public class Order implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name="ORDER_ID")
    private Long orderId;
    
    @Column(name="ORDERDATE")
    private Date orderDate;    
    
    @ManyToMany
    @JoinTable(name = "jnd_Orders_ServicePacks",
            joinColumns = @JoinColumn(name = "ORDER_ID_FK"),
            inverseJoinColumns = @JoinColumn(name = "SERVICEPACK_ID_FK"))
    private List<ServicePack> servicePacks;
    
    @ManyToMany
    @JoinTable(name = "jnd_Orders_Services",
            joinColumns = @JoinColumn(name = "ORDER_ID_FK"),
            inverseJoinColumns = @JoinColumn(name = "SERVICE_ID_FK"))
    private List<Service> services;
    
    @ManyToOne
    @JoinColumn(name = "PERSON_ID_FK", referencedColumnName = "personId")
    private Customer subscriber;
    
    @OneToMany(mappedBy="order")
    private List<Bill> bills;
    
    @ManyToMany
    @JoinTable(name = "jnd_Orders_Extras",
            joinColumns = @JoinColumn(name = "ORDER_ID_FK"),
            inverseJoinColumns = @JoinColumn(name = "EXTRA_ID_FK"))
    private List<Extra> extras;
}
