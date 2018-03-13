/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package woordenapplicatie.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author danny
 */
public class WoordenBreinTest {
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
    
    private WoordenBrein brein;
    
    public WoordenBreinTest() {
        brein = new WoordenBrein(DEFAULT_TEXT);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of aantalButton method, of class WoordenBrein.
     */
    @Test
    public void testAantalButton() {
        brein.aantalButton();
        assertEquals(brein.getAllSize(),68);
        assertEquals(brein.getUsedSize(),29);
    }

    /**
     * Test of sorteerButton method, of class WoordenBrein.
     */
    @Test
    public void testSorteerButton() {
       List<String> oldWords = brein.getAllList();
       assertFalse(brein.sorteerButton() == oldWords);
    }

    /**
     * Test of frequentieButton method, of class WoordenBrein.
     */
    @Test
    public void testFrequentieButton() {
        assertTrue(brein.frequentieButton().containsKey(9));
        assertTrue(brein.frequentieButton().containsKey(6));
        assertFalse(brein.frequentieButton().containsKey(8));
    }

    /**
     * Test of concordatieButton method, of class WoordenBrein.
     */
    @Test
    public void testConcordatieButton() {
        List<Integer> values = new ArrayList<Integer>();
        values.add(14);
        assertTrue(brein.concordatieButton().containsKey("glazenkas"));
        assertEquals(brein.concordatieButton().get("glazenkas"),values );
    }
    
}
