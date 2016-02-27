package com.szakdolgozat.ejbs;

import com.szakdolgozat.dto.LoginDTO;
import com.szakdolgozat.entities.person.ApplicationUser;
import com.szakdolgozat.entities.service.InternetService;
import com.szakdolgozat.entities.service.TelephoneService;
import com.szakdolgozat.entities.service.TelevisionService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Created by Ákos on 2016.02.27..
 */
@Stateless
public class ApplicationUserBean {

    @PersistenceContext(unitName = "SZERPU")
    EntityManager entityManager;

    public void doRegistration(ApplicationUser newUser){
        entityManager.persist(newUser);
    }

    public ApplicationUser doLogin(LoginDTO loginDTO){
        ApplicationUser userFromDB;

        TypedQuery<ApplicationUser> loginQuery;
        loginQuery = entityManager.createQuery("SELECT au FROM ApplicationUser au" +
                " WHERE au.username=:un AND au.password=:p", ApplicationUser.class);
        loginQuery.setParameter("un", loginDTO.getUsername());
        loginQuery.setParameter("p", loginDTO.getPassword());

        try{
            userFromDB = loginQuery.getSingleResult();
        }
        catch(Exception e){
            userFromDB = null;
        }

        return userFromDB;
    }

    public void dbfill(){

        TelephoneService ts1= new TelephoneService();
        TelephoneService ts2= new TelephoneService();
        TelephoneService ts3= new TelephoneService();


        ts1.setName("Kis telefon");
        ts1.setDescription("Kis telefon");
        ts1.setPrice(1500);
        ts1.setType("vezetékes");

        ts2.setName("Közepes telefon");
        ts2.setDescription("Közepes telefon");
        ts2.setPrice(3000);
        ts2.setType("vezetékes");

        ts3.setName("Nagy telefon");
        ts3.setDescription("Nagy telefon");
        ts3.setPrice(4000);
        ts3.setType("mobil");

        TelevisionService televisionService1= new TelevisionService();
        TelevisionService televisionService2= new TelevisionService();
        TelevisionService televisionService3= new TelevisionService();

        televisionService1.setName("Kis televízió");
        televisionService1.setDescription("Kis televízió");
        televisionService1.setPrice(3000);
        televisionService1.setChannelCount((byte)8);

        televisionService2.setName("Közepes televízió");
        televisionService2.setDescription("Közepes televízió");
        televisionService2.setPrice(4000);
        televisionService2.setChannelCount((byte)25);

        televisionService3.setName("Nagy televízió");
        televisionService3.setDescription("Nagy televízió");
        televisionService3.setPrice(5500);
        televisionService3.setChannelCount((byte)70);

        entityManager.persist(ts1);
        entityManager.persist(ts2);
        entityManager.persist(ts3);
        entityManager.persist(televisionService1);
        entityManager.persist(televisionService2);
        entityManager.persist(televisionService3);


    }
}
