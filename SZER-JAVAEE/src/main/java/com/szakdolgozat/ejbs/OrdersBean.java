package com.szakdolgozat.ejbs;

import com.szakdolgozat.entities.Address;
import com.szakdolgozat.entities.Bill;
import com.szakdolgozat.entities.Order;
import com.szakdolgozat.entities.ServicePack;
import com.szakdolgozat.entities.person.ApplicationUser;
import com.szakdolgozat.entities.person.Customer;
import com.szakdolgozat.entities.service.Service;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.UserTransaction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * Created by Ákos on 2016.03.06..
 */
@Stateless
public class OrdersBean {

    @PersistenceContext(unitName = "SZERPU",type= PersistenceContextType.TRANSACTION)
    EntityManager entityManager;

    @Inject
    DateFormatConverter dfc;

    @Inject
    ServicesBean servicesBean;



    public Collection<Service> getServicesByName(List<String> serviceNames){
        TypedQuery<Service> services=entityManager.createQuery("SELECT s FROM Service s WHERE s.name IN :names", Service.class);
        services.setParameter("names", serviceNames);

        Collection<Service> result = services.getResultList();

        return result;
    }

    public Collection<ServicePack> getServicePacksByName(List<String> servicePackNames){
        TypedQuery<ServicePack> servicePacks=entityManager.createQuery("SELECT s FROM ServicePack s WHERE s.name IN :names", ServicePack.class);
        servicePacks.setParameter("names", servicePackNames);

        Collection<ServicePack> result = servicePacks.getResultList();

        return result;
    }

    public void saveOrder(ApplicationUser user, List<String> serviceNames, List<String> servicePackNames, Address address){
        Order order= new Order();
        ArrayList<Service> orderedServices=null;
        ArrayList<ServicePack> orderedServicePacks=null;
        ArrayList<Bill> bills= new ArrayList<>();

        ArrayList<Order> orderList= new ArrayList<>();
/*
        Address newAddress= new Address();
        newAddress.setZipCode(address.getZipCode());
        newAddress.setCity(address.getCity());
        newAddress.setStreet(address.getStreet());
        newAddress.setHouseNumber(address.getHouseNumber());
        newAddress.setFloor(address.getFloor());
        newAddress.setDoor(address.getDoor());


        entityManager.persist(newAddress);
        System.out.println("======================");
        System.out.println("addressID:" + newAddress.getId());
        System.out.println("======================");*/


        if(serviceNames.size()>0){
           orderedServices=(ArrayList<Service>) getServicesByName(serviceNames);
            for(Service s :orderedServices){
                java.util.Calendar calendar = Calendar.getInstance();
                for(int i=0;i<s.getLoyalty()*12;i++) {
                    calendar.add(Calendar.MONTH,1);
                    calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
                    calendar.add(Calendar.DATE,4);
                    Bill b = new Bill();
                    b.setAmount(s.getPrice());
                    b.setDeadline(calendar.getTime());
                    b.setOrder(order);
                    b.setBillName(s.getName() + " - " + dfc.convertToYearMonth(calendar.getTime()));
                    bills.add(b);

                    //entityManager.persist(b);
                }

                if(s.getLoyalty()==0){
                    calendar.add(Calendar.MONTH,1);
                    calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
                    calendar.add(Calendar.DATE,4);
                    Bill b = new Bill();
                    b.setAmount(s.getPrice());
                    b.setDeadline(calendar.getTime());
                    b.setOrder(order);
                    b.setBillName(s.getName() + " - " + dfc.convertToYearMonth(calendar.getTime()));
                    bills.add(b);

                   // entityManager.persist(b);
                }

            }
        }
        if(servicePackNames.size()>0){
            orderedServicePacks=(ArrayList<ServicePack>)getServicePacksByName(servicePackNames);
            for(ServicePack sp : orderedServicePacks){
                java.util.Calendar calendar = Calendar.getInstance();
                for(int i=0;i<sp.getLoyalty();i++) {
                    calendar.add(Calendar.MONTH,1);
                    calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
                    calendar.add(Calendar.DATE,4);
                    Bill b = new Bill();
                    b.setAmount(sp.getPrice());
                    b.setOrder(order);
                    b.setDeadline(calendar.getTime());
                    b.setBillName(sp.getName() + " - " + dfc.convertToYearMonth(calendar.getTime()));
                    bills.add(b);
                    //entityManager.persist(b);
                }

                if(sp.getLoyalty()==0){
                    calendar.add(Calendar.MONTH,1);
                    calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
                    calendar.add(Calendar.DATE,4);
                    Bill b = new Bill();
                    b.setAmount(sp.getPrice());
                    b.setDeadline(calendar.getTime());
                    b.setOrder(order);
                    b.setBillName(sp.getName() + " - " + dfc.convertToYearMonth(calendar.getTime()));
                    bills.add(b);

                    //entityManager.persist(b);
                }
            }
        }

        order.setSubscriber((Customer)user);
        order.setServices(orderedServices);
        order.setServicePacks(orderedServicePacks);
        order.setOrderDate(java.util.Calendar.getInstance().getTime());
        order.setBills(bills);
        order.setAddress(address);

        entityManager.persist(order);


        System.out.println("======================");
        System.out.println("addressID:" + order.getOrderId());
        System.out.println("======================");

    //    attachOrderAddress(order.getOrderId(),newAddress.getId());

    }

    private Address lookUpForAddress(Address address){
        TypedQuery<Address> query;
        query=entityManager.createQuery("SELECT a FROM Address a " +
                "WHERE a.zipCode=:z AND a.city=:c AND a.street=:s AND a.houseNumber=:h" +
                " AND a.floor=:f AND a.door=:d", Address.class);
        query.setParameter("z", address.getZipCode());
        query.setParameter("c", address.getCity());
        query.setParameter("s", address.getStreet());
        query.setParameter("h", address.getHouseNumber());
        query.setParameter("f", address.getFloor());
        query.setParameter("d", address.getDoor());

        Address addressFromDB=null;

        ArrayList<Address> addresses=(ArrayList<Address>)query.getResultList();

        addressFromDB=addresses.get(0);

        System.out.println("dbbeli address ID : " + addressFromDB.getId());

        return addressFromDB;
    }

    private void attachOrderAddress(Long orderId, Long addressId){
       Query query;
        query=entityManager.createNativeQuery("UPDATE ORDERS SET ADDRESS_ID_FK =:addressId " +
                "WHERE ORDER_ID=:orderId",Integer.class);

        query.setParameter("addressId",addressId);
        query.setParameter("orderId",orderId);
        System.out.println(query.toString());

        int modifiedRows = query.executeUpdate();
        System.out.println("mofifiedRows: "+modifiedRows);
    }

}
