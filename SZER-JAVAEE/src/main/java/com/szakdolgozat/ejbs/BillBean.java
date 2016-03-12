package com.szakdolgozat.ejbs;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;

/**
 * Created by Ákos on 2016.03.12..
 */
@Stateful
public class BillBean {

    @PersistenceContext(unitName = "SZERPU")
    EntityManager entityManager;

    @Inject
    IDStorageBean idStorageBean;

    public void makeBillsPayed(String customerName){
        ArrayList<Long> ids=idStorageBean.getIDListOfCustomer(customerName);

        Query updateQuery = entityManager.createQuery("UPDATE Bill b SET b.paidTime=:date " +
                "WHERE b.billId IN :ids ");
        updateQuery.setParameter("date", java.util.Calendar.getInstance().getTime());
        updateQuery.setParameter("ids", ids);

        updateQuery.executeUpdate();

        idStorageBean.removeList(customerName);
    }
}
