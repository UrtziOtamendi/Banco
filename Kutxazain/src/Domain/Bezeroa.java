package Domain;

import GUI.ObjetuIkuskatzailea;
import java.util.ArrayList;


public class Bezeroa
{

   
	
	private String izena;
        private int ID;
        private int pass;
        private int tel;
        

  


    public Bezeroa(int ID, int pass, String izena, int tel) {
        this.setID(ID);
        this.setPass(pass);
        this.setIzena(izena);
        this.setTel(tel);
        
    }  
    
   
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    
public String getIzena()
{
	return izena;
}

public void setIzena(String izenBerria)
{
	this.izena = izenBerria;
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
