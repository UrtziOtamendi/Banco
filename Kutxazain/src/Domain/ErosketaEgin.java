/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import GUI.ObjetuIkuskatzailea;
import DataBase.KontuDatuBasea;
import java.util.Date;

/**
 *
 * @author urtzi
 */
public class ErosketaEgin extends Transakzioa {
    
    	private double kantitatea;
public ErosketaEgin(double kantitateBat)
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
