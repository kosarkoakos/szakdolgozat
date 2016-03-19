package com.szakdolgozat.views;

import com.szakdolgozat.MyUI;
import com.szakdolgozat.ejbs.FaultBean;
import com.szakdolgozat.entities.ReportedFault;
import com.szakdolgozat.entities.person.Customer;
import com.szakdolgozat.enums.ServiceType;
import com.vaadin.cdi.CDIView;
import com.vaadin.ui.*;

import javax.inject.Inject;

/**
 * Created by Ákos on 2016.03.06..
 */
@CDIView("FaultSenderView")
public class FaultSenderView extends AbstractView {

    public static final String VIEWID="FaultSenderView";

    Panel panel;
    FormLayout faultForm;
    ComboBox serviceType;
    TextField faultTitle;
    TextArea faultDescription;
    Button sendIn;

    String panelWidth="400px";

    @Inject
    FaultBean faultBean;

    @Override
    public void afterEnter() {

        initForm();

        faultForm.addComponent(serviceType);
        faultForm.addComponent(faultTitle);
        faultForm.addComponent(faultDescription);
        faultForm.addComponent(sendIn);


        panel.setContent(faultForm);
        panel.setWidth(panelWidth);

        menuContent.addComponent(panel);

        menuContent.setComponentAlignment(panel, Alignment.TOP_CENTER);



    }

    private void initForm(){
        panel=new Panel("Hibabejelentés");

        faultForm=new FormLayout();
        serviceType= new ComboBox();
        serviceType.setCaption("Jelleg:");
        serviceType.addItem(ServiceType.Televízió);
        serviceType.addItem(ServiceType.Telefon);
        serviceType.addItem(ServiceType.Internet);
        serviceType.addItem(ServiceType.Egyéb);
        serviceType.setNullSelectionAllowed(false);


        faultTitle= new TextField();
        faultTitle.setCaption("Tárgy:");
        faultDescription= new TextArea();
        faultDescription.setCaption("Hibaleírás:");

        sendIn= new Button("Beküld");

        sendIn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ReportedFault reportedFault=new ReportedFault();
                reportedFault.setReporter((Customer)((MyUI)getUI().getCurrent()).getLoggedInUser());
                reportedFault.setServiceType((ServiceType) serviceType.getValue());
                reportedFault.setTitle(faultTitle.getValue());
                reportedFault.setDescription(faultDescription.getValue());
                reportedFault.setReportDate(java.util.Calendar.getInstance().getTime());
                faultBean.saveReportedFault(reportedFault);
                cleanForm();
            }
        });
    }

    private void cleanForm(){
        faultTitle.setValue("");
        faultDescription.setValue("");
    }
}
