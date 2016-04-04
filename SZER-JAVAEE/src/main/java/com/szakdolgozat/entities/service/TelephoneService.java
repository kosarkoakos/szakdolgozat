package com.szakdolgozat.entities.service;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ákos on 2016.02.20..
 */
@Entity
@Table(name="TELEPHONESERVICES")
@Inheritance(strategy = InheritanceType.JOINED)
public class TelephoneService extends Service implements Serializable {
    @Column(name="TYPE", nullable = false, length = 19)
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
