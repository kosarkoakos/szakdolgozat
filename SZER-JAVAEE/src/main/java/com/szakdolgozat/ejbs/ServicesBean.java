package com.szakdolgozat.ejbs;

import com.szakdolgozat.entities.service.InternetService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;

/**
 * Created by Ákos on 2016.02.27..
 */
@Stateless
public class ServicesBean {

    @PersistenceContext(unitName = "SZERPU")
    EntityManager entityManager;

    public Collection<InternetService> getAllInternetServices(){
        TypedQuery<InternetService> getAllISQury;
        getAllISQury = entityManager.createQuery("SELECT a FROM InternetService a",InternetService.class);

        return getAllISQury.getResultList();
    }
}
