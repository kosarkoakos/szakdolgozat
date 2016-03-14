package com.szakdolgozat.ejbs;

import com.szakdolgozat.entities.ReportedFault;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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

    public ReportedFault getFaultById(Long id){
        TypedQuery<ReportedFault> query;
        query = entityManager.createQuery("SELECT r FROM ReportedFault r WHERE r.id=:id",ReportedFault.class);
        query.setParameter("id",id);

        return query.getSingleResult();
    }

    public void makeFaultSolved(Long id, String solution){
        System.out.println();
        Query query=entityManager.createQuery("UPDATE ReportedFault r SET r.solution=:solution, r.solvedDate=:date " +
                "WHERE r.id=:id");
        query.setParameter("solution",solution);
        query.setParameter("date",java.util.Calendar.getInstance().getTime());
        query.setParameter("id",id);

        query.executeUpdate();
    }
}
