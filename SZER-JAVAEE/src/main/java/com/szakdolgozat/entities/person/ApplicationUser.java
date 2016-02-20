package com.szakdolgozat.entities.person;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ákos on 2016.02.20..
 */
@Entity
@Table(name="ApplicationUsers")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "USERTYPE")
public class ApplicationUser implements Serializable {

    @Id
    @GeneratedValue
    @Column(name="USER_ID")
    private Long userId;

    @Column(name="NAME")
    private String name;

    @Column(name="BIRTHDATE")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Column(name="BIRTHPLACE")
    private String birthPlace;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }
}
