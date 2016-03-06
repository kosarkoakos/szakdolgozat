package com.szakdolgozat.ejbs;

import com.szakdolgozat.entities.ReportedFault;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Ákos on 2016.03.06..
 */
@Stateful
public class FaultBean {

    @PersistenceContext(unitName = "SZERPU")
    EntityManager entityManager;

    public void saveReportedFault(ReportedFault reportedFault){
        entityManager.persist(reportedFault);
    }
}
