package com.szakdolgozat.views;

import com.szakdolgozat.MyUI;
import com.szakdolgozat.dto.NamePriceDTO;
import com.szakdolgozat.ejbs.*;
import com.szakdolgozat.entities.ServicePack;
import com.szakdolgozat.entities.person.ApplicationUser;
import com.szakdolgozat.entities.person.Customer;
import com.szakdolgozat.entities.service.InternetService;
import com.szakdolgozat.entities.service.Service;
import com.szakdolgozat.entities.service.TelephoneService;
import com.szakdolgozat.entities.service.TelevisionService;
import com.vaadin.cdi.CDIView;
import com.vaadin.cdi.NormalUIScoped;
import com.vaadin.cdi.UIScoped;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.ArrayList;

/**
 * Created by Ákos on 2016.02.27..
 */
@CDIView("ServicesView")
public class ServicesView extends AbstractView {

    public static final String VIEWID="ServicesView";

    private VerticalLayout layoutForServiceTables;
    private HorizontalLayout layoutForServiceTablesLittleComponents;
    private HorizontalLayout layoutForContent;
    private VerticalLayout layoutForBasket;
    private HorizontalLayout layoutForBasketButtons;
    private String tableWidth="730px";
    private String basketWidth="270px";


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
    private Button orderButton;
    private ComboBox serviceTypes;
    private String removeName;

    private VerticalLayout basketCompenents;

    ArrayList<ServicePack> allServicePacks;

    @Inject
    ServicesBean servicesBean;

    @Inject
    BasketEJB basketEJB;

    @Inject
    TableContentHandlerBean tableContentHandlerBean;

    @Inject
    OrdersBean ordersBean;

    @Inject
    ServicesStorageBean servicesStorageBean;

    @Override
    public void afterEnter() {
        basket=new Table();
        initBasketTable();
        initLayoutForBasket();

        initServiceTablesLayout();

        layoutForContent = new HorizontalLayout();

        layoutForContent.addComponent(layoutForServiceTables);

      //  layoutForContent.addComponent(layoutForBasket);
      //  layoutForContent.setComponentAlignment(layoutForBasket,Alignment.TOP_RIGHT);

        menuContent.addComponent(layoutForContent);

        fillBasketTable();

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
        if(layoutForServiceTables.getComponentCount()==3){
            layoutForServiceTables.removeComponent(packContents);
        }
    }

    private void makeLayoutForTablesAsTwoTable(){
        if(layoutForServiceTables.getComponentCount()==2){
            packContents= new Table("A szolgáltatáscsomag tartalma:");
            packContents.setPageLength(3);
            packContents.setWidth(tableWidth);
            layoutForServiceTables.addComponent(packContents);
        }
    }

    private void switchToInternet() {
       /* if (internetContainer == null) {

            internetContainer = new IndexedContainer();
            internetContainer.addContainerProperty("Név", String.class, null);
            internetContainer.addContainerProperty("Leírás", String.class, null);
            internetContainer.addContainerProperty("Hűségidő", Integer.class, null);
            internetContainer.addContainerProperty("Ár", Integer.class, null);
            internetContainer.addContainerProperty("Sebesség", String.class, null);

            ArrayList<InternetService> internetServices = (ArrayList<InternetService>) servicesBean.getAllInternetServices();

            for (InternetService is : internetServices) {
                Item item = internetContainer.addItem(is);
                item.getItemProperty("Név").setValue(is.getName());
                item.getItemProperty("Leírás").setValue(is.getDescription());
                item.getItemProperty("Hűségidő").setValue(is.getLoyalty());
                item.getItemProperty("Ár").setValue(is.getPrice());
                item.getItemProperty("Sebesség").setValue(is.getSpeed());
            }
            }*/

        internetContainer=tableContentHandlerBean.makeInternetServicesIndexedContainer();
        services.setContainerDataSource(internetContainer);
    }

    private void switchToTelevision(){
        /*if(televisionContainer==null){
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
        }*/
        televisionContainer=tableContentHandlerBean.makeTelevisionIndexedContainer();
        services.setContainerDataSource(televisionContainer);
    }

    private void switchToTelephone(){
       /* if(telephoneContainer==null){
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
        }*/
        telephoneContainer=tableContentHandlerBean.makeTelephoneIndexedConatiner();
        services.setContainerDataSource(telephoneContainer);
    }

    private void switchToServicePack(){
        /*if(servicePackContainer ==null){
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
        }*/

        servicePackContainer=tableContentHandlerBean.makeServicePacksIndexedContainer();
        services.setContainerDataSource(servicePackContainer);
    }

    private void fillpackContents(){
           /* for(ServicePack sp : allServicePacks){
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
            }*/
        if(selectedServicePackName!=null) {
            servicePackContentContainer = tableContentHandlerBean.makeServicePackContentIndexedContainer(selectedServicePackName);

            packContents.setContainerDataSource(servicePackContentContainer);
        }
    }

    private void setBasketDS(@Observes String s){
        fillBasketTable();


    }

    private void fillBasketTable(){
        IndexedContainer bic;
        bic=tableContentHandlerBean.makeBasketIndexedConatiner(this.basketEJB.getServiceNames(),
                this.basketEJB.getServicePackNames());
        layoutForBasket.removeComponent(basket);
        initBasketTable();
        layoutForBasket.addComponent(basket);

        basket.setContainerDataSource(bic);
    }

    private void initBasketTable(){
        basket= new Table();
        basket.setSelectable(true);
        basket.setImmediate(true);
        basket.setPageLength(3);
        basket.setWidth(basketWidth);

        basket.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                if(valueChangeEvent!=null) {
                    removeName = ((NamePriceDTO) valueChangeEvent.getProperty().getValue()).getName();
                }
            }
        });
    }

    private void initLayoutForBasket(){
        layoutForBasket=new VerticalLayout();
        layoutForBasketButtons= new HorizontalLayout();
        basketCompenents=new VerticalLayout();

        removeFromCart= new Button("Eltávolít");
        removeFromCart.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(basketEJB != null){
                    basketEJB.removeService(removeName);
                    basketEJB.removeServicePack(removeName);
                }else{
                    System.out.println("A basketEJB null.");
                }

            }
        });

        orderButton= new Button("Megrendel");
        orderButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ApplicationUser user= ((MyUI)getUI().getCurrent()).getLoggedInUser();

                if(user!=null){
                    servicesStorageBean.addServicesList(user.getUsername(),(ArrayList<String>) basketEJB.getServiceNames());
                    servicesStorageBean.addServicePacksList(user.getUsername(),(ArrayList<String>) basketEJB.getServicePackNames());
                    getUI().getNavigator().navigateTo(SetAddressView.VIEWID);

                }else{
                    Notification notification= new Notification("Jelentkezz be!", Notification.Type.HUMANIZED_MESSAGE);
                    notification.setDelayMsec(5000);
                    notification.show(getUI().getPage());
                }
            }
        });

        layoutForBasketButtons.addComponent(removeFromCart);
        layoutForBasketButtons.addComponent(orderButton);
        layoutForBasket.addComponent(basket);
        layoutForBasket.addComponent(layoutForBasketButtons);

        //basketCompenents.addComponent(layoutForBasketButtons);
        //basketCompenents.addComponent(layoutForBasket);

        if(((MyUI)getUI().getCurrent()).getLoggedInUser() instanceof Customer) {
            loginLayout.addComponent(layoutForBasket);
            loginLayout.setComponentAlignment(layoutForBasket,Alignment.MIDDLE_LEFT);
        }

    }

    private void initServiceTablesLayout(){
        layoutForServiceTables = new VerticalLayout();
        layoutForServiceTables.setMargin(new MarginInfo(false,true,false,false));
        services= new Table();

        serviceTypes= new ComboBox();
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

        switchToTelephone();

        services.setWidth(tableWidth);
        services.setPageLength(5);
        services.setImmediate(true);
        services.setSelectable(true);
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

        layoutForServiceTablesLittleComponents= new HorizontalLayout();
        layoutForServiceTablesLittleComponents.addComponent(serviceTypes);
        layoutForServiceTablesLittleComponents.addComponent(toCartButton);

        layoutForServiceTables.addComponent(layoutForServiceTablesLittleComponents);
        layoutForServiceTables.addComponent(services);
    }
}
