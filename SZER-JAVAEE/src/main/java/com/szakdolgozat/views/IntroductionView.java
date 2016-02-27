package com.szakdolgozat.views;

import com.vaadin.cdi.CDIView;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

/**
 * Created by Ákos on 2016.02.27..
 */
@CDIView("IntroductionView")
public class IntroductionView extends AbstractView {

    public static final String VIEWID="IntroductionView";

    Label introductionLabel;
    String introductionText;

    @Override
    public void afterEnter(){
        introductionText= "Kedves Látogató! <br/> Köszöntünk oldalunkon!" +
                "<br/> Mi a Kapcsolatteremtők Zrt vagyunk. Szolgáltatásainkkal ahoz segítünk téged hozzá, hogy a" +
                " tőled távolban élő rokonaiddal, barátaiddal és leendő barátaiddal kapcsolatot tudj tartani a modern" +
                " techinaikai eszközök segítségével, amikhez mi adjuk az összeköttetést." +
                "<br/> Cégünk 2016 óta foglalkozik telefon, inetrnet és kábeltelevízió-szolgáltatások nyújtásával lakossági ügyfelek" +
                " részére. Csapatunk tagjai pontos és precíz teknikusok, rendzsermérnökök, akik szaktudása adja a szolgáltatásaink erejét" +
                " és megbízhatóságát. Szolgáltatásainkra előfizetni regosztráció és bejelentkezés után tudsz, az előfizetések" +
                " menüpontban. Bármifél kérdésed van, vedd fel bátran velük a kapcsolatot a Kapcsolat menüpontban található " +
                "elérhetőségek bármelyikén, mi készségesen állunk a rendelkezésedre!" +
                "<br/>Üdvözlettel," +
                "<br/>a Kapcsolatteremtők Zrt csapata";
        introductionLabel= new Label();
        introductionLabel.setContentMode(ContentMode.HTML);
        introductionLabel.setValue(introductionText);
        menuContent.addComponent(introductionLabel);
    }
}
