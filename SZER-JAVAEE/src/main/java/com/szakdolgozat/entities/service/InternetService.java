package com.szakdolgozat.entities.service;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ákos on 2016.02.20..
 */
@Entity
@Table(name="InternetServices")
@Inheritance(strategy = InheritanceType.JOINED)
public class InternetService extends Service implements Serializable {
    @Column(name="SPEED")
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
