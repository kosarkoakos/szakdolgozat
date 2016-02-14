package com.szakdolgozat.views;

import com.vaadin.cdi.CDIView;
import com.vaadin.ui.Label;

/**
 * Created by Ákos on 2016.02.14..
 */
@CDIView("MainView")
public class MainView extends AbstractView {

    public static final String VIEWID="MainView";

    Label testLabel= new Label("Ez az oldal tartalma.");

    @Override
    public void afterEnter(){

        menuContent.addComponent(testLabel);
    }
}
