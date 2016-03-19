package com.szakdolgozat.ejbs;

import com.szakdolgozat.dto.FaultDTO;
import com.szakdolgozat.dto.NamePriceDTO;
import com.szakdolgozat.entities.Bill;
import com.szakdolgozat.entities.ReportedFault;
import com.szakdolgozat.entities.ServicePack;
import com.szakdolgozat.entities.person.ApplicationUser;
import com.szakdolgozat.entities.person.Customer;
import com.szakdolgozat.entities.service.InternetService;
import com.szakdolgozat.entities.service.Service;
import com.szakdolgozat.entities.service.TelephoneService;
import com.szakdolgozat.entities.service.TelevisionService;
import com.szakdolgozat.enums.ServiceType;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Index;
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

    @Inject
    DateFormatConverter dfc;

    public IndexedContainer makeBasketIndexedConatiner(List<String> sNames, List<String> spNames){

        IndexedContainer ic= new IndexedContainer();

        ic.addContainerProperty("Név", String.class, null);
        ic.addContainerProperty("Ár", Integer.class,null);
  //      ic.addContainerProperty("Hűségidő",Integer.class,null);

        TypedQuery<NamePriceDTO> sq;
        String selectString="SELECT NEW com.szakdolgozat.dto.NamePriceDTO(s.name, s.price, s.loyalty) FROM Service s" +
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
        selectString="SELECT NEW com.szakdolgozat.dto.NamePriceDTO(s.name, s.price, s.loyalty) FROM ServicePack s" +
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
    //        item.getItemProperty("Hűségidő").setValue(np.getLoyalty());
        }

        for(NamePriceDTO np : spresult){
            Item item= ic.addItem(np);
            item.getItemProperty("Név").setValue(np.getName());
            item.getItemProperty("Ár").setValue(np.getPrice());
    //        item.getItemProperty("Hűségidő").setValue(np.getLoyalty());
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
        ic.addContainerProperty("Befizetési határidő", String.class ,null);

        for (Bill b : bills){
            Item item= ic.addItem(b);
            item.getItemProperty("Szolgáltatás").setValue(b.getBillName());
            item.getItemProperty("Összeg").setValue(b.getAmount());
            item.getItemProperty("Befizetési határidő").setValue(dfc.convertToFullDate(b.getDeadline()));
        }

        return ic;
    }

    public IndexedContainer makeFaultsIndexedContainer(ServiceType type){
        List<FaultDTO> faults = null;

        TypedQuery<FaultDTO> query;
        query = entityManager.createQuery("SELECT NEW com.szakdolgozat.dto.FaultDTO(r.id, r.reporter.username, r.title, r.reportDate) FROM ReportedFault r WHERE r.serviceType =:type " +
                "AND r.solvedDate IS NULL",FaultDTO.class);
        query.setParameter("type",type);

        try{
            faults=query.getResultList();
        }catch (Exception e){
            System.out.println("Nem talált rekordot.");
        }

        IndexedContainer ic = new IndexedContainer();
        ic.addContainerProperty("Bejelentő neve", String.class,null);
        ic.addContainerProperty("Tárgy", String.class, null);
        ic.addContainerProperty("Beérkezett", String.class,null);

        for(FaultDTO f : faults){
            Item item=ic.addItem(f);
            item.getItemProperty("Bejelentő neve").setValue(f.getCustomerName());
            item.getItemProperty("Tárgy").setValue(f.getFaultTitle());
            item.getItemProperty("Beérkezett").setValue(dfc.convertTofullDateTime(f.getReportedDate()));
        }

        return ic;
    }

    public IndexedContainer makeInternetServicesIndexedContainer(){
        IndexedContainer ic = new IndexedContainer();
        ic.addContainerProperty("Név", String.class, null);
        ic.addContainerProperty("Leírás", String.class, null);
        ic.addContainerProperty("Hűségidő", Integer.class, null);
        ic.addContainerProperty("Ár", Integer.class, null);
        ic.addContainerProperty("Sebesség(Mbit/sec)", String.class, null);

        ArrayList<InternetService> internetServices;

        TypedQuery<InternetService> query=entityManager.createQuery("SELECT s FROM InternetService s",InternetService.class);

        internetServices=(ArrayList<InternetService>)query.getResultList();

        for (InternetService is : internetServices) {
            Item item = ic.addItem(is);
            item.getItemProperty("Név").setValue(is.getName());
            item.getItemProperty("Leírás").setValue(is.getDescription());
            item.getItemProperty("Hűségidő").setValue(is.getLoyalty());
            item.getItemProperty("Ár").setValue(is.getPrice());
            item.getItemProperty("Sebesség(Mbit/sec)").setValue(is.getSpeed());
        }

        return ic;
    }

    public IndexedContainer makeTelevisionIndexedContainer(){
        IndexedContainer ic=new IndexedContainer();

        ic.addContainerProperty("Név", String.class,null);
        ic.addContainerProperty("Leírás", String.class,null);
        ic.addContainerProperty("Hűségidő", Integer.class,null);
        ic.addContainerProperty("Ár", Integer.class, null);
        ic.addContainerProperty("Csatornák száma", Byte.class, null);

        ArrayList<TelevisionService> allTelevisionServices;
        TypedQuery<TelevisionService> query=entityManager.createQuery("SELECT s FROM TelevisionService s",TelevisionService.class);
        allTelevisionServices=(ArrayList<TelevisionService>)query.getResultList();

        for (TelevisionService ts : allTelevisionServices){
            Item item = ic.addItem(ts);
            item.getItemProperty("Név").setValue(ts.getName());
            item.getItemProperty("Leírás").setValue(ts.getDescription());
            item.getItemProperty("Hűségidő").setValue(ts.getLoyalty());
            item.getItemProperty("Ár").setValue(ts.getPrice());
            item.getItemProperty("Csatornák száma").setValue(ts.getChannelCount());
        }

        return ic;
    }

    public IndexedContainer makeTelephoneIndexedConatiner(){
        IndexedContainer ic=new IndexedContainer();

        ic.addContainerProperty("Név", String.class, null);
        ic.addContainerProperty("Leírás", String.class,null);
        ic.addContainerProperty("Hűségidő", Integer.class,null);
        ic.addContainerProperty("Ár", Integer.class, null);
        ic.addContainerProperty("Típus", String.class,null);

        ArrayList<TelephoneService> allTelephoneServices;
        TypedQuery<TelephoneService> query=entityManager.createQuery("SELECT s FROM TelephoneService s",TelephoneService.class);
        allTelephoneServices=(ArrayList<TelephoneService>) query.getResultList();

        for(TelephoneService ts : allTelephoneServices){
            Item item= ic.addItem(ts);
            item.getItemProperty("Név").setValue(ts.getName());
            item.getItemProperty("Leírás").setValue(ts.getDescription());
            item.getItemProperty("Hűségidő").setValue(ts.getLoyalty());
            item.getItemProperty("Ár").setValue(ts.getPrice());
            item.getItemProperty("Típus").setValue(ts.getType());
        }

        return ic;
    }

    public IndexedContainer makeServicePacksIndexedContainer(){
        IndexedContainer ic = new IndexedContainer();

        ic.addContainerProperty("Név", String.class,null);
        ic.addContainerProperty("Leírás", String.class,null);
        ic.addContainerProperty("Hűségidő",Integer.class,null);
        ic.addContainerProperty("Ár", Integer.class,null);

        ArrayList<ServicePack> allServicePacks;
        TypedQuery query=entityManager.createQuery("SELECT s FROM ServicePack s",ServicePack.class);
        allServicePacks=(ArrayList<ServicePack>) query.getResultList();
        System.out.println("allServicePacks size:");
        System.out.println(allServicePacks.size());
        for(ServicePack sp : allServicePacks){
            Item item=ic.addItem(sp);
            item.getItemProperty("Név").setValue(sp.getName());
            item.getItemProperty("Leírás").setValue(sp.getDescription());
            item.getItemProperty("Hűségidő").setValue(sp.getLoyalty());
            item.getItemProperty("Ár").setValue(sp.getPrice());
        }

        return ic;
    }

    public IndexedContainer makeServicePackContentIndexedContainer(String spname){

        TypedQuery<ServicePack> query;
        query=entityManager.createQuery("SELECT s FROM ServicePack s WHERE s.name=:spname",ServicePack.class);
        query.setParameter("spname", spname);
        ServicePack sp =query.getSingleResult();

        IndexedContainer ic=new IndexedContainer();

        ic.addContainerProperty("Név", String.class,null);
        ic.addContainerProperty("Leírás", String.class,null);
        ic.addContainerProperty("Jellemző", String.class,null);

        for(Service part : sp.getParts()){
            Item item = ic.addItem(part);
            item.getItemProperty("Név").setValue(part.getName());
            item.getItemProperty("Leírás").setValue(part.getDescription());
            if (part instanceof TelephoneService){
                item.getItemProperty("Jellemző").setValue(((TelephoneService) part).getType());
            }else if (part instanceof TelevisionService){
                item.getItemProperty("Jellemző").setValue((((TelevisionService) part).getChannelCount()).toString());
            }else if(part instanceof InternetService){
                item.getItemProperty("Jellemző").setValue(((InternetService)part).getSpeed());
            }
        }

        return ic;
    }
}
