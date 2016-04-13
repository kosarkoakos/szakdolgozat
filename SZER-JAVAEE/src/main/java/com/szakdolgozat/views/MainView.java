package com.szakdolgozat.views;

import com.vaadin.cdi.CDIView;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;

/**
 * Created by Ákos on 2016.02.14..
 */
@CDIView("MainView")
public class MainView extends AbstractView {

    public static final String VIEWID="MainView";

    Label introLabel= new Label();
    String intro = "Kedves felhasználó!" +
            "<br>Köszöntünk oldalunkon! Az oldalon való navigáláshoz a bal oldali menüsort használhatod. Amennyiben kérdésed van, azt a Kapcsolat menüpontban található módokon felkeresve minket teheted fel nekünk." +
            "<br>Kellemes böngészést kívánunk!";

    @Override
    public void afterEnter(){
        introLabel.setValue(intro);
        introLabel.setContentMode(ContentMode.HTML);
        menuContent.addComponent(introLabel);
    }
}
