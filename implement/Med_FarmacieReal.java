package implement;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Farmacie;
import classes.Med_Farmacie;
import classes.Medicament;
import interfaces.Med_Farmacieinter;

public class Med_FarmacieReal implements Med_Farmacieinter {
	private String JDBC_DRIVER = "com.mysql.jdbc.Driver",host;  
	private String DB_URL,dbase;
	private String USER;
	private String PASS;
	private Connection conn = null;
	private Statement stmt = null;
	
    public Med_FarmacieReal(String host,String dbase,String USER,String PASS)
    {this.host=host;
     this.dbase=(dbase==null)?"Test":dbase;
     this.USER=(USER==null)?"root":USER;
     this.PASS=(PASS==null)?"":PASS;
     this.DB_URL= "jdbc:mysql://"+host+"/"+this.dbase;
    }
    
    public String getDBase() throws RemoteException
    {return this.dbase;}
    
    public String getHost() throws RemoteException
    {return this.host;}
    
	@Override
	public Farmacie getFarm(int sid) throws RemoteException {
	    Farmacie f=null;
	    int id;      
	      String nume,adresa,nrtel; 
	        try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	            stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "SELECT f.* FROM med_farmacie mf,farmacie f WHERE mf.id_farmacie=f.id_farmacie AND mf.id_med_farm="+sid;
			      ResultSet rs = stmt.executeQuery(sql);
			      
	                      if(rs.next())
	                      {id  = rs.getInt("id_farmacie");
		                  nume = rs.getString("nume");
                         adresa = rs.getString("adresa");
                         nrtel = rs.getString("telefon");
                         f=(new Farmacie(id,nume,adresa,nrtel,host,dbase));
	                      }
			     
			      rs.close();
			      stmt.close();
			      conn.close();
			   }catch(SQLException se){
			      se.printStackTrace();
			   }catch(Exception e){
			      e.printStackTrace();
			   }finally{
			      try{
			         if(stmt!=null)
			            stmt.close();
			      }catch(SQLException se2){}
			      try{  if(conn!=null)
			            conn.close();
			      }catch(SQLException se){se.printStackTrace();}
			   }
	       return f;
	}

	@Override
	public Farmacie getFarm(Med_Farmacie s) throws RemoteException {
		if(!(this.host.equals(s.getHost())&&this.dbase.equals(s.getDBase())))
			return null;
		return this.getFarm(s.getIDMedFarm());
	}

	@Override
	public Medicament getMedicament(int id_med_farm) throws RemoteException {
		int id_medicament;
	       Medicament p=null;
	        String nume,poza,descriere;
	        double pret;     
	        try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	            stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "SELECT m.* FROM medicament m,med_farmacie mf WHERE m.id_medicament=mf.id_medicament AND mf.id_med_farm="+id_med_farm;
			      ResultSet rs = stmt.executeQuery(sql);
			      
	                      if(rs.next())
	                      {id_medicament=rs.getInt("id_medicament");
	                      nume=rs.getString("nume");
	                      pret=rs.getDouble("pret");
	                      poza=rs.getString("poza");
	                      descriere=rs.getString("descriere");
	                      p=new Medicament(id_medicament,nume, pret, poza, descriere, host, dbase);
	                      }
			     
			      rs.close();
			      stmt.close();
			      conn.close();
			   }catch(SQLException se){
			      se.printStackTrace();
			   }catch(Exception e){
			      e.printStackTrace();
			   }finally{
			      try{
			         if(stmt!=null)
			            stmt.close();
			      }catch(SQLException se2){}
			      try{  if(conn!=null)
			            conn.close();
			      }catch(SQLException se){se.printStackTrace();}
			   }
	       return p;}

	@Override
	public Medicament getMedicament(Med_Farmacie s) throws RemoteException {
		if(!(this.host.equals(s.getHost())&&this.dbase.equals(s.getDBase())))
			return null;
		return this.getMedicament(s.getIDMedFarm());
	}
}
