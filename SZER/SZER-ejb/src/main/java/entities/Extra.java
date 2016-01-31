/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Ákos
 */
@Entity
@Table(name="Extras")
public class Extra implements Serializable {
    
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
}
