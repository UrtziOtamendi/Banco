package Domain;


import GUI.ObjetuIkuskatzailea;
import DataBase.KontuDatuBasea;
import java.util.Date;
public class Gordailua extends Transakzioa
{
	private double kantitatea;
public Gordailua(double kantitateBat)
{
	//  Deklarazioak
	int temp;
	//  Kalkulatu transakzio zenbakia
	//temp = this.getAzkenTransakzioarenZenbakia();
	temp = KontuDatuBasea.instantzia().irakurriHasierakoTransakzioZenbakia();
	temp++;
	this.setAzkenTransakzioarenZenbakia(temp);
	//  Idatzi
	this.setTransakzioZenbakia(temp);
	this.setKantitatea(kantitateBat);
	this.setData(new Date());
}
public double kantitatea()
{
	return this.getKantitatea();
}
public double getKantitatea()
{
	return kantitatea;
}
public void setKantitatea(double kantitateBerria)
{
	this.kantitatea = kantitateBerria;
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
