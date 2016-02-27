package com.szakdolgozat;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;

import com.szakdolgozat.entities.person.ApplicationUser;
import com.szakdolgozat.views.MainView;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
@CDIUI("")
@Theme("mytheme")
@Widgetset("com.szakdolgozat.MyAppWidgetset")
@PreserveOnRefresh
public class MyUI extends UI {

    @Inject
    private CDIViewProvider viewProvider;

    private Navigator navigator;

    private ApplicationUser loggedInUser;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        MainView mainView= new MainView();

        navigator= new Navigator(this, mainView);
        navigator.addProvider(viewProvider);

        setContent(mainView);

        navigator.navigateTo(MainView.VIEWID);
    }

    public ApplicationUser getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(ApplicationUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
