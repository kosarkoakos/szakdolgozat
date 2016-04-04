package com.szakdolgozat.ejbs;

import com.szakdolgozat.dto.LoginDTO;
import com.szakdolgozat.entities.Address;
import com.szakdolgozat.entities.ServicePack;
import com.szakdolgozat.entities.person.Agent;
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

        InternetService is1= new InternetService();
        InternetService is2= new InternetService();
        InternetService is3= new InternetService();
        InternetService is4= new InternetService();
        InternetService is5= new InternetService();
        InternetService is6= new InternetService();
        InternetService is7= new InternetService();
        InternetService is8= new InternetService();
        InternetService is9= new InternetService();

        is1.setName("Kis internet - 1 év");
        is1.setDescription("Kis internet");
        is1.setLoyalty(1);
        is1.setPrice(4500);
        is1.setSpeed("10M/1M");

        is2.setName("Közepes internet - 1 év");
        is2.setDescription("Közepes internet");
        is2.setPrice(6000);
        is2.setLoyalty(1);
        is2.setSpeed("50M/5M");

        is3.setName("Nagy internet - 1 év");
        is3.setDescription("Nagy internet");
        is3.setPrice(7000);
        is3.setLoyalty(1);
        is3.setSpeed("100M/10M");

        is4.setName("Kis internet - 2 év");
        is4.setDescription("Kis internet");
        is4.setLoyalty(2);
        is4.setPrice(3500);
        is4.setSpeed("10M/1M");

        is5.setName("Közepes internet - 2 év");
        is5.setDescription("Közepes internet");
        is5.setPrice(5000);
        is5.setLoyalty(2);
        is5.setSpeed("50M/5M");

        is6.setName("Nagy internet - 2 év");
        is6.setDescription("Nagy internet");
        is6.setPrice(5500);
        is6.setLoyalty(2);
        is6.setSpeed("100M/10M");

        is7.setName("Kis internet - h. n.");
        is7.setDescription("Kis internet");
        is7.setLoyalty(0);
        is7.setPrice(5500);
        is7.setSpeed("10M/1M");

        is8.setName("Közepes internet - h. n.");
        is8.setDescription("Közepes internet");
        is8.setPrice(7000);
        is8.setLoyalty(0);
        is8.setSpeed("50M/5M");

        is9.setName("Nagy internet - h. n.");
        is9.setDescription("Nagy internet");
        is9.setPrice(8000);
        is9.setLoyalty(0);
        is9.setSpeed("100M/10M");


        TelephoneService ts1= new TelephoneService();
        TelephoneService ts2= new TelephoneService();
        TelephoneService ts3= new TelephoneService();
        TelephoneService ts4= new TelephoneService();
        TelephoneService ts5= new TelephoneService();
        TelephoneService ts6= new TelephoneService();
        TelephoneService ts7= new TelephoneService();
        TelephoneService ts8= new TelephoneService();
        TelephoneService ts9= new TelephoneService();


        ts1.setName("Kis telefon - 1 év");
        ts1.setDescription("Kis telefon");
        ts1.setLoyalty(1);
        ts1.setPrice(2000);
        ts1.setType("digitális");

        ts2.setName("Közepes telefon - 1 év");
        ts2.setDescription("Közepes telefon");
        ts2.setPrice(3500);
        ts2.setLoyalty(1);
        ts2.setType("digitális");

        ts3.setName("Nagy telefon - 1 év");
        ts3.setDescription("Nagy telefon");
        ts3.setLoyalty(1);
        ts3.setPrice(4500);
        ts3.setType("digitális");

        ts4.setName("Kis telefon - 2 év");
        ts4.setDescription("Kis telefon");
        ts4.setLoyalty(2);
        ts4.setPrice(1500);
        ts4.setType("digitális");

        ts5.setName("Közepes telefon - 2 év");
        ts5.setDescription("Közepes telefon");
        ts5.setPrice(3000);
        ts5.setLoyalty(2);
        ts5.setType("digitális");

        ts6.setName("Nagy telefon - 2 év");
        ts6.setDescription("Nagy telefon");
        ts6.setLoyalty(2);
        ts6.setPrice(4000);
        ts6.setType("digitális");

        ts7.setName("Kis telefon - h. n.");
        ts7.setDescription("Kis telefon");
        ts7.setLoyalty(0);
        ts7.setPrice(2500);
        ts7.setType("digitális");

        ts8.setName("Közepes telefon - h. n.");
        ts8.setDescription("Közepes telefon");
        ts8.setPrice(4000);
        ts8.setLoyalty(0);
        ts8.setType("digitális");

        ts9.setName("Nagy telefon - h. n.");
        ts9.setDescription("Nagy telefon");
        ts9.setLoyalty(0);
        ts9.setPrice(5000);
        ts9.setType("digitális");

        TelevisionService televisionService1= new TelevisionService();
        TelevisionService televisionService2= new TelevisionService();
        TelevisionService televisionService3= new TelevisionService();
        TelevisionService televisionService4= new TelevisionService();
        TelevisionService televisionService5= new TelevisionService();
        TelevisionService televisionService6= new TelevisionService();
        TelevisionService televisionService7= new TelevisionService();
        TelevisionService televisionService8= new TelevisionService();
        TelevisionService televisionService9= new TelevisionService();

        televisionService1.setName("Kis televízió - 1 év");
        televisionService1.setDescription("Kis televízió");
        televisionService1.setPrice(3500);
        televisionService1.setLoyalty(1);
        televisionService1.setChannelCount((byte)8);

        televisionService2.setName("Közepes televízió - 1 év");
        televisionService2.setDescription("Közepes televízió");
        televisionService2.setPrice(4500);
        televisionService2.setLoyalty(1);
        televisionService2.setChannelCount((byte)25);

        televisionService3.setName("Nagy televízió - 1 év");
        televisionService3.setDescription("Nagy televízió");
        televisionService3.setPrice(6000);
        televisionService3.setLoyalty(1);
        televisionService3.setChannelCount((byte)70);

        televisionService4.setName("Kis televízió - 2 év");
        televisionService4.setDescription("Kis televízió");
        televisionService4.setPrice(3000);
        televisionService4.setLoyalty(2);
        televisionService4.setChannelCount((byte)8);

        televisionService5.setName("Közepes televízió - 2 év");
        televisionService5.setDescription("Közepes televízió");
        televisionService5.setPrice(4000);
        televisionService5.setLoyalty(2);
        televisionService5.setChannelCount((byte)25);

        televisionService6.setName("Nagy televízió - 2 év");
        televisionService6.setDescription("Nagy televízió");
        televisionService6.setPrice(5500);
        televisionService6.setLoyalty(2);
        televisionService6.setChannelCount((byte)70);

        televisionService7.setName("Kis televízió - h. n.");
        televisionService7.setDescription("Kis televízió");
        televisionService7.setPrice(4000);
        televisionService7.setLoyalty(0);
        televisionService7.setChannelCount((byte)8);

        televisionService8.setName("Közepes televízió - h. n.");
        televisionService8.setDescription("Közepes televízió");
        televisionService8.setPrice(5000);
        televisionService8.setLoyalty(0);
        televisionService8.setChannelCount((byte)25);

        televisionService9.setName("Nagy televízió - h. n.");
        televisionService9.setDescription("Nagy televízió");
        televisionService9.setPrice(6500);
        televisionService9.setLoyalty(0);
        televisionService9.setChannelCount((byte)70);

        ServicePack sp1=new ServicePack();
        ServicePack sp2=new ServicePack();
        ServicePack sp3=new ServicePack();
        ServicePack sp4=new ServicePack();
        ServicePack sp5=new ServicePack();
        ServicePack sp6=new ServicePack();
        ServicePack sp7=new ServicePack();
        ServicePack sp8=new ServicePack();
        ServicePack sp9=new ServicePack();

        sp1.setName("Kis csomag - 1 év");
        sp1.setDescription("Kis csomag");
        sp1.setLoyalty(1);
        sp1.setPrice((int)((is1.getPrice()+ts1.getPrice()+televisionService1.getPrice())*0.8));

        ArrayList<Service> sp1Services= new ArrayList<>();

        sp1Services.add(ts1);
        sp1Services.add(televisionService1);
        sp1Services.add(is1);

        sp1.setParts(sp1Services);

        sp2.setName("Közepes csomag - 1 év");
        sp2.setLoyalty(1);
        sp2.setDescription("Közepes csomag");
        sp2.setPrice((int)((is2.getPrice()+ts2.getPrice()+televisionService2.getPrice())*0.8));

        ArrayList<Service> sp2Services= new ArrayList<>();

        sp2Services.add(ts2);
        sp2Services.add(televisionService2);
        sp2Services.add(is2);

        sp2.setParts(sp2Services);

        sp3.setName("Nagy csomag - 1 év");
        sp3.setLoyalty(1);
        sp3.setDescription("Nagy csomag");
        sp3.setPrice((int)((is3.getPrice()+ts3.getPrice()+televisionService3.getPrice())*0.8));

        ArrayList<Service> sp3Services= new ArrayList<>();

        sp3Services.add(ts3);
        sp3Services.add(televisionService3);
        sp3Services.add(is3);

        sp3.setParts(sp3Services);



        sp4.setName("Kis csomag - 2 év");
        sp4.setLoyalty(2);
        sp4.setDescription("Kis csomag");
        sp4.setPrice((int)((is4.getPrice()+ts4.getPrice()+televisionService4.getPrice())*0.8));

        ArrayList<Service> sp4Services= new ArrayList<>();

        sp4Services.add(ts4);
        sp4Services.add(televisionService4);
        sp4Services.add(is4);

        sp4.setParts(sp1Services);

        sp5.setName("Közepes csomag - 2 év");
        sp5.setLoyalty(2);
        sp5.setDescription("Közepes csomag");
        sp5.setPrice((int)((is5.getPrice()+ts5.getPrice()+televisionService5.getPrice())*0.8));

        ArrayList<Service> sp5Services= new ArrayList<>();

        sp5Services.add(ts5);
        sp5Services.add(televisionService5);
        sp5Services.add(is5);

        sp5.setParts(sp5Services);

        sp6.setName("Nagy csomag - 2 év");
        sp6.setLoyalty(2);
        sp6.setDescription("Nagy csomag");
        sp6.setPrice((int)((is6.getPrice()+ts6.getPrice()+televisionService6.getPrice())*0.8));

        ArrayList<Service> sp6Services= new ArrayList<>();

        sp6Services.add(ts6);
        sp6Services.add(televisionService6);
        sp6Services.add(is6);

        sp6.setParts(sp6Services);


        sp7.setName("Kis csomag - h. n.");
        sp7.setLoyalty(0);
        sp7.setDescription("Kis csomag");
        sp7.setPrice((int)((is7.getPrice()+ts7.getPrice()+televisionService7.getPrice())*0.8));

        ArrayList<Service> sp7Services= new ArrayList<>();

        sp7Services.add(ts7);
        sp7Services.add(televisionService7);
        sp7Services.add(is7);

        sp7.setParts(sp7Services);

        sp8.setName("Közepes csomag - h. n.");
        sp8.setLoyalty(0);
        sp8.setDescription("Közepes csomag");
        sp8.setPrice((int)((is8.getPrice()+ts8.getPrice()+televisionService8.getPrice())*0.8));

        ArrayList<Service> sp8Services= new ArrayList<>();

        sp8Services.add(ts8);
        sp8Services.add(televisionService8);
        sp8Services.add(is8);

        sp8.setParts(sp8Services);

        sp9.setName("Nagy csomag - h. n.");
        sp9.setLoyalty(0);
        sp9.setDescription("Nagy csomag");
        sp9.setPrice((int)((is9.getPrice()+ts9.getPrice()+televisionService9.getPrice())*0.8));

        ArrayList<Service> sp9Services= new ArrayList<>();

        sp9Services.add(ts9);
        sp9Services.add(televisionService9);
        sp9Services.add(is9);

        sp9.setParts(sp9Services);

        Agent agent= new Agent();
        agent.setUsername("agent");
        agent.setPassword("agent");
        agent.setBirthPlace("Budapest");
        agent.setBirthdate(java.util.Calendar.getInstance().getTime());
        agent.setAgentLevel("level max");
        agent.setName("Kiss Laura");

        entityManager.persist(agent);




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

        entityManager.persist(is4);
        entityManager.persist(is5);
        entityManager.persist(is6);
        entityManager.persist(ts4);
        entityManager.persist(ts5);
        entityManager.persist(ts6);
        entityManager.persist(televisionService4);
        entityManager.persist(televisionService5);
        entityManager.persist(televisionService6);
        entityManager.persist(sp4);
        entityManager.persist(sp5);
        entityManager.persist(sp6);

        entityManager.persist(is7);
        entityManager.persist(is8);
        entityManager.persist(is9);
        entityManager.persist(ts7);
        entityManager.persist(ts8);
        entityManager.persist(ts9);
        entityManager.persist(televisionService7);
        entityManager.persist(televisionService8);
        entityManager.persist(televisionService9);
        entityManager.persist(sp7);
        entityManager.persist(sp8);
        entityManager.persist(sp9);




    }
}
