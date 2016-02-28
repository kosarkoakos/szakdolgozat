package com.szakdolgozat.ejbs;

import com.szakdolgozat.dto.LoginDTO;
import com.szakdolgozat.entities.ServicePack;
import com.szakdolgozat.entities.person.ApplicationUser;
import com.szakdolgozat.entities.service.InternetService;
import com.szakdolgozat.entities.service.Service;
import com.szakdolgozat.entities.service.TelephoneService;
import com.szakdolgozat.entities.service.TelevisionService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

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
/*
        InternetService is1= new InternetService();
        InternetService is2= new InternetService();
        InternetService is3= new InternetService();

        is1.setName("Kis internet");
        is1.setDescription("Kis internet");
        is1.setPrice(3000);
        is1.setSpeed("10/1");

        is2.setName("Közepes internet");
        is2.setDescription("Közepes internet");
        is2.setPrice(4000);
        is2.setSpeed("50/5");

        is3.setName("Nagy internet");
        is3.setDescription("Nagy internet");
        is3.setPrice(5000);
        is3.setSpeed("100/10");


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

        ServicePack sp1=new ServicePack();
        ServicePack sp2=new ServicePack();
        ServicePack sp3=new ServicePack();

        sp1.setName("Kis csomag");
        sp1.setDescription("Kis csomag");
        sp1.setPrice((int)((is1.getPrice()+ts1.getPrice()+televisionService1.getPrice())*0.8));

        ArrayList<Service> sp1Services= new ArrayList<>();

        sp1Services.add(ts1);
        sp1Services.add(televisionService1);
        sp1Services.add(is1);

        sp1.setParts(sp1Services);

        sp2.setName("Közepes csomag");
        sp2.setDescription("Közepes csomag");
        sp2.setPrice((int)((is2.getPrice()+ts2.getPrice()+televisionService2.getPrice())*0.8));

        ArrayList<Service> sp2Services= new ArrayList<>();

        sp2Services.add(ts2);
        sp2Services.add(televisionService2);
        sp2Services.add(is2);

        sp2.setParts(sp2Services);

        sp3.setName("Nagy csomag");
        sp3.setDescription("Nagy csomag");
        sp3.setPrice((int)((is3.getPrice()+ts3.getPrice()+televisionService3.getPrice())*0.8));

        ArrayList<Service> sp3Services= new ArrayList<>();

        sp3Services.add(ts3);
        sp3Services.add(televisionService3);
        sp3Services.add(is3);

        sp3.setParts(sp3Services);

        entityManager.persist(is1);
        entityManager.persist(is2);
        entityManager.persist(is3);
        entityManager.persist(ts1);
        entityManager.persist(ts2);
        entityManager.persist(ts3);
        entityManager.persist(televisionService1);
        entityManager.persist(televisionService2);
        entityManager.persist(televisionService3);
        entityManager.persist(sp1);
        entityManager.persist(sp2);
        entityManager.persist(sp3);

*/


    }
}
