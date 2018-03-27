package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author frankcoenen
 */
public class WoordenController implements Initializable {

    private static final String DEFAULT_TEXT = "Een, twee, drie, vier\n"
            + "Hoedje van, hoedje van\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "Heb je dan geen hoedje meer\n"
            + "Maak er één van bordpapier\n"
            + "Eén, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van, hoedje van\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "En als het hoedje dan niet past\n"
            + "Zetten we 't in de glazenkas\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier";
    
    private WoordenBrein brein = new WoordenBrein(DEFAULT_TEXT);

    @FXML
    private Button btAantal;
    @FXML
    private TextArea taInput;
    @FXML
    private Button btSorteer;
    @FXML
    private Button btFrequentie;
    @FXML
    private Button btConcordantie;
    @FXML
    private TextArea taOutput;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taInput.setText(DEFAULT_TEXT);
    }

    @FXML
    private void aantalAction(ActionEvent event) {
        brein.aantalButton();
        taOutput.appendText("allwords : " + brein.getAllSize());
        taOutput.appendText("newWords :" + brein.getUsedSize());
    }

    @FXML
    private void sorteerAction(ActionEvent event) {
        for (String word : brein.sorteerButton()) {
            taOutput.appendText(word);
            taOutput.appendText("\n");
        }
    }

    @FXML
    private void frequentieAction(ActionEvent event) {
        for (Map.Entry<Integer, List<String>> entry : brein.frequentieButton().entrySet()) {
            taOutput.appendText(entry.getKey() + "= " + entry.getValue() + "\n");
        }
        
    }

    @FXML
    private void concordatieAction(ActionEvent event) {
        for (Map.Entry<String, List<Integer>> entry : brein.concordatieButton().entrySet()) {
            taOutput.appendText(entry.getKey() + "= " + entry.getValue() + "\n");
        }
    }


}
