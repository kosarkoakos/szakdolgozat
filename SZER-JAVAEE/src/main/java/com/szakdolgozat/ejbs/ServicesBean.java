package com.szakdolgozat.ejbs;

import com.szakdolgozat.entities.ServicePack;
import com.szakdolgozat.entities.service.InternetService;
import com.szakdolgozat.entities.service.TelephoneService;
import com.szakdolgozat.entities.service.TelevisionService;

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

    public Collection<TelevisionService> getAllTelevisionServices(){
        TypedQuery<TelevisionService> getAllISQury;
        getAllISQury = entityManager.createQuery("SELECT a FROM TelevisionService a",TelevisionService.class);

        return getAllISQury.getResultList();
    }

    public Collection<TelephoneService> getAllTelephoneServices(){
        TypedQuery<TelephoneService> getAllISQury;
        getAllISQury = entityManager.createQuery("SELECT a FROM TelephoneService a",TelephoneService.class);

        return getAllISQury.getResultList();
    }

    public Collection<ServicePack> getAllServicePacks(){
        TypedQuery<ServicePack> getAllISQury;
        getAllISQury = entityManager.createQuery("SELECT a FROM ServicePack a",ServicePack.class);

        return getAllISQury.getResultList();
    }
}
