package com.szakdolgozat.views;

import com.szakdolgozat.ejbs.ServicesBean;
import com.szakdolgozat.entities.service.InternetService;
import com.szakdolgozat.entities.service.Service;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.declarative.converters.ShortcutKeyMapper;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ákos on 2016.02.27..
 */
@CDIView("ServicesView")
public class ServicesView extends AbstractView {

    public static final String VIEWID="ServicesView";
    private Table services;
    private IndexedContainer internetContainer;

    private String selectedServiceName;
    private Service selectedService;
    private Button toCartButton;

    @Inject
    ServicesBean servicesBean;

    @Override
    public void afterEnter() {

        services= new Table("Elérhető szolgáltatások");
        services.setPageLength(5);
        internetContainer= new IndexedContainer();
    //    internetContainer.addContainerProperty("ID",Short.class,null);
        internetContainer.addContainerProperty("Név",String.class,null);
        internetContainer.addContainerProperty("Leírás",String.class,null);
        internetContainer.addContainerProperty("Ár",Integer.class,null);
        internetContainer.addContainerProperty("Sebesség",Short.class,null); // 10/1 stringben módosítási javaslat

        ArrayList<InternetService> internetServices=(ArrayList<InternetService>) servicesBean.getAllInternetServices();

        System.out.println("A lekérdezés eredménylistájának mérete:" + internetServices.size());
        for(InternetService is : internetServices){
            System.out.println(is.getName());
            Item item=internetContainer.addItem(is);
     //       item.getItemProperty("ID").setValue(is.getServiceId());
            item.getItemProperty("Név").setValue(is.getName());
            item.getItemProperty("Leírás").setValue(is.getDescription());
            item.getItemProperty("Ár").setValue(is.getPrice());
            item.getItemProperty("Sebesség").setValue(is.getSpeed());
        }
        services.setImmediate(true);
        services.setSelectable(true);
        services.setContainerDataSource(internetContainer);
    //    services.setColumnCollapsingAllowed(true);
    //    services.setColumnCollapsed("ID",true);

        services.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                selectedService=(Service)valueChangeEvent.getProperty().getValue();
                if(selectedService!=null) {
                    selectedServiceName = selectedService.getName();
                    System.out.println(selectedService.getName());
                }
            }
        });

        toCartButton= new Button("Kosárba");

        toCartButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                //TODO
            }
        });
        menuContent.addComponent(toCartButton);

        menuContent.addComponent(services);

    }
}
