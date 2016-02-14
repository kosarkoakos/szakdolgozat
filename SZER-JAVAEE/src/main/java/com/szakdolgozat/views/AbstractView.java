package com.szakdolgozat.views;

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
    protected VerticalLayout menuBar;
    protected VerticalLayout menuContent;
    Button loginButton= new Button("Bejelentkezés");


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
        menuBar=new VerticalLayout();
        menuContent= new VerticalLayout();

        topStripe.addComponent(image);
        topStripe.addComponent(loginBox);

        bottomStripe.addComponent(menuBar);
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
