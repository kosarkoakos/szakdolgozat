package com.szakdolgozat.entities.person;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ákos on 2016.02.20..
 */
@Entity
@Table(name="APPLICATIONUSERS")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "USERTYPE")
public class ApplicationUser implements Serializable {

    @Id
    @GeneratedValue
    @Column(name="USER_ID")
    private Long userId;

    @Column(name="USERNAME", nullable = false, length = 99)
    private String username;

    @Column(name="PASSWORD", nullable = false, length = 99)
    private String password;

    @Column(name="NAME", nullable = false, length = 99)
    private String name;

    @Column(name="BIRTHDATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Column(name="BIRTHPLACE", nullable = false, length = 99)
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
