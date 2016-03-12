package com.szakdolgozat.views;

import com.szakdolgozat.MyUI;
import com.szakdolgozat.components.VerticalMenu;
import com.szakdolgozat.dto.LoginDTO;
import com.szakdolgozat.ejbs.ApplicationUserBean;
import com.szakdolgozat.entities.person.Agent;
import com.szakdolgozat.entities.person.ApplicationUser;
import com.szakdolgozat.entities.person.Customer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;

import javax.inject.Inject;

/**
 * Created by Ákos on 2016.02.14..
 */

public abstract class AbstractView extends VerticalLayout implements View {

    protected VerticalLayout mainLayout= new VerticalLayout();

    protected HorizontalLayout topStripe;
    protected HorizontalLayout bottomStripe;

    protected VerticalLayout image;
    protected MenuBar menuBar;
    protected VerticalLayout menuLayout;

    protected VerticalLayout menuContent;

    protected Button loginButton;
    protected Button toRegPage;
    protected Button logoutButton;

    protected Button introductionButton;
    protected Button servicesButton;
    protected Button salesButton;
    protected Button contatcButton;

    protected VerticalLayout loginLayout;
    protected HorizontalLayout regLinkAndButton;
    protected Label userGreetingsLabel;
    protected TextField usernameTxtF;
    protected PasswordField passwordField;


    protected String imageWidth="710px";
    protected String loginLayoutWidth="180px";
    protected String menuWidth="150px";
    protected String menuContentWidth="740px";

    @Inject
    ApplicationUserBean applicationUserBean;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

        if(((MyUI)getUI().getCurrent()).getLoggedInUser()==null){
            mainLayout.removeAllComponents();
            initDefaultComponents();
            afterEnter();
        }else if(isCustomer()) {
            mainLayout.removeAllComponents();
            initDefaultComponents();
            afterEnter();
        }
        else if(isAgent()){
            mainLayout.removeAllComponents();
            initDefaultComponents();
            afterEnter();
        }
    }



    abstract public void afterEnter();

    private void initDefaultComponents() {

        topStripe=new HorizontalLayout();
        bottomStripe=new HorizontalLayout();

        image= new VerticalLayout();

        menuContent= new VerticalLayout();

        Label t = new Label("Tesztlabel");
        image.addComponent(t);

        topStripe.addComponent(image);

        buildLoginBox();
        buildMenuLayout();

        topStripe.addComponent(loginLayout);
        topStripe.setComponentAlignment(loginLayout,Alignment.TOP_RIGHT);

        bottomStripe.addComponent(menuLayout);
        bottomStripe.addComponent(menuContent);

        mainLayout.addComponent(topStripe);
        mainLayout.addComponent(bottomStripe);


        addComponent(mainLayout);

        mainLayout.setMargin(true);
        mainLayout.setWidth("1000px");
        setComponentAlignment(mainLayout,Alignment.MIDDLE_CENTER);

        image.setWidth(imageWidth);
        loginLayout.setWidth(loginLayoutWidth);
        menuLayout.setWidth(menuWidth);
        menuContent.setWidth(menuContentWidth);
        menuLayout.setMargin(new MarginInfo(false,false,false,false));
        menuContent.setMargin(new MarginInfo(false,false,false,false));
        image.setComponentAlignment(t, Alignment.BOTTOM_RIGHT);

    }

    protected void buildLoginBox(){
        ApplicationUser user=((MyUI)getUI().getCurrent()).getLoggedInUser();

        loginLayout= new VerticalLayout();

        if(user==null){
            usernameTxtF = new TextField("Felhasználónév: ");
            passwordField = new PasswordField("Jelszó: ");
            loginLayout.addComponent(usernameTxtF);
            loginLayout.addComponent(passwordField);
            usernameTxtF.setHeight("25px");
            passwordField.setHeight("25px");
            loginLayout.setComponentAlignment(usernameTxtF, Alignment.TOP_LEFT);
            loginLayout.setComponentAlignment(passwordField, Alignment.BOTTOM_RIGHT);

            regLinkAndButton= new HorizontalLayout();

            loginButton= new Button("Bejelentkezés");
            loginButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    LoginDTO loginDTO= new LoginDTO();
                    loginDTO.setUsername(usernameTxtF.getValue());
                    loginDTO.setPassword(passwordField.getValue());
                    ApplicationUser au=applicationUserBean.doLogin(loginDTO);
                    if(au!=null) {
                        ((MyUI) getUI().getCurrent()).setLoggedInUser(au);

                        getUI().getNavigator().navigateTo(MainView.VIEWID);
                    }else {
                        Notification.show("Valótlan bejelentkezési adatok!");
                    }
                }
            });

     //       loginLayout.addComponent(loginButton);
     //       loginLayout.setComponentAlignment(loginButton,Alignment.TOP_RIGHT);


            toRegPage= new Button("Regisztráció");
            toRegPage.setStyleName(Reindeer.BUTTON_LINK);
            toRegPage.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    getUI().getNavigator().navigateTo(CustomerRegistrationView.VIEWID);
                }
            });
            regLinkAndButton.addComponent(toRegPage);
            regLinkAndButton.addComponent(loginButton);
            loginLayout.addComponent(regLinkAndButton);


            //dbfeltöltéshez
        //    Button dbfill= new Button("tölt");
        //    dbfill.addClickListener(new Button.ClickListener() {
        //        @Override
        //        public void buttonClick(Button.ClickEvent clickEvent) {
        //            applicationUserBean.dbfill();
        //        }
        //    });

        //    loginLayout.addComponent(dbfill);



        }else{
            userGreetingsLabel= new Label("Üdvözlünk, " + user.getName());
            logoutButton= new Button("Kijelentkezés");
            logoutButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    logout();
                    getUI().getNavigator().navigateTo(MainView.VIEWID);
                }
            });

            loginLayout.addComponent(userGreetingsLabel);
            loginLayout.addComponent(logoutButton);
        }
    }

    private void logout(){
        ((MyUI)getUI().getCurrent()).setLoggedInUser(null);
    }

    private boolean isCustomer(){
        boolean isCustomer=((MyUI)getUI().getCurrent()).getLoggedInUser() instanceof Customer;
        return isCustomer;
    }

    private boolean isAgent(){
        return ((MyUI)getUI().getCurrent()).getLoggedInUser() instanceof Agent;
    }

    private void buildMenuLayout(){
        VerticalMenu verticalMenu= new VerticalMenu();

        verticalMenu.addButton("Bemutatkozás", "IntroductionView");
        verticalMenu.addButton("Szolgáltatások", "ServicesView");
        verticalMenu.addButton("Akciók", "akciókView");

        if(isCustomer()){
            verticalMenu.addButton("Hibabejelentés", "FaultSenderView");
            verticalMenu.addButton("Számlák", BillsView.VIEWID);
        }
        if(isAgent()){
            verticalMenu.addButton("Ügyféladatok módosítása","ügyféladatokMódosításaView");;
        }
        verticalMenu.addButton("Kapcsolat", "ContactView");

        menuLayout=verticalMenu.getBuiltMenu();
    }


}
