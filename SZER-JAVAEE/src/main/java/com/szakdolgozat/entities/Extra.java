package com.szakdolgozat.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Ákos on 2016.02.20..
 */
@Entity
@Table(name="Extras")
public class Extra implements Serializable{

    @Id
    @GeneratedValue
    @Column(name="EXTRA_ID")
    private Short extraId;

    @Column(name="NAME")
    private String name;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="CONTENT")
    private String content;

    @Column(name="COUNT")
    private Short count;

    @ManyToMany(mappedBy="extras")
    private List<Order> orders;

    public Extra(){

    }

    public Short getExtraId() {
        return extraId;
    }

    public void setExtraId(Short extraId) {
        this.extraId = extraId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Short getCount() {
        return count;
    }

    public void setCount(Short count) {
        this.count = count;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
