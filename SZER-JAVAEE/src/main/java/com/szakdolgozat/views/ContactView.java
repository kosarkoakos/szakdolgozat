package com.szakdolgozat.views;

import com.vaadin.cdi.CDIView;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

/**
 * Created by Ákos on 2016.02.27..
 */
@CDIView("ContactView")
public class ContactView extends AbstractView {

    public static final String VIEWID="ContactView";

    private Label contactLabel;
    private String contactText;


    @Override
    public void afterEnter() {
        contactText="Elérhetőségeink:" +
                "<br/>Cím: Budapest, 5555, Budapest út 33." +
                "<br/>Levelezési cím: Budapest, 5555, Budapest út 33. Pf 55." +
                "<br/>Telefonszám: +36-00-123-4567" +
                "<br/>Ügyfélszolgálat email: ugyfelszolgalat@kapcsolatteremtok.hu";
        contactLabel=new Label();
        contactLabel.setContentMode(ContentMode.HTML);
        contactLabel.setValue(contactText);
        menuContent.addComponent(contactLabel);
    }
}
