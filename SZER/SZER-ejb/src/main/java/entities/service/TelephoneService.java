/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.service;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author Ákos
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
