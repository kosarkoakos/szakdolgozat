package com.szakdolgozat.ejbs;

import com.szakdolgozat.entities.Address;
import com.szakdolgozat.entities.ServicePack;
import com.szakdolgozat.entities.service.InternetService;
import com.szakdolgozat.entities.service.TelephoneService;
import com.szakdolgozat.entities.service.TelevisionService;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import java.util.Collection;

/**
 * Created by Ákos on 2016.02.27..
 */
@Stateless
public class ServicesBean {

    @PersistenceContext(unitName = "SZERPU",type= PersistenceContextType.TRANSACTION)
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

    public Address lookUpForAddress(Address address){
        TypedQuery<Address> query;
        query=entityManager.createQuery("SELECT a FROM Address a " +
                "WHERE a.zipCode=:z AND a.city=:c AND a.street=:s AND a.houseNumber=:h" +
                " AND a.floor=:f AND a.door=:d", Address.class);


        Address a=new Address();
        a.setZipCode("teszt");
/*        a.setCity(address.getCity());
        a.setStreet(address.getStreet());
        a.setHouseNumber(address.getHouseNumber());
        a.setFloor(address.getFloor());
        a.setDoor(address.getDoor());*/
        query.setParameter("z", a.getZipCode());
        query.setParameter("c", a.getCity());
        query.setParameter("s", a.getStreet());
        query.setParameter("h", a.getHouseNumber());
        query.setParameter("f", a.getFloor());
        query.setParameter("d", a.getDoor());

        Address addressFromDB=null;



        if(addressFromDB != null){
            return addressFromDB;
        }
       // try {

            entityManager.persist(a);

           // addressFromDB = query.getSingleResult();
      //  }catch (Exception e){
      //      System.out.println("Hiba a servicesBean lookUpForAddress-ében.");
      //  }


        return addressFromDB;
    }

}
