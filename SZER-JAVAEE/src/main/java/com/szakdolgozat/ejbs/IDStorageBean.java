package com.szakdolgozat.ejbs;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ákos on 2016.03.12..
 */
@Singleton
public class IDStorageBean {

    private HashMap<String,ArrayList<Long>> billIDsPerCustomer= new HashMap<>();

    public void addList(String customerName, ArrayList<Long> ids){
        if(!billIDsPerCustomer.containsKey(customerName)){
            billIDsPerCustomer.put(customerName, ids);
        }
    }

    public void removeList(String customerName){
        if(billIDsPerCustomer.containsKey(customerName)){
            billIDsPerCustomer.remove(customerName);
        }
    }

    public ArrayList<Long> getIDListOfCustomer(String customerName){
        if(billIDsPerCustomer.containsKey(customerName)){
            return billIDsPerCustomer.get(customerName);
        }
        return null;
    }
}
