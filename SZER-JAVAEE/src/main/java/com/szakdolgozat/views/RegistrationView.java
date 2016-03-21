package com.szakdolgozat.views;

import com.szakdolgozat.entities.person.Customer;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIView;
import com.vaadin.ui.*;

/**
 * Created by Ákos on 2016.03.21..
 */
@CDIView("RegistrationView")
public class RegistrationView extends AbstractView {

    public static final String VIEWID="RegistrationView";
    Panel regPanel;
    TextField usernameTextField;
    PasswordField passwordField;
    PasswordField cPasswordField;
    TextField nameTextField;
    DateField birthDateDateField;
    TextField birthPlaceTextField;

    Button regButton;
    FormLayout regForm;
    private String panelWidth="400px";

    @Override
    public void afterEnter() {

        initForm();

        regPanel=new Panel("Regisztráció");
        regPanel.setContent(regForm);
        regPanel.setWidth(panelWidth);

        menuContent.addComponent(regPanel);
        menuContent.setComponentAlignment(regPanel,Alignment.TOP_CENTER);
    }

    private void initForm(){

        regForm= new FormLayout();

        usernameTextField=new TextField("Felhasználónév: ");
        passwordField= new PasswordField("Jelszó: ");
        cPasswordField= new PasswordField("Jelszó ismét: ");
        nameTextField= new TextField("Teljes név: ");
        birthDateDateField= new DateField("Születési idő: ");
        birthPlaceTextField= new TextField("Születési hely: ");
        regButton= new Button("Regisztráció");

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

        regForm.addComponent(usernameTextField);
        regForm.addComponent(passwordField);
        regForm.addComponent(cPasswordField);
        regForm.addComponent(nameTextField);
        regForm.addComponent(birthDateDateField);
        regForm.addComponent(birthPlaceTextField);
        regForm.addComponent(regButton);
    }
}
