package Domain;

import DataBase.KontuDatuBasea;
import java.util.*;
import java.text.*;
public class Kontua
{
	private int kontuZenbakia;
	private double saldoZaharra;
	private ArrayList<Bezeroa> bezeroak;

  
        private ArrayList<Transakzioa> transakzioak;
public Kontua(int kontuZenbakiBat, double saldoZaharra)
{
	this.setKontuZenbakia(kontuZenbakiBat);
	this.setSaldoZaharra(saldoZaharra);
	this.setTransakzioak(new ArrayList());
	this.setBezeroak(new ArrayList());
}

public int getKontuZenbakia()
{
	return this.kontuZenbakia;
}

public double getSaldoZaharra()
{
	return this.saldoZaharra;
}
 public ArrayList<Bezeroa> getBezeroak() {
        return bezeroak;
    }

    public void setBezeroak(ArrayList<Bezeroa> bezeroak) {
        this.bezeroak = bezeroak;
    }
public Gordailua eginGordailua(double kantitateBat,int bezeroID)
{
	// Deklarazioak
	Transakzioa transakzioa;
	int transakzioZenbakia, kontuZenbakia;
	double kantitatea;
	Date data;
	// Hasieraketa
	transakzioa = new Gordailua(kantitateBat);
	// Kontua-ri gehitu
	transakzioak.add(transakzioa);
	// SQL taulan sartu Transakzioa
	transakzioZenbakia = transakzioa.getTransakzioZenbakia();
	data = transakzioa.getData();
	kontuZenbakia = this.getKontuZenbakia();
	kantitatea = transakzioa.kantitatea();
	System.out.println("gordailua :" + transakzioa);
        int saldo=(int) (saldoBerria());
	KontuDatuBasea.instantzia().sartuTransakzioa(transakzioZenbakia, bezeroID ,kontuZenbakia, data, kantitatea, "Gordailua", saldo);
	// Irteera
	return (Gordailua) transakzioa;
}
public Kontsulta eginKontsulta(int bezeroID)
{
	// Deklarazioak
	Transakzioa transakzioa;
	int transakzioZenbakia, kontuZenbakia;
	double kantitatea;
	Date data;
	// Hasieraketa
	transakzioa = new Kontsulta();
	// Kontua-ri gehitu
	transakzioak.add(transakzioa);
	// SQL taulan sartu Transakzioa
	transakzioZenbakia = transakzioa.getTransakzioZenbakia();
	data = transakzioa.getData();
	kontuZenbakia = this.getKontuZenbakia();
	kantitatea = 0.0;
	System.out.println("kontsulta :" + transakzioa);
        double saldo= saldoBerria();
	KontuDatuBasea.instantzia().sartuTransakzioa(transakzioZenbakia, bezeroID, kontuZenbakia, data, kantitatea,"Konsulta",saldo);
	// Irteera
	return (Kontsulta) transakzioa;
}
public DiruAteratze ateraDirua(double kantitateBat,int bezeroID)
{
	// Deklarazioak
	Transakzioa transakzioa;
	int transakzioZenbakia, kontuZenbakia;
	double kantitatea;
	Date data;
	// Hasieraketa
	transakzioa = new DiruAteratze(kantitateBat);
	// Kontua-ri gehitu
	transakzioak.add(transakzioa);
	// SQL taulan sartu Transakzioa
	transakzioZenbakia = transakzioa.getTransakzioZenbakia();
	data = transakzioa.getData();
	kontuZenbakia = this.getKontuZenbakia();
	kantitatea = transakzioa.kantitatea();
	System.out.println("diruateratzea:" + transakzioa);
        int saldo=(int) (saldoBerria());
	KontuDatuBasea.instantzia().sartuTransakzioa(transakzioZenbakia,bezeroID, kontuZenbakia, data, kantitatea, "Dirua Ateratzea", saldo);
	// Irteera
	return (DiruAteratze) transakzioa;
}
public ErosketaEgin eginErosketa(double kantitateBat,int bezeroID,int SarreraZenb)
{
	// Deklarazioak
	Transakzioa transakzioa;
	int transakzioZenbakia, kontuZenbakia, erosketaZenbakia;
	double kantitatea;
	Date data;
	String mota;
	// Hasieraketa
	transakzioa = new ErosketaEgin(kantitateBat);
	// Kontua-ri gehitu
	transakzioak.add(transakzioa);
	// SQL taulan sartu Transakzioa
        erosketaZenbakia=KontuDatuBasea.instantzia().irakurriHasierakoErosketaZenbakia()+1;
	transakzioZenbakia = transakzioa.getTransakzioZenbakia();
	data = transakzioa.getData();
	kontuZenbakia = this.getKontuZenbakia();
	kantitatea = transakzioa.kantitatea();
	System.out.println("Erosketa" + transakzioa);
        int saldo=(int) (saldoBerria());
	KontuDatuBasea.instantzia().sartuErosketa(transakzioZenbakia,bezeroID, kontuZenbakia, data, kantitatea, saldo,SarreraZenb,erosketaZenbakia);
	// Irteera
	return (ErosketaEgin) transakzioa;
}
public double saldoBerria()
{
	// Deklarazioak
	double saldoBerria;
	Iterator iterator;
	Transakzioa transakzioa;
	// Hasieraketa
	saldoBerria = this.getSaldoZaharra();
	iterator = this.transakzioak.iterator();
	// Prozesua
	while (iterator.hasNext())
	{
		transakzioa = (Transakzioa) iterator.next();
		saldoBerria += transakzioa.kantitatea();
	}
	// Irteera
	return saldoBerria;
}
public void setKontuZenbakia(int kontuZenbakiBerria)
{
	this.kontuZenbakia = kontuZenbakiBerria;
}

public void setSaldoZaharra(double saldoZaharBerria)
{
	this.saldoZaharra = saldoZaharBerria;
}
public void setTransakzioak(ArrayList transakzioBerria)
{
	this.transakzioak = transakzioBerria;
}

public String toStringTerm()
{
	// Delarations
	Iterator iterator;
	Transakzioa transakzioa;
	String string;
	NumberFormat numberFormat;
	// Hasieraketa
	numberFormat = NumberFormat.getCurrencyInstance();
	// Prozesua
	string = "(Kontua: " 
	+ "kontuZenbakia = " + this.getKontuZenbakia() + " |  "
	+ "saldoZaharra = " + numberFormat.format(this.getSaldoZaharra()) + " |  "
	+ "saldoBerria = " + numberFormat.format(this.saldoBerria()) + " |  "; 
	iterator = transakzioak.iterator();
	while (iterator.hasNext())
	{
		transakzioa = (Transakzioa) iterator.next();
		string += "\n" + transakzioa.toString();
	}
	string += ")";
	// Irteera
	return string;
}
public String toString(){
    String n= ""+ this.kontuZenbakia +"";
    return n;
}

    void addBezeroa(Bezeroa bezeroa) {
        this.bezeroak.add(bezeroa);
    }
}
