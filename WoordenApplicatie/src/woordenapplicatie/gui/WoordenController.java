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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.SortedSet;
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

    private ArrayList<String> allwords = new ArrayList<String>();

    private HashMap<String, Integer> hmap = new HashMap<String, Integer>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taInput.setText(DEFAULT_TEXT);
    }

    @FXML
    private void aantalAction(ActionEvent event) {
        String[] splitted = DEFAULT_TEXT.split(" |\n|,", 0);
        allwords = new ArrayList<String>();
        ArrayList<String> usedWords = new ArrayList<String>();
        for (String word : splitted) {
            if (!word.equals("")) {
                allwords.add(word);
                if (!usedWords.contains(word)) {
                    usedWords.add(word);
                }
            }
        }

        //https://stackoverflow.com/questions/14602062/java-string-split-removed-empty-values
        //https://stackoverflow.com/questions/17103275/how-to-settext-in-a-textarea-from-an-arraylist
        taOutput.appendText("allwords : " + allwords.size());
        taOutput.appendText("newWords :" + usedWords.size());
    }

    @FXML
    private void sorteerAction(ActionEvent event) {
        String[] splitted = DEFAULT_TEXT.split(" |\n|,", 0);
        allwords = new ArrayList<String>();
        for (String word : splitted) {
            if (!word.equals("")) {
                allwords.add(word);
            }
        }
        allwords.sort(Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
        for (String word : allwords) {
            taOutput.appendText(word);
            taOutput.appendText("\n");
        }
    }

    @FXML
    private void frequentieAction(ActionEvent event) {
        String[] splitted = DEFAULT_TEXT.split(" |\n|,", 0);
        allwords = new ArrayList<String>();
        for (String word : splitted) {
            if (!word.equals("")) {
                allwords.add(word);
            }
        }
        for(String word : allwords){
            if(hmap.containsKey(word)){
                hmap.put(word, hmap.get(word) + 1);
            }
            else{
                hmap.put(word,1);
            }
        }
        
        hmap.entrySet().stream()
        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
        .forEach(System.out::println);
        
//        for(Map.Entry<String, Integer> entry : hmap.entrySet()){
//           entry.getKey();
//           entry.getValue();
//           taOutput.appendText("woord:" + entry.getKey());
//           taOutput.appendText(" " + entry.getValue());
//           taOutput.appendText("\n");
//        }
    }

    @FXML
    private void concordatieAction(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
