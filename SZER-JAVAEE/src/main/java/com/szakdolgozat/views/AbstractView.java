package com.szakdolgozat.views;

import com.szakdolgozat.MyUI;
import com.szakdolgozat.components.VerticalMenu;
import com.szakdolgozat.entities.person.ApplicationUser;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;

/**
 * Created by Ákos on 2016.02.14..
 */

public class AbstractView extends VerticalLayout implements View {

    protected VerticalLayout mainLayout= new VerticalLayout();

    protected HorizontalLayout topStripe;
    protected HorizontalLayout bottomStripe;

    protected VerticalLayout image;
    protected MenuBar menuBar;
    protected VerticalLayout menuLayout;

    protected VerticalLayout menuContent;


    protected Button loginButton;

    protected Button introductionButton;
    protected Button servicesButton;
    protected Button salesButton;
    protected Button contatcButton;

    protected VerticalLayout loginLayout;
    protected Label userGreetingsLabel;
    protected TextField usernameTxtF;
    protected PasswordField passwordField;

    protected String imageWidth="710px";
    protected String loginLayoutWidth="180px";
    protected String menuWidth="150px";
    protected String menuContentWidth="740px";


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        boolean hasAcces= true;
        if(hasAcces){
            mainLayout.removeAllComponents();
            initDefaultComponents();
            afterEnter();
        }else {
      //      mainView aszerint, hogy be van-e jelentkezve
        }
    }

    public void afterEnter(){

    }

    private void initDefaultComponents() {

        topStripe=new HorizontalLayout();
        bottomStripe=new HorizontalLayout();

        image= new VerticalLayout();
        menuBar=new MenuBar();
        menuLayout= new VerticalLayout();
        menuContent= new VerticalLayout();


        /*
        MenuBar.Command commandToIntroduction = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {
                getUI().getNavigator().navigateTo("bemutatkozóView");
            }
        };

        MenuBar.Command commandToServices = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {
                getUI().getNavigator().navigateTo("szolgáltatásokView");
            }
        };

        MenuBar.Command commandToSales = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {
                getUI().getNavigator().navigateTo("akciókkView");
            }
        };

        MenuBar.Command commandToContact = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {
                getUI().getNavigator().navigateTo("kapcsolatView");
            }
        };

        menuBar.addItem("Bemutatkozás", commandToIntroduction);
        menuBar.addItem("Szolgáltatások", commandToServices);
        menuBar.addItem("Akciók", commandToSales);
        menuBar.addItem("Kapcsolat", commandToContact);

        */

        /*

        introductionButton = new Button("Bemutatkozás");
        servicesButton= new Button("Szolgáltatások");
        salesButton= new Button("Akciók");
        contatcButton = new Button("Kapcsolat");

        introductionButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                getUI().getNavigator().navigateTo("bemutatkozóView");
            }
        });

        servicesButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                getUI().getNavigator().navigateTo("szolgáltatásokView");
            }
        });

        salesButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                getUI().getNavigator().navigateTo("akciókView");
            }
        });

        contatcButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                getUI().getNavigator().navigateTo("kapcsolatView");
            }
        });

        menuLayout.addComponent(introductionButton);
        menuLayout.addComponent(servicesButton);
        menuLayout.addComponent(salesButton);
        menuLayout.addComponent(contatcButton);

        */

        VerticalMenu menu = new VerticalMenu();
        menu.addButton("Bemutatkozás", "bemutatkozóView");
        menu.addButton("Szolgáltatások", "szolgáltatásokView");
        menu.addButton("Akciók", "akciókView");
        menu.addButton("Kapcsolat", "kapcsolatView");

        Label t = new Label("Tesztlabel");
        image.addComponent(t);

        topStripe.addComponent(image);

        buildLoginBox();

        topStripe.addComponent(loginLayout);
        topStripe.setComponentAlignment(loginLayout,Alignment.TOP_RIGHT);


        bottomStripe.addComponent(menu.getBuiltMenu());
        bottomStripe.addComponent(menuContent);

        //ha ben van jelentkezve szöveg, ha nincs egy kétsoros form kerül a loginboxba
        //a menübár tartalma szintén bejelentkezettség állapota alapján már itt összeállhat

        mainLayout.addComponent(topStripe);
        mainLayout.addComponent(bottomStripe);



        addComponent(mainLayout);
    //    mainLayout.setStyleName("mytheme");
        mainLayout.setMargin(true);
        mainLayout.setWidth("1000px");
        setComponentAlignment(mainLayout,Alignment.MIDDLE_CENTER);

        image.setWidth(imageWidth);
        loginLayout.setWidth(loginLayoutWidth);
        menuLayout.setWidth(menuWidth);
        menuContent.setWidth(menuContentWidth);
        menu.setMargin(new MarginInfo(false,true,false,false));
        menuContent.setMargin(new MarginInfo(true,false,false,false));
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


            loginButton= new Button("Bejelentkezés");
            loginButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    //TODO bejelentkeztetést implementálni
                }
            });

            loginLayout.addComponent(loginButton);
            loginLayout.setComponentAlignment(loginButton,Alignment.TOP_RIGHT);

        }else{
            userGreetingsLabel= new Label("Üdvözlünk, " + user.getName());
            loginLayout.addComponent(userGreetingsLabel);
        }
    }

}
