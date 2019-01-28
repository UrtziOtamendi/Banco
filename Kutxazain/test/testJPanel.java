/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author urtzi
 */
public class testJPanel {
    private GUI.NirePanela bankua;

    
    public testJPanel() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws InterruptedException {
        bankua= new GUI.NirePanela();
   
        
       
    }
    
    @After
    public void tearDown() {
        bankua=null;
        
    }
    
    
    public boolean testBezSartu(String Id, String Pass) throws AWTException, InterruptedException{
        
       bankua.setBezeroa(Id);
       bankua.setPasahitza(Pass);
       bankua.sartuBezeroa();
       return bankua.getErrorea();
    
          
    }
      public int testBezSartuBiAldiz(String Id, String Pass) throws AWTException, InterruptedException{
        
       bankua.setBezeroa(Id);
       bankua.setPasahitza(Pass);
       bankua.sartuBezeroa();
       bankua.setBezeroa(Id);
       bankua.setPasahitza(Pass);
       bankua.sartuBezeroa();
       return bankua.kontuKop();
       
          
    }
       public void KontuBezSartu(String Id, String Pass) throws AWTException, InterruptedException{
        
       bankua.setBezeroa(Id);
       bankua.setPasahitza(Pass);
       bankua.sartuBezeroa();   
       bankua.aukeratuKontua();
    }
    public boolean sartuGordailua(String gord) throws AWTException, InterruptedException{
        
        bankua.sartuGordailua(gord);
        return bankua.getErrorea();
    }
     public boolean ateraDirua(String diru) throws AWTException, InterruptedException{
        
        bankua.ateraDirua(diru);
        return bankua.getErrorea();
    }
    public double saldoa() throws AWTException, InterruptedException{
        
        return  bankua.lortuSaldoa();
     
    }
     
   @Test
    public void testBazeroZuzena() throws AWTException, InterruptedException{
        setUp();
        boolean a=testBezSartu("123456","1234");
        assertFalse(a);
        tearDown();
    }
   
    @Test
    public void testBezeroLabur() throws AWTException, InterruptedException{
        setUp();
        boolean a=testBezSartu("12345","1234");
        assertTrue(a);
        tearDown();
    }
     @Test
    public void testBezeroNeg() throws AWTException, InterruptedException{
        setUp();
        boolean a=testBezSartu("-123456","1234");
        assertTrue(a);
        tearDown();
    }
     @Test
    public void testBezeroLetra() throws AWTException, InterruptedException{
        setUp();
        boolean a=testBezSartu("kaixo","1234");       
        assertTrue(a);
        tearDown();
    }
     @Test
    public void testPassLuze() throws AWTException, InterruptedException{
        setUp();
        boolean a=testBezSartu("123456","12345");
        assertTrue(a);
        tearDown();
    }
     @Test
    public void testPassNeg() throws AWTException, InterruptedException{
        setUp();
        boolean a=testBezSartu("123456","-1234");
        assertTrue(a);
        tearDown();
    } 
     @Test
    public void testPassLetra() throws AWTException, InterruptedException{
        setUp();
        boolean a=testBezSartu("123456","heyy");
        
        assertTrue(a);
        tearDown();
    } 
      @Test
    public void testKontuKop() throws AWTException, InterruptedException{
        setUp();
        int a=testBezSartuBiAldiz("123456","1234");
        int zuzena=1;
        
        assertEquals(zuzena,a);
        tearDown();
    } 
        @Test
   public void testGordailuEgokia() throws AWTException, InterruptedException{
        setUp();
        KontuBezSartu("123456","1234");
        boolean a=sartuGordailua("10");
        
        assertFalse(a);
        tearDown();
    } 
      @Test
    public void testGordailuNeg() throws AWTException, InterruptedException{
        setUp();
        KontuBezSartu("123456","1234");
        boolean a=sartuGordailua("-1000");
        
        assertTrue(a);
        tearDown();
    } 
      @Test
    public void testGordailuLetra() throws AWTException, InterruptedException{
        setUp();
        KontuBezSartu("123456","1234");
        boolean a=sartuGordailua("100d0");
        
        assertTrue(a);
        tearDown();
    } 
           @Test
   public void testDiruAteratzeEgokia() throws AWTException, InterruptedException{
        setUp();
        KontuBezSartu("123456","1234");
        boolean a=ateraDirua("5");
        
        assertFalse(a);
        tearDown();
    } 
            @Test
   public void testDiruAteratzeNeg() throws AWTException, InterruptedException{
        setUp();
        KontuBezSartu("123456","1234");
        boolean a=ateraDirua("-5");
        
        assertTrue(a);
        tearDown();
    }
             @Test
   public void testDiruAteratzeLetra() throws AWTException, InterruptedException{
        setUp();
        KontuBezSartu("123456","1234");
        boolean a=ateraDirua("-5s");
        
        assertTrue(a);
        tearDown();
    }
             @Test
   public void testDiruGutxiKontuan() throws AWTException, InterruptedException{
        setUp();
        KontuBezSartu("123456","1234");
        boolean a=ateraDirua("2000");
        
        assertTrue(a);
        tearDown();
    }
             @Test
   public void testDiruGutxiKutxan() throws AWTException, InterruptedException{
        setUp();
        KontuBezSartu("123456","1234");
        boolean a=ateraDirua("10000");
        
        tearDown();
    }
              @Test
   public void testSartuDiruaZuzen() throws AWTException, InterruptedException{
        setUp();
        KontuBezSartu("123456","1234");
        int hasierakoSaldoa= (int) saldoa()+10;
        boolean a=sartuGordailua("10");
        int amaieraSaldoa= (int) saldoa();
        assertEquals(hasierakoSaldoa, amaieraSaldoa);
        tearDown();
    }
   
   @Test
   public void testAteraDiruaZuzen() throws AWTException, InterruptedException{
        setUp();
        KontuBezSartu("123456","1234");
        int hasierakoSaldoa= (int) saldoa() -10;
        boolean a=ateraDirua("10");
        int amaieraSaldoa= (int) saldoa();
        assertEquals(hasierakoSaldoa, amaieraSaldoa);
        
        tearDown();
    }
   
    
}

