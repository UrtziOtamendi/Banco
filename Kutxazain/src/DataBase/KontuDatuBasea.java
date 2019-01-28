package DataBase;

import Domain.Bezeroa;
import Domain.Kontua;
import Domain.Sarrera;
import Domain.Emanaldia;
import java.sql.*;
import java.util.*;
public class KontuDatuBasea
{
	private static KontuDatuBasea instantzia;
	private String urla;
	private Connection konekzioa;
	private Statement sententzia;
public KontuDatuBasea()
{       
	try
	{
		urla = "jdbc:sqlite:bankua.sqlite";
		Class.forName("org.sqlite.JDBC");
		konekzioa = DriverManager.getConnection(urla, "", "");
		sententzia = konekzioa.createStatement();
                
                String Kontua = "CREATE TABLE IF NOT EXISTS Kontua  " +
                        "(Kontu_Zenbakia INT PRIMARY KEY  NOT NULL," +
                        " Saldo_Zaharra DOUBLE NOT NULL)";
                
                 String Bez = "CREATE TABLE IF NOT EXISTS Bezeroa" +
                        "(ID INT PRIMARY KEY NOT NULL," +
                        " Pass INT NOT NULL," +  
                        " Izena VARCHAR(255) NOT NULL," +
                        " Bezeroaren_Telefono_Zenbakia INT NOT NULL)";
                 
                 
                String Transakzioa = "CREATE TABLE IF NOT EXISTS Transakzioa   " +
                        "(Trantsakzio_Zenbaki INT NOT NULL," +
                        " Bezeroaren_ID INT  NOT NULL," +
                        " Kontu_Zenbakia INT NOT NULL," + 
                        " Data VARCHAR(255) NOT NULL," +
                        " Kantitatea INT NOT NULL," +
                        " Mota VARCHAR(255) NOT NULL," +
                        " FOREIGN KEY(Kontu_Zenbakia) REFERENCES KontuBezero(Kontu_Zenbakia)"+
                        " FOREIGN KEY(Bezeroaren_ID) REFERENCES KontuBezero(ID),"+
                        " PRIMARY KEY (Trantsakzio_Zenbaki,Kontu_Zenbakia,Bezeroaren_ID))";
                        
                 String Zineak = "CREATE TABLE IF NOT EXISTS Zinea  " +
                        "(Hiria VARCHAR(255) NOT NULL," +
                        " Zinema_Izena VARCHAR(255) NOT NULL," +
                        " Telefonoa INT NOT NULL," +           
                        " PRIMARY KEY (Hiria,Zinema_Izena))";
                 
                 String Filma= "CREATE TABLE IF NOT EXISTS Filma " +
                        "(Film_Izena VARCHAR(255) NOT NULL," +
                        " Zuzendaria VARCHAR(255) NOT NULL," +
                        " estreinaldiData VARCHAR(255) NOT NULL," +
                        " PRIMARY KEY (Film_Izena))";
                 
                 String Emanaldia= "CREATE TABLE IF NOT EXISTS Emanaldia " +
                        "(Hiria VARCHAR(255) NOT NULL," +
                        " Zinema_Izena VARCHAR(255) NOT NULL," +
                        " Film_Izena VARCHAR(255) NOT NULL," +
                        " Data VARCHAR(255) NOT NULL," +
                        " Ordua VARCHAR(255) NOT NULL," +
                        " Argazkia VARCHAR(255) NOT NULL," +
                        " Prezioa INT NOT NULL," +
                        " FOREIGN KEY(Hiria) REFERENCES Zinea(Hiria)"+
                        " FOREIGN KEY(Film_Izena) REFERENCES Filma(Film_Izena),"+
                        " FOREIGN KEY(Zinema_Izena) REFERENCES Zinea(Zinema_Izena)"+
                        " PRIMARY KEY (Hiria,Zinema_Izena,Film_Izena,Data,ordua))";
                 
                 String Sarrera="CREATE TABLE IF NOT EXISTS Sarrera " +
                        "(Zenbakia INT NOT NULL,"+
                        " Butaka INT NOT NULL," +
                        " Prezioa INT NOT NULL," +
                        " Hiria VARCHAR(255) NOT NULL," +
                        " Zinema_Izena VARCHAR(255) NOT NULL," +
                        " Film_Izena VARCHAR(255) NOT NULL," +
                        " Data VARCHAR(255) NOT NULL," +  
                        " Ordua VARCHAR(255) NOT NULL," +
                        " Saldua INT NOT NULL,"+
                        " FOREIGN KEY(Prezioa) REFERENCES Emanaldia(Prezioa)"+                         
                        " FOREIGN KEY(Data) REFERENCES Emanaldia(Data)"+
                        " FOREIGN KEY(Ordua) REFERENCES Emanaldia(Ordua)"+
                        " FOREIGN KEY(Hiria) REFERENCES Emanaldia(Hiria)"+
                        " FOREIGN KEY(Film_Izena) REFERENCES Emanaldia(Film_Izena)"+
                        " FOREIGN KEY(Zinema_Izena) REFERENCES Emanaldia(Zinema_Izena)"+
                        " PRIMARY KEY (Zenbakia))";
                 
                  String Erosketa="CREATE TABLE IF NOT EXISTS Erosketa " +
                        "(Zenbakia INT NOT NULL,"+
                        " Trantsakzio_Zenbakia INT NOT NULL," +
                        " Sarrera_Zenbakia INT NOT NULL," +                                            
                        " FOREIGN KEY(Trantsakzio_Zenbakia) REFERENCES Trantsakzioa(Trantsakzio_Zenbaki)"+
                        " FOREIGN KEY(Sarrera_Zenbakia) REFERENCES Sarrera(Zenbakia)"+
                        " PRIMARY KEY (Zenbakia))";
                 
                
                 
                String KontuBez = "CREATE TABLE IF NOT EXISTS KontuBezero   " +
                        "(Bezeroaren_ID INT  NOT NULL," +
                        " Kontu_Zenbakia INT NOT NULL," +  
                        " FOREIGN KEY(Bezeroaren_ID) REFERENCES Bezeroa(ID),"+
                        " FOREIGN KEY(Kontu_Zenbakia) REFERENCES Kontua(Kontu_Zenbakia),"+
                        " PRIMARY KEY(Kontu_Zenbakia, Bezeroaren_ID))";
                
              
                     
               String K1 = "INSERT INTO Bezeroa" +
                        " VALUES (123456, 1234, 'Ohian Arroyo', 666666666)";
                
                String K2 = "INSERT INTO Kontua" +
                        " VALUES (111, 1000)";
                
                String K3 = "INSERT INTO  Bezeroa" +
                        " VALUES (123450, 1234, 'Urtzi Otamendi', 666666668)";
                
                String K4= "INSERT INTO KontuBezero" +
                          " VALUES (123456, 111)";
                
                 String K5= "INSERT INTO KontuBezero" +
                          " VALUES (123450, 111)";
                 
                 String K6= "INSERT INTO KontuBezero" +
                          " VALUES (123450, 112)";
                 
                 String K7 = "INSERT INTO Kontua" +
                        " VALUES (112, 1000)";
                                 
              String K8 = "INSERT INTO Zinea" +
                        " VALUES ('Donostia', 'Principe',123456789)";
                   String K9 = "INSERT INTO Filma" +
                        " VALUES ('Argo', 'Argien','18-12-1996')";
               String K10 = "INSERT INTO Emanaldia" +
                        " VALUES ('Donostia', 'Principe','Argo','18-12-2016','19:00','http://pics.filmaffinity.com/argo-756723442-large.jpg',8)";
                   String K11 = "INSERT INTO Sarrera" +
                        " VALUES (2,13,8,'Donostia', 'Principe','Argo','18-12-2016','19:00',0)";
                String K12 = "INSERT INTO Sarrera" +
                        " VALUES (3,14,8,'Donostia', 'Principe','Argo','18-12-2016','19:00',0)";
                String K13 = "INSERT INTO Sarrera" +
                        " VALUES (13,14,8,'Donostia', 'Principe','Argo','18-12-2016','14:00',0)";
                String K14 = "INSERT INTO Sarrera" +
                        " VALUES (14,14,8,'Donostia', 'Principe','Argo','18-12-2016','14:00',0)";
                String K15 = "INSERT INTO Sarrera" +
                        " VALUES (15,14,8,'Donostia', 'Principe','Argo','18-12-2016','14:00',0)";
                String K16 = "INSERT INTO Sarrera" +
                        " VALUES (16,14,8,'Donostia', 'Principe','Argo','18-12-2016','14:00',0)";
                String K17 = "INSERT INTO Sarrera" +
                        " VALUES (17,14,8,'Donostia', 'Principe','Argo','18-12-2016','14:00',0)";
                String K18 = "INSERT INTO Sarrera" +
                        " VALUES (18,14,8,'Donostia', 'Principe','Argo','18-12-2016','14:00',0)";
                String K19 = "INSERT INTO Sarrera" +
                        " VALUES (19,14,8,'Donostia', 'Principe','Argo','18-12-2016','14:00',0)";
                String K20 = "INSERT INTO Sarrera" +
                        " VALUES (20,14,8,'Donostia', 'Principe','Argo','18-12-2016','14:00',0)";
                String K21 = "INSERT INTO Sarrera" +
                        " VALUES (21,14,8,'Donostia', 'Principe','Argo','18-12-2016','14:00',0)";
                String K22 = "INSERT INTO Emanaldia" +
                        " VALUES ('Donostia', 'Principe','Argo','18-12-2016','14:00','http://pics.filmaffinity.com/argo-756723442-large.jpg',8)";
                
                
                 
                sententzia.executeUpdate(Kontua);
                sententzia.executeUpdate(Transakzioa);
                sententzia.executeUpdate(KontuBez);
                sententzia.executeUpdate(Bez);
                sententzia.executeUpdate(Filma);
                sententzia.executeUpdate(Zineak);
                sententzia.executeUpdate(Emanaldia);
                sententzia.executeUpdate(Sarrera);
                sententzia.executeUpdate(Erosketa);
                
          /*  sententzia.executeUpdate(K1);
              sententzia.executeUpdate(K2);
              sententzia.executeUpdate(K3);
              sententzia.executeUpdate(K4);
              sententzia.executeUpdate(K5);
              sententzia.executeUpdate(K6);
              sententzia.executeUpdate(K7); 
              sententzia.executeUpdate(K8);  
              sententzia.executeUpdate(K9);               
               sententzia.executeUpdate(K12);
               sententzia.executeUpdate(K11);         
             sententzia.executeUpdate(K10);         
              sententzia.executeUpdate(K13); 
              sententzia.executeUpdate(K14); 
              sententzia.executeUpdate(K15); 
              sententzia.executeUpdate(K16); 
              sententzia.executeUpdate(K17); 
              sententzia.executeUpdate(K18); 
              sententzia.executeUpdate(K19); 
              sententzia.executeUpdate(K20); 
              sententzia.executeUpdate(K21); 
              sententzia.executeUpdate(K22);    */    
          
              
            
                
	}
	catch (SQLException anException)
	{
		while (anException != null)
		{
			System.out.println("SQL Exception:  " + anException.getMessage());
			anException = anException.getNextException();
		}
	}
	catch (java.lang.Exception anException)
	{
		anException.printStackTrace();
	}
}
public void finalize()
{
	try
	{
		sententzia.close();
		konekzioa.close();
	}
	catch (SQLException anException)
	{
		while (anException != null)
		{
			System.out.println("SQL Exception:  " + anException.getMessage());
			anException = anException.getNextException();
		}
	}
	catch (Exception anException)
	{
		anException.printStackTrace();
	}
}
public Connection getKonekzioa()
{
	return konekzioa;
}
public Statement getSententzia()
{
	return sententzia;
}
public String getUrl()
{
	return urla;
}

public void sartuErosketa(int transakzioZenbakiBat, int bezeroaren_ID, int kontu_zenbakia, java.util.Date dataBat, double kantitateBat,  double saldoa, int sarrera_Zenbakia, int erosketa_zenbakia)
{
	// Deklarazioak
	String sql, saldo, erosketa,sarrera;
        String motaBat="Erosketa";
	int count;
	java.text.DateFormat dateFormat = java.text.DateFormat.getDateTimeInstance();
	// Hasieraketa
        System.out.println(saldoa);
        saldo = "UPDATE Kontua SET Saldo_Zaharra = " + saldoa + " WHERE Kontu_Zenbakia = "+ kontu_zenbakia ;
	sql = "INSERT into Transakzioa VALUES (" + transakzioZenbakiBat + ", " + bezeroaren_ID + ", " + kontu_zenbakia +", '"  + dateFormat.format(dataBat) + "'," + kantitateBat + ", '" + motaBat + "')";
	erosketa = "INSERT into Erosketa VALUES (" + erosketa_zenbakia + ", "+transakzioZenbakiBat  + ", " + sarrera_Zenbakia +" )";
        sarrera= "UPDATE Sarrera SET Saldua= "+ 1 + " WHERE Zenbakia = "+ sarrera_Zenbakia;
        System.out.println("SQL : " + sql);
	try
	{
		// Insert sql exekutatu sartutako erregistroak itzuliz
                sententzia.executeUpdate(saldo);
                sententzia.executeUpdate(erosketa);
                sententzia.executeUpdate(sarrera);
		count = sententzia.executeUpdate(sql);
		System.out.println("count : " + count);
	}
	catch (SQLException anException)
	{
		while (anException != null)
		{
			System.out.println(" SQL Exception Transakzioa sartzen: " + anException.getMessage());
			anException = anException.getNextException();
		}
	}
	catch (java.lang.Exception anException)
	{
		anException.printStackTrace();
	}
}
public void sartuTransakzioa(int transakzioZenbakiBat, int bezeroaren_ID, int kontu_zenbakia, java.util.Date dataBat, double kantitateBat, String motaBat, double saldoa)
{
	// Deklarazioak
	String sql, saldo;
	int count;
	java.text.DateFormat dateFormat = java.text.DateFormat.getDateTimeInstance();
	// Hasieraketa
        System.out.println(saldoa);
        saldo = "UPDATE Kontua SET Saldo_Zaharra = " + saldoa + " WHERE Kontu_Zenbakia = "+ kontu_zenbakia ;
	sql = "INSERT into Transakzioa VALUES (" + transakzioZenbakiBat + ", " + bezeroaren_ID + ", " + kontu_zenbakia +", '"  + dateFormat.format(dataBat) + "'," + kantitateBat + ", '" + motaBat + "')";

        System.out.println("SQL : " + sql);
	try
	{
		// Insert sql exekutatu sartutako erregistroak itzuliz
                sententzia.executeUpdate(saldo);
		count = sententzia.executeUpdate(sql);
		System.out.println("count : " + count);
	}
	catch (SQLException anException)
	{
		while (anException != null)
		{
			System.out.println(" SQL Exception Transakzioa sartzen: " + anException.getMessage());
			anException = anException.getNextException();
		}
	}
	catch (java.lang.Exception anException)
	{
		anException.printStackTrace();
	}
}
public static KontuDatuBasea instantzia()
{
	if (instantzia == null)
		instantzia = new KontuDatuBasea();
	return instantzia;
}
public Bezeroa irakurriBezeroak(int Ident, int passa)
{
  	// Deklarazioak
	String query = "select * from Bezeroa where ID="+Ident+ " and Pass="+passa;
	Bezeroa bezeroa=null;
	ResultSet resultSet;
	// Initilization
	
	try
	{
		// SQL exekutatu
		resultSet = sententzia.executeQuery(query);
		//	Resultset-eko errenkada eta zutabe guztiak kapturatu
		while (resultSet.next())
		{
			int ID = Integer.parseInt(resultSet.getString(1));
			int pass = Integer.parseInt(resultSet.getString(2));
                        String izena = resultSet.getString(3);
                        int tel= Integer.parseInt(resultSet.getString(4));
			System.out.println("Bezeroa: ID = " + ID+ " | "  + "Izena = " + izena + " | " + "Telefonoa = " + tel + " | ");
			bezeroa = new Bezeroa(ID, pass, izena,tel);
			
		}
		resultSet.close();
	}
	catch (SQLException anException)
	{
		while (anException != null)
		{
			System.out.println("SQL Exception:  " + anException.getMessage());
			anException = anException.getNextException();
		}
	}
	catch (java.lang.Exception anException)
	{
		anException.printStackTrace();
	}
	finally
	{
		return bezeroa;
	}
}
public List<Integer> irakurriKontuak(int ID )
{
  	// Deklarazioak
	List<Integer> kontuak = new ArrayList<Integer>();
        String lotura = "select Kontu_Zenbakia from KontuBezero where Bezeroaren_ID="+ID;
	ResultSet resultSet,resultKontuak;
	// Initilization
	try
	{
		// SQL exekutatu
		
                resultSet = sententzia.executeQuery(lotura);
		//	Resultset-eko errenkada eta zutabe guztiak kapturatu
		while (resultSet.next())
		{
                       
			int kontuZenbakia = Integer.parseInt(resultSet.getString(1));
                        kontuak.add(kontuZenbakia);
                        
			
		}
		resultSet.close();
	}
	catch (SQLException anException)
	{
		while (anException != null)
		{
			System.out.println("SQL Exception:  " + anException.getMessage());
			anException = anException.getNextException();
		}
	}
	catch (java.lang.Exception anException)
	{
		anException.printStackTrace();
	}
	finally
	{
		return kontuak;
	}
}
public Kontua lortuKontua(int kontuZenbakia)
{
  	// Deklarazioak
	String query = "select * from Kontua where Kontu_Zenbakia="+kontuZenbakia;
        Kontua kontua = null;
	
	ResultSet resultSet;
	// Initilization
	
	try
	{
		// SQL exekutatu
		resultSet = sententzia.executeQuery(query);
		//	Resultset-eko errenkada eta zutabe guztiak kapturatu
		while (resultSet.next())
		{ 
                        
                            int zenbakia = Integer.parseInt(resultSet.getString(1));
                            double saldoZaharra = Double.valueOf(resultSet.getString(2)).doubleValue();
                            System.out.println("Kontua: kontuZenbakia = " + kontuZenbakia + " | "  + "saldoZaharra = " + saldoZaharra + " | " );
                            
                            kontua = new Kontua(zenbakia, saldoZaharra);
                            
                        
		}
               
		resultSet.close();
                
                
	}
	catch (SQLException anException)
	{
		while (anException != null)
		{
			System.out.println("SQL Exception:  " + anException.getMessage());
			anException = anException.getNextException();
		}
	}
	catch (java.lang.Exception anException)
	{
		anException.printStackTrace();
	}
	finally
	{
		return kontua;
	}
}
public void setKonekzioa(Connection konekzioBerria)
{
	konekzioa = konekzioBerria;
}
public void setSententzia(Statement sententziBerria)
{
	sententzia = sententziBerria;
}
public void setUrl(String urlBerria)
{
	urla = urlBerria;
}

public int irakurriHasierakoTransakzioZenbakia()
{
  	// Deklarazioak
	String query = "select MAX(Trantsakzio_Zenbaki) as Zenbat from Transakzioa";
	int transakzioZenbakia = 30000;
	ResultSet resultSet;	
	try
	{
		// SQL exekutatu
		resultSet = sententzia.executeQuery(query);
		//	Resultset-eko errenkada eta zutabe guztiak kapturatu
		while (resultSet.next())
		{     
			transakzioZenbakia = resultSet.getInt("Zenbat");		
		}
		resultSet.close();
	}
	catch (SQLException anException)
	{
		while (anException != null)
		{
			System.out.println("SQL Exception irakurtzen:  " + anException.getMessage());
			anException = anException.getNextException();
		}
	}
	catch (java.lang.Exception anException)
	{
		anException.printStackTrace();
	}
	finally
	{
      if (transakzioZenbakia == 0)
        transakzioZenbakia = 30000;
		return transakzioZenbakia;
	}
        
}

public int irakurriHasierakoSarreraZenbakia()
{
  	// Deklarazioak
	String query = "select MAX(Zenbakia) as Zenbat from Sarrera";
	int transakzioZenbakia = 30000;
	ResultSet resultSet;	
	try
	{
		// SQL exekutatu
		resultSet = sententzia.executeQuery(query);
		//	Resultset-eko errenkada eta zutabe guztiak kapturatu
		while (resultSet.next())
		{     
			transakzioZenbakia = resultSet.getInt("Zenbat");		
		}
		resultSet.close();
	}
	catch (SQLException anException)
	{
		while (anException != null)
		{
			System.out.println("SQL Exception irakurtzen:  " + anException.getMessage());
			anException = anException.getNextException();
		}
	}
	catch (java.lang.Exception anException)
	{
		anException.printStackTrace();
	}
	finally
	{
      if (transakzioZenbakia == 0)
        transakzioZenbakia = 30000;
		return transakzioZenbakia;
	}
        
}
public int irakurriHasierakoErosketaZenbakia()
{
  	// Deklarazioak
	String query = "select MAX(Zenbakia) as Zenbat from Erosketa";
	int transakzioZenbakia = 30000;
	ResultSet resultSet;	
	try
	{
		// SQL exekutatu
		resultSet = sententzia.executeQuery(query);
		//	Resultset-eko errenkada eta zutabe guztiak kapturatu
		while (resultSet.next())
		{     
			transakzioZenbakia = resultSet.getInt("Zenbat");		
		}
		resultSet.close();
	}
	catch (SQLException anException)
	{
		while (anException != null)
		{
			System.out.println("SQL Exception irakurtzen:  " + anException.getMessage());
			anException = anException.getNextException();
		}
	}
	catch (java.lang.Exception anException)
	{
		anException.printStackTrace();
	}
	finally
	{
      if (transakzioZenbakia == 0)
        transakzioZenbakia = 30000;
		return transakzioZenbakia;
	}
        
}
public List<String> getHerriak()
{
  	// Deklarazioak
	String query = "select Hiria from Zinea";
	List<String> Herriak= new ArrayList<String>();
	ResultSet resultSet;
	// Initilization
	
	try
	{
		// SQL exekutatu
		resultSet = sententzia.executeQuery(query);
		//	Resultset-eko errenkada eta zutabe guztiak kapturatu
		while (resultSet.next())
		{
			String herri = resultSet.getString(1);
			Herriak.add(herri);
			
		}
		resultSet.close();
	}
	catch (SQLException anException)
	{
		while (anException != null)
		{
			System.out.println("SQL Exception:  " + anException.getMessage());
			anException = anException.getNextException();
		}
	}
	catch (java.lang.Exception anException)
	{
		anException.printStackTrace();
	}
	finally
	{
		return Herriak;
	}
}
public List<String> getZineak(String hiria)
{
  	// Deklarazioak
	String query = "select Zinema_Izena from Zinea where Hiria='"+hiria+"'";
	List<String> Zineak= new ArrayList<String>();
	ResultSet resultSet;
	// Initilization
	
	try
	{
		// SQL exekutatu
            
		resultSet = sententzia.executeQuery(query);
		//	Resultset-eko errenkada eta zutabe guztiak kapturatu
		while (resultSet.next())
		{
			String zine = resultSet.getString(1);
			Zineak.add(zine);
			
		}
		resultSet.close();
	}
	catch (SQLException anException)
	{
		while (anException != null)
		{
			System.out.println("SQL Exception:  " + anException.getMessage());
			anException = anException.getNextException();
		}
	}
	catch (java.lang.Exception anException)
	{
		anException.printStackTrace();
	}
	finally
	{
		return Zineak;
	}
}
public List<Emanaldia> aurkituFilmak(String data,String Hiria,String Zinea)
{
  	// Deklarazioak
	String query = "select * from Emanaldia where Hiria='"+Hiria+"' and Zinema_Izena='"+Zinea+"' and Data='"+data+"'";
	List<Emanaldia> Emanaldiak= new ArrayList<Emanaldia>();
	ResultSet resultSet;
	// Initilization
	
	try
	{
		// SQL exekutatu
            
		resultSet = sententzia.executeQuery(query);
		//	Resultset-eko errenkada eta zutabe guztiak kapturatu
		while (resultSet.next())
		{
                        
			String hiria = resultSet.getString(1);
                        String zinea = resultSet.getString(2);
                        String film = resultSet.getString(3);
                        String date = resultSet.getString(4);
                        String ordua = resultSet.getString(5);
                        String argazkia = resultSet.getString(6);
                        int prezioa= Integer.parseInt(resultSet.getString(7));
                        Emanaldia em= new Emanaldia(hiria,zinea,film,date,ordua,argazkia,prezioa);                     
			Emanaldiak.add(em);
			
		}
		resultSet.close();
	}
	catch (SQLException anException)
	{
		while (anException != null)
		{
			System.out.println("SQL Exception:  " + anException.getMessage());
			anException = anException.getNextException();
		}
	}
	catch (java.lang.Exception anException)
	{
		anException.printStackTrace();
	}
	finally
	{
		return Emanaldiak;
	}
}
public List<String> aurkituOrduak(String data,String Hiria,String Zinea,String filma)
{
  	// Deklarazioak
	String query = "select Ordua from Emanaldia where Hiria='"+Hiria+"' and Zinema_Izena='"+Zinea+"' and Data='"+data+"' and Film_Izena='"+filma+"'";
	List<String> Orduak= new ArrayList<String>();
	ResultSet resultSet;
	// Initilization
	System.out.println(query);
	try
	{
		// SQL exekutatu
            
		resultSet = sententzia.executeQuery(query);
		//	Resultset-eko errenkada eta zutabe guztiak kapturatu
		while (resultSet.next())
		{
			String ordua = resultSet.getString(1);
                                
			Orduak.add(ordua);
			
		}
		resultSet.close();
	}
	catch (SQLException anException)
	{
		while (anException != null)
		{
			System.out.println("SQL Exception:  " + anException.getMessage());
			anException = anException.getNextException();
		}
	}
	catch (java.lang.Exception anException)
	{
		anException.printStackTrace();
	}
	finally
	{
		return Orduak;
	}
}
public List<Sarrera> aurkituSarrerak(String data,String Hiria,String Zinea,String filma,String ordua)
{
  	// Deklarazioak
	String query = "select * from Sarrera where Hiria='"+Hiria+"' and Zinema_Izena='"+Zinea+"' and Data='"+data+"' and Film_Izena='"+filma+"' and Ordua='"+ordua+"'";
	List<Sarrera> Sarrerak= new ArrayList<Sarrera>();
	ResultSet resultSet;
	// Initilization
	
	try
	{
		// SQL exekutatu
                System.out.println(query);
		resultSet = sententzia.executeQuery(query);
		//	Resultset-eko errenkada eta zutabe guztiak kapturatu
		while (resultSet.next())
		{
                        
                        int Zenb= Integer.parseInt(resultSet.getString(1));
                        int butaka= Integer.parseInt(resultSet.getString(2));
                        int prezioa= Integer.parseInt(resultSet.getString(3));
			String hiria = resultSet.getString(4);
                        String zinea = resultSet.getString(5);
                        String film = resultSet.getString(6);
                        String date = resultSet.getString(7);
                        String ordu = resultSet.getString(8);
                        int saldua= Integer.parseInt(resultSet.getString(9));
                        Sarrera sar=null;
                        if(saldua==1){
                            sar= new Sarrera(Zenb,butaka,prezioa,hiria,zinea,film,date,ordu,true);
                        }
                        else{
                            sar= new Sarrera(Zenb,butaka,prezioa,hiria,zinea,film,date,ordu,false);
                        }
			Sarrerak.add(sar);
			
			
		}
		resultSet.close();
	}
	catch (SQLException anException)
	{
		while (anException != null)
		{
			System.out.println("SQL Exception:  " + anException.getMessage());
			anException = anException.getNextException();
		}
	}
	catch (java.lang.Exception anException)
	{
		anException.printStackTrace();
	}
	finally
	{
		return Sarrerak;
	}
}
	//JUNIT test
public void sartuButaka( String butaka)
{
	
        
	try
	{
		// Insert sql exekutatu sartutako erregistroak itzuliz
                sententzia.executeUpdate(butaka);             
	}
	catch (SQLException anException)
	{
		while (anException != null)
		{
			System.out.println(" SQL Exception Transakzioa sartzen: " + anException.getMessage());
			anException = anException.getNextException();
		}
	}
	catch (java.lang.Exception anException)
	{
		anException.printStackTrace();
	}
}
}
