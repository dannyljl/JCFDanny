/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package woordenapplicatie.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import javafx.event.ActionEvent;

/**
 *
 * @author danny
 */
public class WoordenBrein {

    private LinkedList<String> allwords = new LinkedList<String>();
    
    private TreeSet<String> usedWords = new TreeSet<String>();

    private Map<String, Integer> hmap = new HashMap<String, Integer>();

    private String text;
    

    public WoordenBrein(String Text) {
        text = Text;
        fillallWords();
    }

    public void aantalButton() {
        
    }

    public TreeSet<String> sorteerButton() {
        // collectie aanmaken die meteen sorteerd kijk bij les 1 modules
        return usedWords;
    }

    public Map<Integer,List<String>> frequentieButton() {
        hmap = new HashMap<String, Integer>();
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

        return sorted;

        //https://stackoverflow.com/questions/3019376/shortcut-for-adding-to-list-in-a-hashmap
    }

    public Map<String,List<Integer>> concordatieButton() {
        String[] splitted = text.split(" |,", 0);
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
        return conMap;
    }

    private void fillallWords() {
        String[] splitted = text.split(" |\n|,", 0);
        allwords = new LinkedList<String>();
        for (String word : splitted) {
            if (!word.equals("")) {
                allwords.add(word);
                usedWords.add(word);
                // vervangen met collectie die deen duplicates heeft.
            }
        }
        //https://stackoverflow.com/questions/14602062/java-string-split-removed-empty-values
        //https://stackoverflow.com/questions/17103275/how-to-settext-in-a-textarea-from-an-arraylist
    }
    
    public int getAllSize(){
        return allwords.size();
    }
    
    public int getUsedSize(){
        return usedWords.size();
    }
    
    public List<String> getAllList(){
        List<String> newList = new ArrayList<String>();
        for(String word : allwords){
            newList.add(word);
        }
        return newList;
    }

}
