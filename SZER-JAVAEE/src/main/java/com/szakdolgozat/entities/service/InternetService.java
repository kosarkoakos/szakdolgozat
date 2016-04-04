package com.szakdolgozat.entities.service;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ákos on 2016.02.20..
 */
@Entity
@Table(name="INTERNETSERVICES")
@Inheritance(strategy = InheritanceType.JOINED)
public class InternetService extends Service implements Serializable {
    @Column(name="SPEED", nullable = false, length = 19)
    private String speed;

    public InternetService(){

    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
}
