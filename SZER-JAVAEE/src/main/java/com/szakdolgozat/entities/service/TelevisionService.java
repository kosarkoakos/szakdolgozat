package com.szakdolgozat.entities.service;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ákos on 2016.02.20..
 */
@Entity
@Table(name="TelevisionServices")
@Inheritance(strategy = InheritanceType.JOINED)
public class TelevisionService extends Service implements Serializable {

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
