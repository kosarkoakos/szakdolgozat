package com.szakdolgozat.views;

import com.szakdolgozat.MyUI;
import com.szakdolgozat.dto.NamePriceDTO;
import com.szakdolgozat.ejbs.IDStorageBean;
import com.szakdolgozat.ejbs.TableContentHandlerBean;
import com.szakdolgozat.entities.Bill;
import com.szakdolgozat.entities.person.Customer;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
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

    private ArrayList<Long> selectedBillsIDs= new ArrayList<>();
    private Integer sumPrice=0;
    private String ids;

    @Inject
    TableContentHandlerBean tchb;

    @Inject
    IDStorageBean storageBean;


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
        rightColumn.setMargin(new MarginInfo(false,false,false,true));
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
                selectedBillsIDs.clear();
                sumPrice=0;
                Collection selected =  (Collection)valueChangeEvent.getProperty().getValue();

                if(!selected.isEmpty()){
                    Object[] array=selected.toArray();

                    for (int i=0;i<array.length;i++) {
                        Bill sBill = (Bill) array[i];
                        selectedBillsIDs.add(sBill.getBillId());
                        sumPrice+=sBill.getAmount();
                    }
                }
                setFinalPriceLabelContent(sumPrice);
            }
        });
    }

    private void initRightColumn(){
        finalPrice=new Label("Végösszeg: 0 HUF");
        payButton= new Button("Befizet");

        payButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                storageBean.addList(((MyUI)getUI().getCurrent()).getLoggedInUser().getName(),selectedBillsIDs);
                getUI().getNavigator().navigateTo(PayingView.VIEWID + "/" +sumPrice.toString());
            }
        });


    }

    private void setFinalPriceLabelContent(int price){
        finalPrice.setValue("Végösszeg: " + price + " HUF");
    }

}
