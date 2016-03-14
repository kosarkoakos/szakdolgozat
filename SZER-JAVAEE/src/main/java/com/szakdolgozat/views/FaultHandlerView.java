package com.szakdolgozat.views;

import com.szakdolgozat.dto.FaultDTO;
import com.szakdolgozat.ejbs.DateFormatConverter;
import com.szakdolgozat.ejbs.FaultBean;
import com.szakdolgozat.ejbs.TableContentHandlerBean;
import com.szakdolgozat.entities.ReportedFault;
import com.szakdolgozat.enums.ServiceType;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.*;

import javax.inject.Inject;

/**
 * Created by Ákos on 2016.03.14..
 */
@CDIView("FaultHandlerView")
public class FaultHandlerView extends AbstractView {

    public static String VIEWID="FaultHandlerView";

    private HorizontalLayout components;
    private VerticalLayout faultSelector;
    private Table faultsTable;
    private ComboBox types;
    private FormLayout faultForm;
    private Label customerName;
    private Label title;
    private Label description;
    private Label reportedDate;
    private TextArea solution;
    private Button saveButton;



    private int faultsTableLength=10;
    Long selectedFaultId;
    ServiceType selectedType=ServiceType.Televízió;
    String faultsTableWidth="350px";
    String solutionTAWidth="250px";

    @Inject
    TableContentHandlerBean tchb;

    @Inject
    FaultBean faultBean;

    @Inject
    DateFormatConverter dfc;


    @Override
    public void afterEnter() {
        components=new HorizontalLayout();
        initFaultSelector();
        initFaultForm();
        buildLayout();
        menuContent.addComponent(components);
    }

    private void initFaultSelector(){
        faultSelector= new VerticalLayout();
        types= new ComboBox();

        types.addItem(ServiceType.Televízió);
        types.addItem(ServiceType.Telefon);
        types.addItem(ServiceType.Internet);
        types.addItem(ServiceType.Egyéb);
        types.setNullSelectionAllowed(true);
        types.setValue(ServiceType.Televízió);

        types.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                selectedType=(ServiceType) valueChangeEvent.getProperty().getValue();
                faultSelector.removeComponent(faultsTable);
                System.out.println("seletcedType: " + selectedType);
                initFaultsTable();
                faultSelector.addComponent(faultsTable);

            }
        });

        initFaultsTable();
    }

    private void initFaultForm(){
        faultForm=new FormLayout();
        customerName=new Label();
        customerName.setCaption("Bejelentő:");
        title=new Label();
        title.setCaption("Tárgy:");
        description=new Label();
        description.setCaption("Hibaleírás:");
        reportedDate=new Label();
        reportedDate.setCaption("Beérkezett:");
        solution=new TextArea("Megoldás: ");
        solution.setRequired(true);
        solution.setWidth(solutionTAWidth);
        saveButton=new Button("Lezár");

        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                faultBean.makeFaultSolved(selectedFaultId,solution.getValue());
            }
        });

        faultForm.addComponent(customerName);
        faultForm.addComponent(title);
        faultForm.addComponent(description);
        faultForm.addComponent(reportedDate);
        faultForm.addComponent(solution);
        faultForm.addComponent(saveButton);
    }

    private void initFaultsTable(){
        faultsTable=new Table();
        faultsTable.setImmediate(true);
        faultsTable.setPageLength(faultsTableLength);
        faultsTable.setSelectable(true);
        faultsTable.setWidth(faultsTableWidth);

        faultsTable.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                if(valueChangeEvent!=null) {
                    if(valueChangeEvent.getProperty().getValue()!=null) {
                        selectedFaultId = ((FaultDTO) valueChangeEvent.getProperty().getValue()).getId();
                        if (selectedFaultId != null) {
                            setFaultInformation(selectedFaultId);
                        }
                    }
                }

            }
        });

        setFaultsTableDS(selectedType);
    }

    private void buildLayout(){
        faultSelector.addComponent(types);
        faultSelector.addComponent(faultsTable);
        components.addComponent(faultSelector);
        components.addComponent(faultForm);
    }

    private void setFaultsTableDS(ServiceType type){
        IndexedContainer ic=tchb.makeFaultsIndexedContainer(type);
        faultsTable.setContainerDataSource(ic);
    }

    private void setFaultInformation(Long id){
        ReportedFault fault=faultBean.getFaultById(id);
        System.out.println("setFaultInformation: fault.title: " + fault.getTitle() );
        customerName.setValue(fault.getReporter().getName());
        title.setValue(fault.getTitle());
        description.setValue(fault.getDescription());
        reportedDate.setValue(dfc.convertTofullDateTime(fault.getReportDate()));
        solution.setValue("");
    }


}
