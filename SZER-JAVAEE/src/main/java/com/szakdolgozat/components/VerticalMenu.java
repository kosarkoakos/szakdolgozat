package com.szakdolgozat.components;

import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ákos on 2016.02.20..
 */
public class VerticalMenu extends VerticalLayout {

    private List<Button> menuButtons;
    public VerticalMenu(){
        menuButtons= new ArrayList<>();
    }

    public void addButton(String text, String destView){
        Button newButton= new Button (text);
        newButton.setWidth("150px");
        newButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                getUI().getNavigator().navigateTo(destView);
            }
        });
        menuButtons.add(newButton);
    }


    public VerticalLayout getBuiltMenu(){
        for(Button button : menuButtons){
            addComponent(button);
        }

        return (VerticalLayout)this;
    }

}
