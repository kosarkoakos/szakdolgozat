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
@Table(name="TelevisionServices")
public class TelevisionService extends Service implements Serializable{
    
    @Column(name="CHANNEL_COUNT")
    private Byte channelCount;
    
    public TelevisionService(){
        
    }

    public Byte getChannelCount() {
        return channelCount;
    }

    public void setChannelCount(Byte channelCount) {
        this.channelCount = channelCount;
    }
}
