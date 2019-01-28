package BussinesLogic;

// Class izena: 	banku_kutxazain.Kutxazaina.java
// Function:	Bigarren mailaren kontrolatzailea edo Facade

import Domain.Bezeroa;
import Domain.Transakzioa;
import Domain.Kontua;
import Domain.Gordailua;
import Domain.Sarrera;
import Domain.ErosketaEgin;
import Domain.DiruAteratze;
import Domain.Kontsulta;
import Domain.Emanaldia;
import java.text.*;
import java.util.List;

public class Kutxazaina
{
	private KontuNagusia kontuNagusia;
	private Kontua kontua;
        private Bezeroa bezeroa;
	private double kutxazainSaldoa;
public Kutxazaina()
{
	this.setKontuNagusia(new KontuNagusia());
	this.setKutxazainSaldoa(2500.00);
}
public Bezeroa sartuTxartela(int ID, int pinZenbakiBat)
{
	bezeroa = kontuNagusia.aurkituBezeroa(ID, pinZenbakiBat);
	return bezeroa;
}
public List<Integer> lortuKontuak(){
   return kontuNagusia.getKontuak();
}
public Kontua lortuKontua(int zenbakia){
   return kontuNagusia.lortuKontua(zenbakia);
}
public  void aukeratuKontua(Kontua kont)
{
	kontua=kont;
	
}
public Gordailua sartuGordailua(double kantitateBat)
{
	Transakzioa transakzioa;
	transakzioa = this.getKontua().eginGordailua(kantitateBat,bezeroa.getID());
	//  System.out.println("gordailuaa:" + transakzioa);
	return (Gordailua) transakzioa;
}
public void sartuBukaera()
{
	this.setKontua(null);
}
public Kontsulta sartuKontsulta()
{
	Transakzioa transakzioa;
	transakzioa = this.getKontua().eginKontsulta(bezeroa.getID());
	//  System.out.println("kontsulta:" + transakzioa);
	return (Kontsulta) transakzioa;
}
public DiruAteratze sartuDirua(double kantitateBat)
{
	Transakzioa transakzioa;
	double kutxazainSaldoa;
	kutxazainSaldoa = this.getKutxazainSaldoa();
	kutxazainSaldoa -= kantitateBat;
	this.setKutxazainSaldoa(kutxazainSaldoa);
	transakzioa = this.getKontua().ateraDirua(kantitateBat,bezeroa.getID());
	//  System.out.println("DiruAteratze is:" + transakzioa);
	return (DiruAteratze) transakzioa;
}
public ErosketaEgin erosketaEgin(double kantitateBat,int SarreraZenb)
{
	Transakzioa transakzioa;
	transakzioa = this.getKontua().eginErosketa(kantitateBat,bezeroa.getID(),SarreraZenb);
	//  System.out.println("DiruAteratze is:" + transakzioa);
	return (ErosketaEgin) transakzioa;
}


public List<String> getHerriak()
{
	return kontuNagusia.getHerriak();
}
public Kontua getKontua()
{
	return this.kontua;
}
public KontuNagusia getKontuNagusia()
{
	return this.kontuNagusia;
}
public double getKutxazainSaldoa()
{
	return this.kutxazainSaldoa;
}
public void setKontua(Kontua kontuBerria)
{
	this.kontua = kontuBerria;
}
public void setKontuNagusia(KontuNagusia kontuNagusiBerria)
{
	this.kontuNagusia = kontuNagusiBerria;
}
public void setKutxazainSaldoa(double kutxazainSaldoBerria)
{
	this.kutxazainSaldoa = kutxazainSaldoBerria;
}
public String toString()
{
	//  Decarations
	NumberFormat numberFormat;
	// Formatuak hasieratu
	numberFormat = NumberFormat.getCurrencyInstance();
	// Irteera
	return "(Kutxazaina = "
	+ "kutxazainSaldoa = " + numberFormat.format(this.getKutxazainSaldoa()) + " | " 
	+ "kontuNagusia = " + this.getKontuNagusia() + ")";
}

  public  List<String> getZineak(String zineCity) {
        return kontuNagusia.getZineak(zineCity);
    }
   public  List<Emanaldia> aurkituFilmak(String data,String Hiria,String Zinea) {
        return kontuNagusia.aurkituFilmak(data,Hiria,Zinea);
    }
   public  List<String> aurkituOrduak(String data,String Hiria,String Zinea,String film) {
        return kontuNagusia.aurkituOrduak(data,Hiria,Zinea,film);
    }
   public  List<Sarrera> aurkituSarrerak(String data,String Hiria,String Zinea,String film,String ordua) {
        return kontuNagusia.aurkituSarrerak(data,Hiria,Zinea,film,ordua);
    }
}
