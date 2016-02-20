package com.szakdolgozat.entities.person;

import com.szakdolgozat.entities.Order;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Ákos on 2016.02.20..
 */
@Entity
@Table(name="Customers")
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends ApplicationUser implements Serializable {

    @Column(name="REGISTRATIONDATE")
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @OneToMany(mappedBy="subscriber")
    private List<Order> orders;

    public Customer(){

    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
