package com.szakdolgozat.views;

import com.szakdolgozat.components.VerticalMenu;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Ákos on 2016.02.14..
 */

public class AbstractView extends VerticalLayout implements View {

    protected VerticalLayout mainLayout= new VerticalLayout();

    protected HorizontalLayout topStripe;
    protected HorizontalLayout bottomStripe;

    protected VerticalLayout image;
    protected VerticalLayout loginBox;
    protected MenuBar menuBar;
    protected VerticalLayout menuLayout;

    protected VerticalLayout menuContent;


    protected Button loginButton= new Button("Bejelentkezés");

    protected Button introductionButton;
    protected Button servicesButton;
    protected Button salesButton;
    protected Button contatcButton;


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
        loginBox=new VerticalLayout();
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
        menu.addButton("Kapcsolat2", "kapcsolatView");

        topStripe.addComponent(image);
        topStripe.addComponent(loginBox);

        bottomStripe.addComponent(menu.getBuiltMenu());
        bottomStripe.addComponent(menuContent);


        loginButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                getUI().getNavigator().navigateTo(MainView.VIEWID);
            }
        });

        //ha ben van jelentkezve szöveg, ha nincs egy kétsoros form kerül a loginboxba
        //a menübár tartalma szintén bejelentkezettség állapota alapján már itt összeállhat
        loginBox.addComponent(loginButton);

        mainLayout.addComponent(topStripe);
        mainLayout.addComponent(bottomStripe);

        addComponent(mainLayout);


    }

}
