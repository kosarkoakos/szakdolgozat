package com.szakdolgozat.entities.person;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ákos on 2016.02.20..
 */
@Entity
@Table(name="AGENTS")
@Inheritance(strategy = InheritanceType.JOINED)
public class Agent extends ApplicationUser implements Serializable {

    @Column(name="AGENTLEVEL", nullable = false, length = 19)
    private String agentLevel;

    public Agent(){
    }

    public String getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(String agentLevel) {
        this.agentLevel = agentLevel;
    }
}
