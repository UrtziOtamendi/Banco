package Domain;

// Class izena: 	banku_kutxazain.Transakzioa.java
// Function:	Transakzioaren domeinu abstraktua 

import GUI.ObjetuIkuskatzailea;
import DataBase.KontuDatuBasea;
import java.util.Date;

public abstract class Transakzioa
{
	private Date data;
	private int transakzioZenbakia;
	private static int azkenTransakzioarenZenbakia = 30000;
	private int kontuZenbakia;
	
static public void Transkazioa()
{
	azkenTransakzioarenZenbakia = KontuDatuBasea.instantzia().irakurriHasierakoTransakzioZenbakia();
}
  
public double kantitatea()
{
	return 0.0;
}
public int getKontuZenbakia()
{
	return kontuZenbakia;
}
public Date getData()
{
	return data;
}
public static int getAzkenTransakzioarenZenbakia()
{
	return azkenTransakzioarenZenbakia;
}
public int getTransakzioZenbakia()
{
	return transakzioZenbakia;
}
public void setKontuZenbakia(int KontuZenbakiBerria)
{
	kontuZenbakia = KontuZenbakiBerria;
}
public void setData(Date dataBerria)
{
	this.data = dataBerria;
}
public static void setAzkenTransakzioarenZenbakia(int azkenTransakzioarenZenbakiBerria)
{
	azkenTransakzioarenZenbakia = azkenTransakzioarenZenbakiBerria;
}
public void setTransakzioZenbakia(int transakzioZenbakiBerria)
{
	this.transakzioZenbakia = transakzioZenbakiBerria;
}
public String toString()
{
	try
	{
		return ObjetuIkuskatzailea.toString(this);
	}
	catch (Exception exception)
	{
		return "errorea To String -n";
	}
}
}
