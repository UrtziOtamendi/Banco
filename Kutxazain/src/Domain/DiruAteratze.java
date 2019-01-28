package Domain;


import GUI.ObjetuIkuskatzailea;
import DataBase.KontuDatuBasea;
import java.util.Date;
public class DiruAteratze extends Transakzioa
{
	private double kantitatea;
public DiruAteratze(double kantitateBat)
{
	//  Deklarazioak
	int temp;
	//  Kalkulatu transakzio zenbakia	
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
	return -this.getKantitatea();
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
		return "errorea To String ";
	}
}
}
