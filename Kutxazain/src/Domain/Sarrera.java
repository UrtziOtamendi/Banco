/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

/**
 *
 * @author urtzi
 */
public class Sarrera {
    
  private String Hiria;
  private String Zinea;  
  private String Data; 
  private String Filma;
  private int Zenbakia;
  private int Butaka;
  private String Ordua;

   
  private int Prezioa;  
  private Boolean Salduta;
  
  public Sarrera(int Zenb,int butaka, int Prezioa, String Hiria, String Zinea, String Filma,String Data, String ordua, Boolean saldua ){
      this.Zenbakia=Zenb;
      this.Hiria= Hiria;
      this.Zinea= Zinea;
      this.Data= Data;
      this.Prezioa= Prezioa;
      this.Filma=Filma;
      this.Ordua=ordua;
      this.Butaka=butaka;
      this.Salduta=saldua;
      
  }
  
    public String getHiria() {
        return Hiria;
    }

    public void setHiria(String Hiria) {
        this.Hiria = Hiria;
    }

    public String getZinea() {
        return Zinea;
    }

    public void setZinea(String Zinea) {
        this.Zinea = Zinea;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public String getFilma() {
        return Filma;
    }

    public void setFilma(String Filma) {
        this.Filma = Filma;
    }

    public int getPrezioa() {
        return Prezioa;
    }

    public void setPrezioa(int Prezioa) {
        this.Prezioa = Prezioa;
    }
    public String getOrdua() {
        return Ordua;
    }

    public void setOrdua(String Ordua) {
        this.Ordua = Ordua;
    }
     public int getZenbakia() {
        return Zenbakia;
    }

    public void setZenbakia(int Zenbakia) {
        this.Zenbakia = Zenbakia;
    }

    public int getButaka() {
        return Butaka;
    }

    public void setButaka(int Butaka) {
        this.Butaka = Butaka;
    }

    public Boolean getSalduta() {
        return Salduta;
    }

    public void setSalduta(Boolean Salduta) {
        this.Salduta = Salduta;
    }
}
