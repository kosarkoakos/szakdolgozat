package com.szakdolgozat.views;

import com.szakdolgozat.dto.NamePriceDTO;
import com.szakdolgozat.ejbs.BasketEJB;
import com.szakdolgozat.ejbs.ServicesBean;
import com.szakdolgozat.ejbs.TableContentHandlerBean;
import com.szakdolgozat.entities.ServicePack;
import com.szakdolgozat.entities.service.InternetService;
import com.szakdolgozat.entities.service.Service;
import com.szakdolgozat.entities.service.TelephoneService;
import com.szakdolgozat.entities.service.TelevisionService;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.*;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.ArrayList;

/**
 * Created by Ákos on 2016.02.27..
 */
@CDIView("ServicesView")
public class ServicesView extends AbstractView {

    public static final String VIEWID="ServicesView";

    private VerticalLayout layoutForTables;
    private HorizontalLayout layoutForContent;
    private VerticalLayout layoutForBasket;
    private String tableWidth="450px";


    private Table services;
    private Table packContents;
    private Table basket;
    private IndexedContainer internetContainer;
    private IndexedContainer televisionContainer;
    private IndexedContainer telephoneContainer;
    private IndexedContainer servicePackContainer;
    private IndexedContainer servicePackContentContainer;

    private String selectedServiceType;
    private String selectedServiceName;
    private ServicePack selectedServicePack;
    private String selectedServicePackName;
    private Service selectedService;
    private Button toCartButton;
    private Button removeFromCart;
    private ComboBox serviceTypes;
    private String removeName;

    ArrayList<ServicePack> allServicePacks;

    @Inject
    ServicesBean servicesBean;

    @Inject
    BasketEJB basketEJB;

    @Inject
    TableContentHandlerBean tableContentHandlerBean;

    @Override
    public void afterEnter() {
        layoutForTables = new VerticalLayout();
        layoutForBasket = new VerticalLayout();
        services= new Table();
        initBasketTable();

        serviceTypes= new ComboBox("Válassz!");
        serviceTypes.addItem("Telefon");
        serviceTypes.addItem("Televízió");
        serviceTypes.addItem("Internet");
        serviceTypes.addItem("Csomagok");
        serviceTypes.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                selectedServiceType=(String)valueChangeEvent.getProperty().getValue();
                refreshTableContent(selectedServiceType);
            }
        });
        serviceTypes.setNullSelectionAllowed(false);
        serviceTypes.setValue("Telefon");



        services.setWidth(tableWidth);
        services.setPageLength(5);
        services.setImmediate(true);
        services.setSelectable(true);
        switchToTelephone();
        services.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                if(selectedServiceType.equals("Televízió") ||
                   selectedServiceType.equals("Telefon") ||
                   selectedServiceType.equals("Internet")){

                    selectedService = (Service) valueChangeEvent.getProperty().getValue();
                    if (selectedService != null) {
                        selectedServiceName = selectedService.getName();
                    }
                }

                if(selectedServiceType.equals("Csomagok")){
                    selectedServicePack=(ServicePack)valueChangeEvent.getProperty().getValue();
                    if (selectedServicePack != null) {
                        selectedServicePackName= selectedServicePack.getName();
                    }

                    fillpackContents();
                }

            }
        });



        toCartButton= new Button("Kosárba");
        toCartButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(selectedServiceType.equals("Televízió") ||
                        selectedServiceType.equals("Telefon") ||
                        selectedServiceType.equals("Internet")){

                    if (selectedService != null) {
                        basketEJB.addService(selectedServiceName);
                    }
                }

                if(selectedServiceType.equals("Csomagok")){
                    if (selectedServicePack != null) {
                        basketEJB.addServicePack(selectedServicePackName);
                    }
                }
            }
        });


        layoutForTables.addComponent(serviceTypes);
        layoutForTables.addComponent(services);

        layoutForContent = new HorizontalLayout();
        layoutForContent.addComponent(layoutForTables);
        layoutForContent.addComponent(toCartButton);



        removeFromCart= new Button("Eltávolít");
        removeFromCart.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                System.out.println("removeCart clicklistenerjében a removeName: "+ removeName);
                if(basketEJB != null){
                    basketEJB.removeService(removeName);
                    basketEJB.removeServicePack(removeName);
                }else{
                    System.out.println("A basketEJB null.");
                }

            }
        });


        layoutForBasket.addComponent(basket);
        layoutForBasket.addComponent(removeFromCart);
        layoutForContent.addComponent(layoutForBasket);

        menuContent.addComponent(layoutForContent);

    }

    private void refreshTableContent(String serviceType) {

        switch (serviceType){
            case "Telefon": makeLayoutForTablesAsOneTable(); switchToTelephone(); break;
            case "Televízió": makeLayoutForTablesAsOneTable(); switchToTelevision(); break;
            case "Internet": makeLayoutForTablesAsOneTable(); switchToInternet(); break;
            case "Csomagok": makeLayoutForTablesAsTwoTable();  switchToServicePack();fillpackContents(); break;
        }

    }

    private void makeLayoutForTablesAsOneTable(){
        if(layoutForTables.getComponentCount()==3){
            layoutForTables.removeComponent(packContents);
        }
    }

    private void makeLayoutForTablesAsTwoTable(){
        if(layoutForTables.getComponentCount()==2){
            packContents= new Table("A szolgáltatáscsomag tartalma:");
            packContents.setPageLength(3);
            packContents.setWidth(tableWidth);
            layoutForTables.addComponent(packContents);
        }
    }

    private void switchToInternet() {
        if (internetContainer == null) {

            internetContainer = new IndexedContainer();
            internetContainer.addContainerProperty("Név", String.class, null);
            internetContainer.addContainerProperty("Leírás", String.class, null);
            internetContainer.addContainerProperty("Ár", Integer.class, null);
            internetContainer.addContainerProperty("Sebesség", String.class, null);

            ArrayList<InternetService> internetServices = (ArrayList<InternetService>) servicesBean.getAllInternetServices();

            for (InternetService is : internetServices) {
                Item item = internetContainer.addItem(is);
                item.getItemProperty("Név").setValue(is.getName());
                item.getItemProperty("Leírás").setValue(is.getDescription());
                item.getItemProperty("Ár").setValue(is.getPrice());
                item.getItemProperty("Sebesség").setValue(is.getSpeed());
            }

            services.setContainerDataSource(internetContainer);
        }
    }

    private void switchToTelevision(){
        if(televisionContainer==null){
            televisionContainer=new IndexedContainer();

            televisionContainer.addContainerProperty("Név", String.class,null);
            televisionContainer.addContainerProperty("Leírás", String.class,null);
            televisionContainer.addContainerProperty("Ár", Integer.class, null);
            televisionContainer.addContainerProperty("Csatornák száma", Byte.class, null);

            ArrayList<TelevisionService> allTelevisionServices;
            allTelevisionServices=(ArrayList<TelevisionService>) servicesBean.getAllTelevisionServices();

            for (TelevisionService ts : allTelevisionServices){
                Item item = televisionContainer.addItem(ts);
                item.getItemProperty("Név").setValue(ts.getName());
                item.getItemProperty("Leírás").setValue(ts.getDescription());
                item.getItemProperty("Ár").setValue(ts.getPrice());
                item.getItemProperty("Csatornák száma").setValue(ts.getChannelCount());
            }
        }

        services.setContainerDataSource(televisionContainer);
    }

    private void switchToTelephone(){
        if(telephoneContainer==null){
            telephoneContainer=new IndexedContainer();

            telephoneContainer.addContainerProperty("Név", String.class, null);
            telephoneContainer.addContainerProperty("Leírás", String.class,null);
            telephoneContainer.addContainerProperty("Ár", Integer.class, null);
            telephoneContainer.addContainerProperty("Típus", String.class,null);

            ArrayList<TelephoneService> allTelephoneServices;
            allTelephoneServices=(ArrayList<TelephoneService>)servicesBean.getAllTelephoneServices();

            for(TelephoneService ts : allTelephoneServices){
                Item item= telephoneContainer.addItem(ts);
                item.getItemProperty("Név").setValue(ts.getName());
                item.getItemProperty("Leírás").setValue(ts.getDescription());
                item.getItemProperty("Ár").setValue(ts.getPrice());
                item.getItemProperty("Típus").setValue(ts.getType());
            }
        }

        services.setContainerDataSource(telephoneContainer);
    }

    private void switchToServicePack(){
        if(servicePackContainer ==null){
            servicePackContainer = new IndexedContainer();
            servicePackContainer.addContainerProperty("Név", String.class,null);
            servicePackContainer.addContainerProperty("Leírás", String.class,null);
            servicePackContainer.addContainerProperty("Ár", Integer.class,null);

            allServicePacks = (ArrayList<ServicePack>)servicesBean.getAllServicePacks();

            for(ServicePack sp : allServicePacks){
                Item item=servicePackContainer.addItem(sp);
                item.getItemProperty("Név").setValue(sp.getName());
                item.getItemProperty("Leírás").setValue(sp.getDescription());
                item.getItemProperty("Ár").setValue(sp.getPrice());
            }
        }

        services.setContainerDataSource(servicePackContainer);
    }

    private void fillpackContents(){
            for(ServicePack sp : allServicePacks){
                if (sp.getName().equals(selectedServicePackName)){
                    servicePackContentContainer=new IndexedContainer();
                    servicePackContentContainer.addContainerProperty("Név", String.class,null);
                    servicePackContentContainer.addContainerProperty("Leírás", String.class,null);
                    servicePackContentContainer.addContainerProperty("Jellemző", String.class,null);

                    for(Service part : sp.getParts()){
                        Item item = servicePackContentContainer.addItem(part);
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

                }
            }

        packContents.setContainerDataSource(servicePackContentContainer);
    }

    private void setBasketDS(@Observes String s){
        System.out.println("setBasketDS nevű, eventet observelő metódus indul. Stirng: " + s);
        IndexedContainer bic;
        bic=tableContentHandlerBean.makeBasketIndexedConatiner(this.basketEJB.getServiceNames(),
                                                            this.basketEJB.getServicePackNames());
        layoutForBasket.removeComponent(basket);
        initBasketTable();
        layoutForBasket.addComponent(basket);

        if(bic==null){
            System.out.println("A bic null.");
        }else {
            basket.setContainerDataSource(bic);
        }

    }

    private void initBasketTable(){
        basket= new Table("A kosár tartalma:");
        basket.setSelectable(true);
        basket.setImmediate(true);
        basket.setPageLength(3);

        basket.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                if(valueChangeEvent!=null) {
                    removeName = ((NamePriceDTO) valueChangeEvent
                            .getProperty()
                            .getValue())
                            .getName();
                    System.out.println("A kiválasztott basketbeli név: " + removeName);
                }
                else{
                    System.out.println("A basket valueChangeEventje null");
                }

            }
        });
    }
}
