/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import BussinesLogic.Kutxazaina;
import DataBase.KontuDatuBasea;
import Domain.Bezeroa;
import Domain.Kontua;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author urtzi
 */
public class testSarreraErreserbatu {
    private GUI.sarreraErreserba bankua;
    private Kontua kont;
    
    public testSarreraErreserbatu() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws InterruptedException {
        Bezeroa bez= new Bezeroa(123456,1234,"Ohian Arroyo", 666666666);
        kont= KontuDatuBasea.instantzia().lortuKontua(111);
        Kutxazaina kutxa= new Kutxazaina();
        kutxa.setKontua(kont);
        kutxa.sartuTxartela(bez.getID(), bez.getPass());
        String herria="Donostia";
        String zine= "Principe" ;
        String film="Argo";
        String data="18-12-2016" ;
        bankua= new GUI.sarreraErreserba(bez,kont,kutxa, herria,zine,film,data );
        Thread.sleep(1000);
        
       
    }
    
    @After
    public void tearDown() {
        bankua=null;
        
    }
    
    
    public boolean testAukeratuOrdua( ) throws AWTException, InterruptedException{
        
       bankua.aukeratuButaka();
       return bankua.getErrorea();
          
    }

     @Test
    public void testButakaOkerra() throws AWTException, InterruptedException{
        setUp();
        boolean a=testAukeratuOrdua();
        assertTrue(a);
        tearDown();
    }
   @Test
    public void testButakaZuzena() throws AWTException, InterruptedException{
       String butakaLibratu= "UPDATE Sarrera SET Saldua= 0 WHERE Zenbakia = 13 ";
        KontuDatuBasea.instantzia().sartuButaka(butakaLibratu);
        setUp();
       boolean a=testAukeratuOrdua();
       assertFalse(a);
       
      tearDown();
    }
  
     @Test
    public void testButakaOperazioa() throws AWTException, InterruptedException{
        
        String butakaLibratu= "UPDATE Sarrera SET Saldua= 0 WHERE Zenbakia = 13 ";
        KontuDatuBasea.instantzia().sartuButaka(butakaLibratu);
        setUp();
        int hasierakoSaldo=  (int)kont.getSaldoZaharra() - 8;
        System.out.println(hasierakoSaldo);
        boolean a=testAukeratuOrdua();
        int bukaeraSaldo=(int) kont.saldoBerria();
        assertEquals(hasierakoSaldo, bukaeraSaldo);
        tearDown();
    }
    
   
    
}

