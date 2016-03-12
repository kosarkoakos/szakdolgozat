package com.szakdolgozat.views;

import com.szakdolgozat.MyUI;
import com.szakdolgozat.dto.NamePriceDTO;
import com.szakdolgozat.ejbs.TableContentHandlerBean;
import com.szakdolgozat.entities.person.Customer;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ákos on 2016.03.12..
 */
@CDIView("BillsView")
public class BillsView extends AbstractView{

    public static String VIEWID="BillsView";

    private HorizontalLayout verticalColumns;
    private VerticalLayout leftColumn;
    private VerticalLayout rightColumn;

    private Table billsTable;
    private IndexedContainer billsContainer;

    private Label finalPrice;
    private Button payButton;

    private int tableLength=5;

    private List<String> selectedBills= new ArrayList<>();
    private Integer sumPrice=0;

    @Inject
    TableContentHandlerBean tchb;


    @Override
    public void afterEnter() {
        initLayouts();
        initBillsTable();
        initRightColumn();

        menuContent.addComponent(verticalColumns);
        verticalColumns.addComponent(leftColumn);
        verticalColumns.addComponent(rightColumn);
        leftColumn.addComponent(billsTable);
        rightColumn.addComponent(finalPrice);
        rightColumn.addComponent(payButton);
    }

    private void initLayouts(){
        verticalColumns= new HorizontalLayout();
        leftColumn=new VerticalLayout();
        rightColumn=new VerticalLayout();
    }

    private void initBillsTable(){
        billsTable= new Table();
        billsTable.setMultiSelect(true);
        billsTable.setPageLength(tableLength);

        billsContainer=tchb.makeBillsIndexedContainer((Customer)((MyUI)getUI().getCurrent()).getLoggedInUser());
        billsTable.setContainerDataSource(billsContainer);

        billsTable.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                String selected =  (String)valueChangeEvent.getProperty().getValue();
             //   Integer selectedPrice =  ((NamePriceDTO)valueChangeEvent.getProperty().getValue()).getPrice();//selected lehet az, amiről éppen levette a kiválasztottságot
                if(selectedBills.contains(selected)){
                    selectedBills.remove(selected);
                   // sumPrice-=selectedPrice;
                    setFinalPriceLabelContent(sumPrice+10);

                }else{
                    selectedBills.add(selected);
                   // sumPrice+=selectedPrice;
                    setFinalPriceLabelContent(sumPrice+10);
                }

                System.out.println("selectedbills mérete: "+ selectedBills.size());
            }
        });
    }

    private void initRightColumn(){
        finalPrice=new Label("Végösszeg: 0 HUF");
        payButton= new Button("Befizet");


    }

    private void setFinalPriceLabelContent(int price){
        finalPrice.setValue("Végösszeg: " + price + "HUF");
    }
}
