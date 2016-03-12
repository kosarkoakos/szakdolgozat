package com.szakdolgozat.ejbs;

import com.szakdolgozat.dto.NamePriceDTO;
import com.szakdolgozat.entities.Bill;
import com.szakdolgozat.entities.ServicePack;
import com.szakdolgozat.entities.person.ApplicationUser;
import com.szakdolgozat.entities.person.Customer;
import com.szakdolgozat.entities.service.Service;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 * Created by Ákos on 2016.02.28..
 */
@Stateful
public class TableContentHandlerBean {

    @PersistenceContext(unitName = "SZERPU")
    EntityManager entityManager;

    public IndexedContainer makeBasketIndexedConatiner(List<String> sNames, List<String> spNames){

        System.out.println("A makeBasketIndexedConatiner paramétereinek tartalma:");

        for(String n : sNames){
            System.out.println(n);
        }
        for(String n : spNames){
            System.out.println(n);
        }
        IndexedContainer ic= new IndexedContainer();

        ic.addContainerProperty("Név", String.class, null);
        ic.addContainerProperty("Ár", Integer.class,null);

        TypedQuery<NamePriceDTO> sq;
        String selectString="SELECT NEW com.szakdolgozat.dto.NamePriceDTO(s.name, s.price) FROM Service s" +
                " WHERE s.name IN :names";

        sq=entityManager.createQuery(selectString,NamePriceDTO.class);
        sq.setParameter("names", sNames);
        List<NamePriceDTO> sresult=new ArrayList<>();
        try{
            sresult= sq.getResultList();
        }catch (Exception e){
            System.out.println(selectString + " Hiba a query futtatásakor.");
        }



        TypedQuery<NamePriceDTO> spq;
        selectString="SELECT NEW com.szakdolgozat.dto.NamePriceDTO(s.name, s.price) FROM ServicePack s" +
                " WHERE s.name IN :names";

        spq=entityManager.createQuery(selectString,NamePriceDTO.class);
        spq.setParameter("names", spNames);
        List<NamePriceDTO> spresult= new ArrayList<>();
        try {
            spresult= spq.getResultList();
        }catch(Exception e){
            System.out.println(selectString + " Hiba a query futtatásakor.");
        }


        for(NamePriceDTO np : sresult){
            Item item= ic.addItem(np);
            item.getItemProperty("Név").setValue(np.getName());
            item.getItemProperty("Ár").setValue(np.getPrice());
        }

        for(NamePriceDTO np : spresult){
            Item item= ic.addItem(np);
            item.getItemProperty("Név").setValue(np.getName());
            item.getItemProperty("Ár").setValue(np.getPrice());
        }

        return ic;
    }

    public IndexedContainer makeBillsIndexedContainer(ApplicationUser customer){
        List<Bill> bills=null;

        TypedQuery<Bill> getAllBillsQuery;
        getAllBillsQuery=entityManager.createQuery("SELECT s FROM Bill s WHERE s.order.orderId IN (" +
                "SELECT o.orderId FROM Order o WHERE o.subscriber =:customer) AND s.paidTime IS NULL",Bill.class);
        getAllBillsQuery.setParameter("customer", (Customer)customer);
        try {
            bills = getAllBillsQuery.getResultList();
        }catch(Exception e){
            System.out.println("Nincsenek számlái.");
        }

        IndexedContainer ic= new IndexedContainer();
        ic.addContainerProperty("Szolgáltatás", String.class,null);
        ic.addContainerProperty("Összeg", Integer.class, null);
        ic.addContainerProperty("Befizetési határidő", Date.class ,null);

        for (Bill b : bills){
            Item item= ic.addItem(b);
            item.getItemProperty("Szolgáltatás").setValue(b.getBillName());
            item.getItemProperty("Összeg").setValue(b.getAmount());
            item.getItemProperty("Befizetési határidő").setValue(b.getDeadline());
        }

        return ic;
    }
}
