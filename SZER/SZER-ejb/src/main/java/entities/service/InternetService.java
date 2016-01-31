/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.service;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Ákos
 */
@Entity
@Table(name="InternetServices")
public class InternetService extends Service implements Serializable {
    
    @Column(name="SPEED")
    private Short speed;
    
    public InternetService(){
        
    }

    public Short getSpeed() {
        return speed;
    }

    public void setSpeed(Short speed) {
        this.speed = speed;
    }
}
