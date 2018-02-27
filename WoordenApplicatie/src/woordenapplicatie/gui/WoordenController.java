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

    private Map<String, Integer> hmap = new HashMap<String, Integer>();

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
        fillallWords();
        allwords.sort(Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
        for (String word : allwords) {
            taOutput.appendText(word);
            taOutput.appendText("\n");
        }
    }

    @FXML
    private void frequentieAction(ActionEvent event) {
        fillallWords();
        Map<Integer, List<String>> newMap = new HashMap<Integer, List<String>>();
        for (String word : allwords) {
            if (hmap.containsKey(word)) {
                hmap.put(word, hmap.get(word) + 1);
            } else {
                hmap.put(word, 1);
            }
        }

        for (Map.Entry<String, Integer> entry : hmap.entrySet()) {
            newMap.computeIfAbsent(entry.getValue(), k -> new ArrayList<>()).add(entry.getKey());
        }

        Map<Integer, List<String>> sorted = new TreeMap<Integer, List<String>>(newMap).descendingMap();

        for (Map.Entry<Integer, List<String>> entry : sorted.entrySet()) {
            taOutput.appendText(entry.getKey() + "= " + entry.getValue() + "\n");
        }

    }

    @FXML
    private void concordatieAction(ActionEvent event) {
        String[] splitted = DEFAULT_TEXT.split(" |,", 0);
        Map<String, List<Integer>> conMap = new HashMap<String, List<Integer>>();
        int line = 1;
        for (String word : splitted) {
            if (!word.equals("")) {
                if (word.contains("\n")) {
                    line++;
                    String[] newSplit = word.split("\n");
                    conMap.computeIfAbsent(newSplit[0], k -> new ArrayList<>()).add(line - 1);
                    if (!newSplit[1].equals("")) {
                        conMap.computeIfAbsent(newSplit[1], k -> new ArrayList<>()).add(line);
                    }
                } else {
                    conMap.computeIfAbsent(word, k -> new ArrayList<>()).add(line);
                }
            }
        }
        for (Map.Entry<String, List<Integer>> entry : conMap.entrySet()) {
            taOutput.appendText(entry.getKey() + "= " + entry.getValue() + "\n");
        }
    }

    private void fillallWords() {
        String[] splitted = DEFAULT_TEXT.split(" |\n|,", 0);
        allwords = new ArrayList<String>();
        for (String word : splitted) {
            if (!word.equals("")) {
                allwords.add(word);
            }
        }
    }

}
