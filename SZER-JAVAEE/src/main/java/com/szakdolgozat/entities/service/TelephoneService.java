package com.szakdolgozat.entities.service;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ákos on 2016.02.20..
 */
@Entity
@Table(name="TelephoneServices")
@Inheritance(strategy = InheritanceType.JOINED)
public class TelephoneService extends Service implements Serializable {
    @Column(name="TYPE")
    private String type;

    public TelephoneService(){

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
