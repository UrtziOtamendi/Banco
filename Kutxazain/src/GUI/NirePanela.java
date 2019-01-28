package GUI;

// Class izena: 	banku_kutxazain.NirePanela.java
// Function:	Arkitekturaren aurkezpen maila

import Domain.Bezeroa;
import Domain.Kontua;
import BussinesLogic.Kutxazaina;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.awt.Dimension;
import static java.awt.event.ActionEvent.ACTION_FIRST;
import java.util.ArrayList;
import java.util.List;

public class NirePanela extends JPanel implements ActionListener
{
	// Kontrolak
	private JPasswordField testuEremuaPinZenbakia;
	private JButton botoiaSartuID, botoiaGordailua, botoiaDiruAteratzea, botoiaKontsulta, botoiaBukatu, kontuaAukeratu, btnSarrera;
	private JTextField testuEremuaKontuZenbakia, testuEremuaSaldoa, testuEremuaGordailua, testuEremuaDiruAteratzea, testuEremuaSaldoBerria ;
	private JLabel etiketaIzena, etiketaKontuZenbakia, etiketaPinZenbakia, etiketaSaldoa, etiketaGordailua, etiketaDiruAteratzea, etiketaSaldoBerria,lblKontua;
        private JComboBox kontuLista;
	// Modeloak
	private Kutxazaina kutxazaina;
	private Kontua kontua;
        private Bezeroa bezeroa;
        private Boolean Errorea;

    public Boolean getErrorea() {
        return Errorea;
    }
	// Utilitatea
	private NumberFormat numberFormat;
public NirePanela()
{
        Errorea=false;
	// Modeloa hasieratu
	kutxazaina = new Kutxazaina();
	// Utilitatea hasieratu
	numberFormat = NumberFormat.getCurrencyInstance();
	// Leihoak doitu
	this.setFont(new Font("Arial", Font.PLAIN, 12));
	this.setBackground(Color.lightGray);
	this.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
	// Kontrolak hasieratu
	gehituKontrolak();
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

}
public void actionPerformed(ActionEvent event)
{
	if (event.getSource() == botoiaSartuID ||botoiaSartuID.isSelected() )
	{       
		// Deklarazioak
		int ID, pinZenbakia;
		// Input
                try{
                    ID = Integer.parseInt(testuEremuaKontuZenbakia.getText());
                    pinZenbakia = Integer.parseInt(new String((testuEremuaPinZenbakia.getPassword())));
                    // Modeloa sartu 
                    
                    if((6==(int)(Math.log10(ID)+1)) && (ID>0) && ((4==(int)(Math.log10(pinZenbakia)+1)) && (pinZenbakia >0))){
                        
                        bezeroa = kutxazaina.sartuTxartela(ID, pinZenbakia);
                        // Erroreak egiaztatu
                        if(bezeroa != null){
                           List<Integer> kontuak = new ArrayList<Integer>();
                           kontuak= kutxazaina.lortuKontuak();
                           kontuLista.removeAllItems();
                            for(int i=0; i<kontuak.size(); i++){
                                kontuLista.addItem(kontuak.get(i));
                            }
                            kontuaAukeratu.setEnabled(true);
                        }
                        else
                        {
                                
                                JOptionPane.showMessageDialog(this, "Erabiltzailea ez da aurkitu", "Errorea", JOptionPane.WARNING_MESSAGE);
                                
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "ID-a 6 digituko zenbaki positiboa izan behar da, eta pasahitza 4 digituko balio positiboa.", "Errorea", JOptionPane.WARNING_MESSAGE);
                         Errorea= true;
                    }
                }
                catch (java.lang.NumberFormatException exeption)
                {
                     JOptionPane.showMessageDialog(this, "Passahitza edo ID-a ez da zenbaki bat", "Errorea", JOptionPane.WARNING_MESSAGE);
                     Errorea= true;
                }
        }
        if (event.getSource() == kontuaAukeratu || kontuaAukeratu.isSelected()){ 
            int zenbakia;
            zenbakia= (Integer) kontuLista.getSelectedItem();
            kontua =  kutxazaina.lortuKontua(zenbakia);
            
            
		if (kontua != null)
		{
			// Botoiak eta testu eremuak gaitu
                        kutxazaina.aukeratuKontua(kontua);
			botoiaSartuID.setEnabled(false);
			botoiaKontsulta.setEnabled(true);
			botoiaGordailua.setEnabled(true);
			botoiaDiruAteratzea.setEnabled(true);
			botoiaBukatu.setEnabled(false);
			testuEremuaKontuZenbakia.setEditable(false);
			testuEremuaPinZenbakia.setEditable(false);
			testuEremuaGordailua.setEditable(true);
			testuEremuaDiruAteratzea.setEditable(true);
                        btnSarrera.setEnabled(true);
		}
		else
		{
                     Errorea=true;
			JOptionPane.showMessageDialog(this, "Message", "Title", JOptionPane.WARNING_MESSAGE);
		}
	}
	if (event.getSource() == botoiaKontsulta)
	{
		// Modeloa sartu 
		kutxazaina.sartuKontsulta();
		// Irteera
		testuEremuaSaldoa.setText(numberFormat.format(kontua.saldoBerria()));
		// Botoiak eta testu eremuak gaitu
		botoiaSartuID.setEnabled(false);
		botoiaKontsulta.setEnabled(true);
		botoiaGordailua.setEnabled(true);
		botoiaDiruAteratzea.setEnabled(true);
		botoiaBukatu.setEnabled(true);
		testuEremuaKontuZenbakia.setEditable(false);
		testuEremuaPinZenbakia.setEditable(false);
		testuEremuaGordailua.setEditable(true);
		testuEremuaDiruAteratzea.setEditable(true);
	}
        
	if (event.getSource() == botoiaGordailua)
	{
		try{
                // Deklarazioak
                    double gordailuKantitatea, saldoBerria;
                    // Input
                String sartutakoKantitatea = testuEremuaGordailua.getText();
                if (sartutakoKantitatea.indexOf("Pts") != -1)
                  sartutakoKantitatea = sartutakoKantitatea.substring(0, sartutakoKantitatea.length()-3);
                    gordailuKantitatea = Double.valueOf(sartutakoKantitatea).doubleValue();
                    if(0<=gordailuKantitatea ){
                    // Modeloa sartu 
                    kutxazaina.sartuGordailua(gordailuKantitatea);
                    // Irteera
                    testuEremuaGordailua.setText(numberFormat.format(gordailuKantitatea));
                    testuEremuaSaldoBerria .setText(numberFormat.format(kontua.saldoBerria()));
                    // Botoiak eta testu eremuak gaitu
                    botoiaSartuID.setEnabled(false);
                    botoiaKontsulta.setEnabled(true);
                    botoiaGordailua.setEnabled(true);
                    botoiaDiruAteratzea.setEnabled(true);
                    botoiaBukatu.setEnabled(true);
                    testuEremuaKontuZenbakia.setEditable(false);
                    testuEremuaPinZenbakia.setEditable(false);
                    testuEremuaGordailua.setEditable(true);
                    testuEremuaDiruAteratzea.setEditable(true);
                    }
                    else{ 
                        JOptionPane.showMessageDialog(this, "Balioa ez da zenbaki positibo bat", "Errorea", JOptionPane.WARNING_MESSAGE);
                        Errorea=true;
                    }
             }
                catch (java.lang.NumberFormatException exeption)
                {
                     JOptionPane.showMessageDialog(this, "Balioa ez da zenbaki bat", "Errorea", JOptionPane.WARNING_MESSAGE);
                     Errorea=true;
                }         
                
                
	}
	if (event.getSource() == botoiaDiruAteratzea)
	{
		try{
                // Deklarazioak
		double diruAteratzeKantitatea, saldoBerria;
		// Input
    String sartutakoKantitatea = testuEremuaDiruAteratzea.getText();
    if (sartutakoKantitatea.indexOf("Pts") != -1)
      sartutakoKantitatea = sartutakoKantitatea.substring(0, sartutakoKantitatea.length()-3);
		diruAteratzeKantitatea = Double.valueOf(sartutakoKantitatea).doubleValue();
		// Modeloa sartu 
                if(0<=diruAteratzeKantitatea){
		
                
                if(kutxazaina.getKutxazainSaldoa()<diruAteratzeKantitatea){
                     JOptionPane.showMessageDialog(this, "Ez dago nahikoa diru kutxazainan", "Errorea", JOptionPane.WARNING_MESSAGE);
                    Errorea=true;
                }
                else if(kontua.saldoBerria()<diruAteratzeKantitatea){
                    JOptionPane.showMessageDialog(this, "Ez dago nahikoa diru kontuan", "Errorea", JOptionPane.WARNING_MESSAGE);
                    Errorea=true;
                }
                else{
                    kutxazaina.sartuDirua(diruAteratzeKantitatea);
                    // Irteera
                    saldoBerria = kontua.saldoBerria();
                    testuEremuaDiruAteratzea.setText(numberFormat.format(diruAteratzeKantitatea));
                    testuEremuaSaldoBerria .setText(numberFormat.format(saldoBerria));
                    // Botoiak eta testu eremuak gaitu
                    botoiaSartuID.setEnabled(false);
                    botoiaKontsulta.setEnabled(true);
                    botoiaGordailua.setEnabled(true);
                    botoiaDiruAteratzea.setEnabled(true);
                    botoiaBukatu.setEnabled(true);
                    testuEremuaKontuZenbakia.setEditable(false);
                    testuEremuaPinZenbakia.setEditable(false);
                    testuEremuaGordailua.setEditable(true);
                    testuEremuaDiruAteratzea.setEditable(true);
                    }
                }
                    else{ 
                        JOptionPane.showMessageDialog(this, "Balioa ez da zenbaki positibo bat", "Errorea", JOptionPane.WARNING_MESSAGE);
                    Errorea=true;
                }
             }
                catch (java.lang.NumberFormatException exeption)
                {
                     JOptionPane.showMessageDialog(this, "Balioa ez da zenbaki bat", "Errorea", JOptionPane.WARNING_MESSAGE);
                Errorea=true;
                }         
                
	}
	if (event.getSource() == botoiaBukatu)
	{
		// Modeloa sartu 
		kutxazaina.sartuBukaera();
		// Eremuak ezabatu
		testuEremuaKontuZenbakia.setText("");
		testuEremuaPinZenbakia.setText("");
		testuEremuaSaldoa.setText("");
		testuEremuaGordailua.setText("");
		testuEremuaDiruAteratzea.setText("");
		testuEremuaSaldoBerria .setText("");
		// Botoiak eta testu eremuak gaitu
		botoiaSartuID.setEnabled(true);
		botoiaGordailua.setEnabled(false);
		botoiaDiruAteratzea.setEnabled(false);
		botoiaBukatu.setEnabled(false);
                btnSarrera.setEnabled(false);
		botoiaKontsulta.setEnabled(false);
		testuEremuaKontuZenbakia.setEditable(true);
		testuEremuaPinZenbakia.setEditable(true);
		testuEremuaGordailua.setEditable(false);
		testuEremuaDiruAteratzea.setEditable(false);         
                kontuLista.removeAllItems();
                kontuaAukeratu.setEnabled(false);
	}
        if(event.getSource() == btnSarrera){
            JFrame a= new emanaldiaAurkitu(bezeroa,kontua,kutxazaina);
            a.setVisible(true);
        }
}
private void gehituKontrolak()
{
	setLayout(null);
	// private metodo lagungarria clutter-a paneleko eraiketzailetik kentzeko
	// Grid (row=1, col=1)
	etiketaKontuZenbakia = new JLabel("Bezero ID");
	etiketaKontuZenbakia.setBounds(26, 25, 116, 28);
	this.add(etiketaKontuZenbakia);
	// Grid (row=1, col=2)
	testuEremuaKontuZenbakia = new JTextField();
	testuEremuaKontuZenbakia.setBounds(167, 25, 116, 28);
	this.add(testuEremuaKontuZenbakia);
	// Grid (row=1, col=3)
	JLabel label = new JLabel("                 ");
	label.setBounds(308, 25, 116, 28);
	this.add(label);
	// Grid (row=2, col=1)
	etiketaPinZenbakia = new JLabel("PIN Zenbakia");
	etiketaPinZenbakia.setBounds(26, 78, 116, 28);
	this.add(etiketaPinZenbakia);
	// Grid (row=2, col=2)
	testuEremuaPinZenbakia = new JPasswordField();
	testuEremuaPinZenbakia.setBounds(167, 78, 116, 28);
	this.add(testuEremuaPinZenbakia);
	// Grid (row=2, col=3)
	botoiaSartuID = new JButton("Sartu Txartela");
	botoiaSartuID.setBounds(308, 78, 116, 28);
	this.add(botoiaSartuID);
	botoiaSartuID.addActionListener(this);
	// Grid (row=3, col=1)
	etiketaSaldoa = new JLabel("Saldoa");
	etiketaSaldoa.setBounds(26, 215, 116, 28);
	this.add(etiketaSaldoa);
	// Grid (row=3, col=2)
	testuEremuaSaldoa = new JTextField();
	testuEremuaSaldoa.setBounds(167, 215, 116, 28);
	this.add(testuEremuaSaldoa);
	testuEremuaSaldoa.setEditable(false);
	// Grid (row=3, col=3) 
	botoiaKontsulta = new JButton("Kontsulta");
	botoiaKontsulta.setBounds(308, 215, 116, 28);
	this.add(botoiaKontsulta);
	botoiaKontsulta.addActionListener(this);
	// Grid (row=4, col=1)
	etiketaGordailua = new JLabel("Gordailua");
	etiketaGordailua.setBounds(26, 264, 116, 28);
	this.add(etiketaGordailua);
	// Grid (row=4, col=2)
	testuEremuaGordailua = new JTextField();
	testuEremuaGordailua.setLocation(167, 264);
	this.add(testuEremuaGordailua);
	// Grid (row=4, col=3)
	botoiaGordailua = new JButton("Gordailua");
	botoiaGordailua.setBounds(308, 264, 116, 28);
	this.add(botoiaGordailua);
	botoiaGordailua.addActionListener(this);
	// Grid (row=5, col=1)
	etiketaDiruAteratzea = new JLabel("DiruAteratzea");
	etiketaDiruAteratzea.setBounds(26, 313, 116, 28);
	this.add(etiketaDiruAteratzea);
	// Grid (row=5, col=2)
	testuEremuaDiruAteratzea = new JTextField();
	testuEremuaDiruAteratzea.setBounds(167, 313, 116, 28);
	this.add(testuEremuaDiruAteratzea);
	// Grid (row=5, col=3)
	botoiaDiruAteratzea = new JButton("DiruAteratzea");
	botoiaDiruAteratzea.setBounds(308, 313, 116, 28);
	this.add(botoiaDiruAteratzea);
	botoiaDiruAteratzea.addActionListener(this);
	// Grid (row=6, col=1)
	etiketaSaldoBerria = new JLabel("Saldo berria");
	etiketaSaldoBerria.setBounds(26, 370, 116, 28);
	this.add(etiketaSaldoBerria);
	// Grid (row=6, col=2)
	testuEremuaSaldoBerria  = new JTextField();
	testuEremuaSaldoBerria.setBounds(167, 370, 116, 28);
	this.add(testuEremuaSaldoBerria );
	testuEremuaSaldoBerria .setEditable(false);
	// Grid (row=6, col=3)
	botoiaBukatu = new JButton("Bukatu");
	botoiaBukatu.setBounds(308, 370, 116, 28);
	this.add(botoiaBukatu);
	botoiaBukatu.addActionListener(this);
	// Botoiak eta testu eremuak ezgaitu
	botoiaGordailua.setEnabled(false);
	botoiaDiruAteratzea.setEnabled(false);
	botoiaBukatu.setEnabled(false);
	botoiaKontsulta.setEnabled(false);
	testuEremuaGordailua.setEditable(false);
	testuEremuaDiruAteratzea.setEditable(false);
	
        
	lblKontua = new JLabel("Kontua");
	lblKontua.setBounds(26, 147, 46, 14);
	this.add(lblKontua);
	
	kontuLista = new JComboBox();
	kontuLista.setBounds(167, 144, 116, 20);
	this.add(kontuLista);
        
        btnSarrera = new JButton("Zineko Sarrera Erosi");
        btnSarrera.setBounds(26, 444, 398, 20);
        this.add(btnSarrera);
	btnSarrera.addActionListener(this);
        btnSarrera.setEnabled(false);
        
	kontuaAukeratu = new JButton("Aukeratu");
	kontuaAukeratu.setBounds(308, 143, 116, 23);
	this.add(kontuaAukeratu);
        kontuaAukeratu.addActionListener(this);
        kontuaAukeratu.setEnabled(false);
        
        JTextArea textArea = new JTextArea();
        textArea.setBounds(32, 496, 392, 22);
        add(textArea);
        
        
}
public Kutxazaina getKutxazain()
{
	return kutxazaina;
}
public void setKutxazain(Kutxazaina kutxazainBerria)
{
	kutxazaina = kutxazainBerria;
}

  private void jbInit() throws Exception
  {
    testuEremuaGordailua.setSize(new Dimension(116, 28));
  }
  
  //TESTERAKO GETTER ETA SETTERRAK

   public void sartuBezeroa() {
       ActionEvent event = new ActionEvent(botoiaSartuID, ACTION_FIRST, "a");
       
       actionPerformed(event);
       
       
    }
   

    public void setKontuLista(JComboBox kontuLista) {
        this.kontuLista = kontuLista;
    }
     public void setBezeroa(String user){
        testuEremuaKontuZenbakia.setText(user);
    }
    public void setPasahitza(String pass){
        testuEremuaPinZenbakia.setText(pass);
    }

    public void aukeratuKontua() {
      kontuLista.setSelectedIndex(0);
      ActionEvent event = new ActionEvent(kontuaAukeratu, ACTION_FIRST, "a");
       
      actionPerformed(event);
      
    }
    public void sartuGordailua(String gord){
        testuEremuaGordailua.setText(gord);
         ActionEvent event = new ActionEvent(botoiaGordailua, ACTION_FIRST, "a");
       
        actionPerformed(event);
    }
    public void ateraDirua(String dirua){
        testuEremuaDiruAteratzea.setText(dirua);
         ActionEvent event = new ActionEvent(botoiaDiruAteratzea, ACTION_FIRST, "a");
       
        actionPerformed(event);
    }
    public int kontuKop(){
       return kontuLista.getItemCount();
    }
     public double lortuSaldoa(){
       return kontua.saldoBerria();
    }
     
     
    
}
