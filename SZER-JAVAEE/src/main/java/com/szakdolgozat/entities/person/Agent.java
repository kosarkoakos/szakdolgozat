package com.szakdolgozat.entities.person;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ákos on 2016.02.20..
 */
@Entity
@Table(name="Agents")
@Inheritance(strategy = InheritanceType.JOINED)
public class Agent extends ApplicationUser implements Serializable {

    @Column(name="ROLE")
    private String role;  //????? új tábla a jogokkal?

    public Agent(){

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
