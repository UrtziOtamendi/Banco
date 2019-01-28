package BussinesLogic;

import DataBase.KontuDatuBasea;
import Domain.Bezeroa;
import Domain.Kontua;
import Domain.Sarrera;
import Domain.Emanaldia;
import java.util.*;
public class KontuNagusia
{

	private Bezeroa bezero;
        private List<Integer> kontuak;
        private Kontua kontua;
    
public KontuNagusia()
{
	KontuDatuBasea.instantzia();
        
}
        
public Bezeroa aurkituBezeroa(int ID, int pinZenbakiBat)
{
	Bezeroa bezeroa;
	bezeroa = (Bezeroa) KontuDatuBasea.instantzia().irakurriBezeroak(ID,pinZenbakiBat);
	if (bezeroa!=null)
	{
            kontuak = KontuDatuBasea.instantzia().irakurriKontuak(ID);
		return bezeroa;
	}
	else
		return null;
        
        
}
public Kontua lortuKontua(int kontuZenbakia){
    kontua = KontuDatuBasea.instantzia().lortuKontua(kontuZenbakia);
    if(kontua!=null){
        return kontua;
    }
    else{
        return null;
    }
}

public List<String> getHerriak()
{
	return KontuDatuBasea.instantzia().getHerriak();
}
public String toString()
{
	Iterator iterator;
	String string;
	string = "(KontuNagusia: " + "kontua = ";
	if (kontua!=null)
	{    
		string += "\n" + kontua.toStringTerm();
	}
	string += ")";
	return string;
}

    public Bezeroa getBezero() {
        return bezero;
    }

    public void setBezero(Bezeroa bezero) {
        this.bezero = bezero;
    }

    public List<Integer> getKontuak() {
        return kontuak;
    }

    public void setKontuak(List<Integer> kontuak) {
        this.kontuak = kontuak;
    }

    public List<String> getZineak(String zineCity) {
       return KontuDatuBasea.instantzia().getZineak(zineCity);
    }

    public List<Emanaldia> aurkituFilmak(String data,String Hiria,String Zinea) {
         return KontuDatuBasea.instantzia().aurkituFilmak(data,Hiria,Zinea);
    }
     public  List<String> aurkituOrduak(String data,String Hiria,String Zinea,String film) {
        return KontuDatuBasea.instantzia().aurkituOrduak(data,Hiria,Zinea,film);
    }
      public  List<Sarrera> aurkituSarrerak(String data,String Hiria,String Zinea,String film,String ordua) {
        return KontuDatuBasea.instantzia().aurkituSarrerak(data,Hiria,Zinea,film,ordua);
    }
}
