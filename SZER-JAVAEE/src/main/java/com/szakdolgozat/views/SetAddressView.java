package com.szakdolgozat.views;

import com.szakdolgozat.MyUI;
import com.szakdolgozat.ejbs.BasketEJB;
import com.szakdolgozat.ejbs.OrdersBean;
import com.szakdolgozat.ejbs.ServicesStorageBean;
import com.szakdolgozat.entities.person.ApplicationUser;
import com.vaadin.cdi.CDIView;
import com.vaadin.ui.*;

import javax.inject.Inject;

/**
 * Created by Ákos on 2016.03.21..
 */
@CDIView("SetAddressView")
public class SetAddressView extends AbstractView {

    public static final String VIEWID="SetAddressView";

    private Panel addressPanel;
    private TextField zipcode;
    private TextField city;
    private TextField street;
    private TextField houseNumber;
    private TextField floor;
    private TextField door;

    private FormLayout addressForm;

    private Button verifyButton;

    @Inject
    OrdersBean ordersBean;

    @Inject
    ServicesStorageBean servicesStorageBean;

    @Inject
    BasketEJB basketEJB;


    @Override
    public void afterEnter() {

    }

    private void initForm(){
        addressForm=new FormLayout();

        zipcode=new TextField("Irányítószám:");
        city=new TextField("Város:");
        street=new TextField("Utcanév:");
        houseNumber=new TextField("Házszám");
        floor=new TextField("Emelet");
        door=new TextField("Ajtó");

        addressForm.addComponent(zipcode);
        addressForm.addComponent(city);
        addressForm.addComponent(street);
        addressForm.addComponent(houseNumber);
        addressForm.addComponent(floor);
        addressForm.addComponent(door);

        verifyButton= new Button("Véglegesít");

        verifyButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ApplicationUser user= ((MyUI)getUI().getCurrent()).getLoggedInUser();

                ordersBean.saveOrder(user,
                                        servicesStorageBean.getServicesListOfCustomer(user.getUsername()),
                                        servicesStorageBean.getServicePacksListOfCustomer(user.getUsername())
                );
                basketEJB.makeBasketEmpty();
                servicesStorageBean.removeServicesList(user.getUsername());
                servicesStorageBean.removeServicePacksList(user.getUsername());
            }
        });

    }
}
