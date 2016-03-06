package com.szakdolgozat.ejbs;

import com.szakdolgozat.entities.Order;
import com.szakdolgozat.entities.ServicePack;
import com.szakdolgozat.entities.person.ApplicationUser;
import com.szakdolgozat.entities.person.Customer;
import com.szakdolgozat.entities.service.Service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Ákos on 2016.03.06..
 */
@Stateless
public class OrdersBean {

    @PersistenceContext(unitName = "SZERPU")
    EntityManager entityManager;

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


    public void saveOrder(ApplicationUser user, List<String> serviceNames, List<String> servicePackNames){
        Order order= new Order();
        ArrayList<Service> orderedServices=null;
        ArrayList<ServicePack> orderedServicePacks=null;
        if(serviceNames.size()>0){
           orderedServices=(ArrayList<Service>) getServicesByName(serviceNames);
        }
        if(servicePackNames.size()>0){
            orderedServicePacks=(ArrayList<ServicePack>)getServicePacksByName(servicePackNames);
        }

        order.setSubscriber((Customer)user);
        order.setServices(orderedServices);
        order.setServicePacks(orderedServicePacks);
        order.setOrderDate(java.util.Calendar.getInstance().getTime());
        entityManager.persist(order);

    }

}
