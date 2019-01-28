/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;


public class Emanaldia {

  
  private String Hiria;
  private String Zinea;  
  private String Data; 
  private String Filma;

    private String Argazkia;
    private String Ordua;
  private int Prezioa;  
  
  
  public Emanaldia(String Hiria, String Zinea, String Filma,String Data, String ordua ,String argazkia, int Prezioa){
      this.Hiria= Hiria;
      this.Zinea= Zinea;
      this.Data= Data;
      this.Prezioa= Prezioa;
      this.Filma=Filma;
      this.Ordua=ordua;
      this.Argazkia=argazkia;
      
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
    
    public String getArgazkia() {
        return Argazkia;
    }

    public void setArgazkia(String Argazkia) {
        this.Argazkia = Argazkia;
    }

    public String getOrdua() {
        return Ordua;
    }

    public void setOrdua(String Ordua) {
        this.Ordua = Ordua;
    }
}
