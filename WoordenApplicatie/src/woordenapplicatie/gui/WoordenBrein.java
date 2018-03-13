/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package woordenapplicatie.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javafx.event.ActionEvent;

/**
 *
 * @author danny
 */
public class WoordenBrein {

    private ArrayList<String> allwords = new ArrayList<String>();
    
    private ArrayList<String> usedWords = new ArrayList<String>();

    private Map<String, Integer> hmap = new HashMap<String, Integer>();

    private String text;
    

    public WoordenBrein(String Text) {
        text = Text;
        fillallWords();
    }

    public void aantalButton() {
        String[] splitted = text.split(" |\n|,", 0);
        allwords = new ArrayList<String>();
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
    }

    public List<String> sorteerButton() {
        fillallWords();
        allwords.sort(Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
        return allwords;
    }

    public Map<Integer,List<String>> frequentieButton() {
        fillallWords();
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
        allwords = new ArrayList<String>();
        for (String word : splitted) {
            if (!word.equals("")) {
                allwords.add(word);
            }
        }
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
