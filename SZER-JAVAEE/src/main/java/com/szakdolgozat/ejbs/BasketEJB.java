package com.szakdolgozat.ejbs;

import com.szakdolgozat.entities.ServicePack;
import com.szakdolgozat.entities.service.Service;
import com.vaadin.cdi.NormalUIScoped;
import com.vaadin.cdi.UIScoped;
import com.vaadin.ui.Notification;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ákos on 2016.02.28..
 */
@NormalUIScoped
public class BasketEJB {

    private List<String> serviceNames;
    private List<String> servicePackNames;

    @Inject
    private Event<String> basketChanged;

    public BasketEJB(){
        serviceNames = new ArrayList<>();
        servicePackNames = new ArrayList<>();
    }

    public void addService(String serviceName){
        if(!serviceNames.contains(serviceName)) {
            serviceNames.add(serviceName);
            basketChanged.fire(serviceName + "added.");
        }else{
            Notification.show("Már benne van a kosárban a \"" + serviceName + "\" szolgáltatás!");
        }
    }

    public void addServicePack(String servicePackName){
        if(!servicePackNames.contains(servicePackName)) {
            servicePackNames.add(servicePackName);
            basketChanged.fire(servicePackName + "added.");
        }else{
            Notification.show("Már benne van a kosárban a \"" + servicePackName + "\" nevű csomag!");
        }
    }

    public void removeService(String serviceName){
        if(serviceNames.contains(serviceName)) {
            serviceNames.remove(serviceName);
            basketChanged.fire(serviceName + " removed.");
        }
    }

    public void removeServicePack(String servicePackName){
        if(servicePackNames.contains(servicePackName)) {
            servicePackNames.remove(servicePackName);
            basketChanged.fire(servicePackName + " removed.");
        }
    }

    public void makeBasketEmpty(){
        serviceNames.clear();
        servicePackNames.clear();
      //  basketChanged.fire("Basket is made empty!");
    }

    public List<String> getServicePackNames() {
        return servicePackNames;
    }

    public void setServicePackNames(List<String> servicePackNames) {
        this.servicePackNames = servicePackNames;
    }

    public List<String> getServiceNames() {
        return serviceNames;
    }

    public void setServiceNames(List<String> serviceNames) {
        this.serviceNames = serviceNames;
    }
}
