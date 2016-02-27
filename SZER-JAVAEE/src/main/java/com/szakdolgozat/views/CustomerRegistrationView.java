package com.szakdolgozat.views;

import com.szakdolgozat.ejbs.ApplicationUserBean;
import com.szakdolgozat.entities.person.ApplicationUser;
import com.szakdolgozat.entities.person.Customer;
import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import javax.inject.Inject;
import java.util.*;

/**
 * Created by Ákos on 2016.02.27..
 */
@CDIView("CustomerRegistrationView")
public class CustomerRegistrationView extends VerticalLayout implements View {

    public static final String VIEWID="CustomerRegistrationView";

    Panel regPanel;
    TextField usernameTextField;
    PasswordField passwordField;
    PasswordField cPasswordField;
    TextField nameTextField;
    DateField birthDateDateField;
    TextField birthPlaceTextField;

    Button regButton;

    FormLayout regForm;

    @Inject
    ApplicationUserBean applicationUserBean;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        removeAllComponents();

        usernameTextField=new TextField("Felhasználónév: ");
        passwordField= new PasswordField("Jelszó: ");
        cPasswordField= new PasswordField("Jelszó ismét: ");
        nameTextField= new TextField("Teljes név: ");
        birthDateDateField= new DateField("Születési idő: ");
        birthPlaceTextField= new TextField("Születési hely: ");
        regButton= new Button("Regisztráció");

        regForm= new FormLayout();
        regForm.addComponent(usernameTextField);
        regForm.addComponent(passwordField);
        regForm.addComponent(cPasswordField);
        regForm.addComponent(nameTextField);
        regForm.addComponent(birthDateDateField);
        regForm.addComponent(birthPlaceTextField);
        regForm.addComponent(regButton);

        regButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Customer newCustomer = new Customer();
                newCustomer.setUsername(usernameTextField.getValue());
                newCustomer.setPassword(passwordField.getValue());
                newCustomer.setName(nameTextField.getValue());
                newCustomer.setBirthdate(birthDateDateField.getValue());
                newCustomer.setBirthPlace(birthPlaceTextField.getValue());
                newCustomer.setRegistrationDate(java.util.Calendar.getInstance().getTime());

                applicationUserBean.doRegistration(newCustomer);
                getUI().getNavigator().navigateTo(MainView.VIEWID);

            }
        });



        regPanel = new Panel("Adatok");
        regPanel.setContent(regForm);
        addComponent(regPanel);


    }
}
