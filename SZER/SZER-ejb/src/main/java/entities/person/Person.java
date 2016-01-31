/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.person;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ákos
 */
@Entity
@Table(name="People")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "PERSONTYPE")
public class Person implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name="PERSON_ID")
    private Long personId;
    
    @Column(name="NAME")
    private String name;
    
    @Column(name="BIRTHDATE")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    
    @Column(name="BIRTHPLACE")
    private String birthPlace;
}
