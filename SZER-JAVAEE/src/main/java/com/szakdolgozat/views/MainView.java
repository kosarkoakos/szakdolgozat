package com.szakdolgozat.views;

import com.vaadin.cdi.CDIView;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;

/**
 * Created by Ákos on 2016.02.14..
 */
@CDIView("MainView")
public class MainView extends AbstractView {

    public static final String VIEWID="MainView";

    Label introLabel= new Label();
    String intro = "Ez egy bemutatkozó szöveg. Ez egy bemutatkozó szöveg. Ez egy bemutatkozó szöveg." +
            "Ez egy bemutatkozó szöveg. Ez egy bemutatkozó szöveg.\n Ez egy bemutatkozó szöveg. Ez egy bemutatkozó szöveg. Ez egy bemutatkozó szöveg. Ez egy bemutatkozó szöveg. Ez egy bemutatkozó szöveg.";

    @Override
    public void afterEnter(){
        TextArea introduction = new TextArea();

        introduction.setCaption("Ez a bemutatkozó oldal");
        introduction.setValue(intro);

        introLabel.setValue(intro);
        menuContent.addComponent(introLabel);
    }
}
