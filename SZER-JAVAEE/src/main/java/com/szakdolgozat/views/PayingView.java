package com.szakdolgozat.views;

import com.szakdolgozat.MyUI;
import com.szakdolgozat.ejbs.BillBean;
import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import javax.inject.Inject;
import java.util.Random;

/**
 * Created by Ákos on 2016.03.12..
 */
@CDIView("PayingView")
public class PayingView extends AbstractView{

    public static String VIEWID="PayingView";

    private Panel payingPanel;
    private FormLayout formLayout;
    private TextField name;
    private TextField cardNumber;
    private TextField expDate;
    private PasswordField CVC;
    private TextField bank;
    private Label informLabel;

    private Button payButton;
    private String amount;

    @Inject
    BillBean billBean;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        super.enter(viewChangeEvent);
        if(viewChangeEvent.getParameters()!=null){
            amount=viewChangeEvent.getParameters();
            setInformLabel();
        }
    }

    @Override
    public void afterEnter() {
        initPanel();
    }

    private void initPanel(){
        payingPanel=new Panel();
        formLayout= new FormLayout();

        name=new TextField("Név:");
        cardNumber=new TextField("Kártyaszám:");
        expDate=new TextField("Lejárati dátum:");
        CVC=new PasswordField("CVC:");
        bank=new TextField("Kibocsátó bank:");
        payButton=new Button("Jóváhagyás");
        informLabel=new Label("A levonásra kerülő összeg: " + amount +" HUF");

        formLayout.addComponent(name);
        formLayout.addComponent(cardNumber);
        formLayout.addComponent(expDate);
        formLayout.addComponent(bank);
        formLayout.addComponent(CVC);
        formLayout.addComponent(informLabel);
        formLayout.addComponent(payButton);

        payButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(new Random().nextDouble()>0.3){
                    billBean.makeBillsPayed(((MyUI)getUI().getCurrent()).getLoggedInUser().getName());
                    getUI().getNavigator().navigateTo(BillsView.VIEWID);
                }else{
                    Notification notification= new Notification("Hibás kártya adatok!", Notification.Type.HUMANIZED_MESSAGE);
                    notification.setDelayMsec(5000);
                    notification.show(getUI().getPage());
                }
            }
        });

        payingPanel.setContent(formLayout);

        menuContent.addComponent(payingPanel);

    }

    private void setInformLabel(){
        informLabel.setValue("A levonásra kerülő összeg: " + amount +" HUF");
    }
}
