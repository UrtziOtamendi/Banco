package Domain;

import GUI.ObjetuIkuskatzailea;
import DataBase.KontuDatuBasea;
import java.util.Date;
public class Kontsulta extends Transakzioa
{
public Kontsulta()
{
	//  Deklarazioak
	int temp;
	//  Kalkulatu transakzio zenbakia	
  	temp = KontuDatuBasea.instantzia().irakurriHasierakoTransakzioZenbakia();
	temp++;
	this.setAzkenTransakzioarenZenbakia(temp);
	//  Idatzi
	this.setTransakzioZenbakia(temp);
	this.setData(new Date());
}
public double kantitatea()
{
	return 0.0;
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
