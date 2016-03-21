package com.szakdolgozat.ejbs;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ákos on 2016.03.21..
 */
@Singleton
public class ServicesStorageBean {

    HashMap<String,ArrayList<String>> servicesUnderOrdering=new HashMap<>();
    HashMap<String,ArrayList<String>> servicePacksUnderOrdering= new HashMap<>();

    public void addServicesList(String customerName, ArrayList<String> services){
        if(!servicesUnderOrdering.containsKey(customerName)){
            servicesUnderOrdering.put(customerName, services);
        }
    }

    public void removeServicesList(String customerName){
        if(servicesUnderOrdering.containsKey(customerName)){
            servicesUnderOrdering.remove(customerName);
        }
    }

    public ArrayList<String> getServicesListOfCustomer(String customerName){
        if(servicesUnderOrdering.containsKey(customerName)){
            return servicesUnderOrdering.get(customerName);
        }
        return null;
    }

    public void addServicePacksList(String customerName, ArrayList<String> servicePacks){
        if(!servicePacksUnderOrdering.containsKey(customerName)){
            servicePacksUnderOrdering.put(customerName, servicePacks);
        }
    }

    public void removeServicePacksList(String customerName){
        if(servicePacksUnderOrdering.containsKey(customerName)){
            servicePacksUnderOrdering.remove(customerName);
        }
    }

    public ArrayList<String> getServicePacksListOfCustomer(String customerName){
        if(servicePacksUnderOrdering.containsKey(customerName)){
            return servicePacksUnderOrdering.get(customerName);
        }
        return null;
    }
}
