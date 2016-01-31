/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.person;

import entities.person.Person;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Ákos
 */
@Entity
@Table(name="Employees")
public class Employee extends Person implements Serializable{
    
    @Column(name="ROLE")
    private String role;
}
