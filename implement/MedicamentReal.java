package implement;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import classes.Farmacie;
import classes.Med_Farmacie;
import classes.Medicament;
import interfaces.Medicamentinter;

public class MedicamentReal implements Medicamentinter{
	private String JDBC_DRIVER = "com.mysql.jdbc.Driver",host;  
	private String DB_URL,dbase;
	private String USER;
	private String PASS;
	private Connection conn = null;
	private Statement stmt = null;
	
    public MedicamentReal(String host,String dbase,String USER,String PASS)
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
	public List<Med_Farmacie> getMed_Farm(Medicament p) throws RemoteException {
		if(!(this.host.equals(p.getHost())&&this.dbase.equals(p.getDBase())))
	    	return null;
		return this.getMed_Farm(p.getID());
	}

	@Override
	public List<Med_Farmacie> getMed_Farm(int pid) throws RemoteException {
		List<Med_Farmacie> ls=new ArrayList<Med_Farmacie>();
		int id_farmacie,id_med_farm,cantitate;
	        try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	            stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "SELECT mf.* FROM med_farmacie mf WHERE mf.id_medicament="+pid;
			      ResultSet rs = stmt.executeQuery(sql);
			      
	                      while(rs.next())
	                      {id_farmacie=rs.getInt("id_farmacie");
	 	                 id_med_farm=rs.getInt("id_med_farm");
		                 cantitate=rs.getInt("cantitate");
		           	 ls.add(new Med_Farmacie(id_farmacie,pid,id_med_farm,cantitate,host,dbase));
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
	       return ls;
	}
	
	public List<Med_Farmacie> getMed_Farm(String med) throws RemoteException {
		List<Med_Farmacie> ls=new ArrayList<Med_Farmacie>();
		int id_medicament,id_med_farm,cantitate,id_farmacie; 
	        try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	            stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "SELECT mf.* FROM med_farmacie mf,medicament m WHERE mf.id_medicament=m.id_medicament AND m.nume='"+med+"'";
			      ResultSet rs = stmt.executeQuery(sql);
			      
	                      while(rs.next())
	                      {id_farmacie=rs.getInt("id_farmacie");
	                      id_medicament=rs.getInt("id_medicament");
	 	                 id_med_farm=rs.getInt("id_med_farm");
		                 cantitate=rs.getInt("cantitate");
		           	 ls.add(new Med_Farmacie(id_farmacie,id_medicament,id_med_farm,cantitate,host,dbase));
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
	       return ls;
	}

	
	
	@Override
	public List<Farmacie> getFarm(int id_med) throws RemoteException 
		{List<Farmacie> ls=new ArrayList<Farmacie>();
	      int id;      
	      String nume,adresa,nrtel;  
		        try{
		        	 Class.forName(JDBC_DRIVER);
		        	 System.out.println("Connecting to database...");
		        	 conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
		             stmt = (Statement) conn.createStatement();
				      String sql;
				      sql = "SELECT f.* FROM farmacie f,med_farmacie mf,medicament m WHERE mf.id_medicament=m.id_medicament AND f.id_farmacie=mf.id_farmacie AND m.id_medicament="+id_med;
				      ResultSet rs = stmt.executeQuery(sql);
				      
                      while(rs.next())
                      {id  = rs.getInt("id_farmacie");
     		         nume = rs.getString("nume");
                     adresa = rs.getString("adresa");
                     nrtel = rs.getString("telefon");
                     ls.add(new Farmacie(id,nume,adresa,nrtel,host,dbase));
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
		       return ls;
		}
	
	
	
	@Override
	public List<Farmacie> getFarm(String p) throws RemoteException {
		{List<Farmacie> ls=new ArrayList<Farmacie>();
	      int id;      
	      String nume,adresa,nrtel;  
		        try{Class.forName(JDBC_DRIVER);
			  System.out.println("Connecting to database...");
			  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
		            stmt = (Statement) conn.createStatement();
				      String sql;
				      sql = "SELECT m.* FROM farmacie f,med_farmacie mf,medicament m WHERE mf.id_medicament=m.id_medicament AND f.id_farmacie=mf.id_farmacie AND m.nume="+p;
				      ResultSet rs = stmt.executeQuery(sql);
				      
		                      while(rs.next())
		                      {id  = rs.getInt("id_farmacie");
		     		         nume = rs.getString("nume");
	                         adresa = rs.getString("adresa");
	                         nrtel = rs.getString("telefon");
	                         ls.add(new Farmacie(id,nume,adresa,nrtel,host,dbase));
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
		       return ls;
		}
	}
	
	public List<Farmacie> getFarm(Medicament p) throws RemoteException {
		if(!(this.host.equals(p.getHost())&&this.dbase.equals(p.getDBase())))
	    	return null;
		return this.getFarm(p.getID());
	}
	
	
	public Med_Farmacie getMed_Farm(Medicament p,Farmacie f) throws RemoteException {
		if(!(this.host.equals(p.getHost())&&this.dbase.equals(p.getDBase()))||!(this.host.equals(f.getHost())&&this.dbase.equals(f.getDBase())))
			return null;
		return this.getMed_Farm(p.getID(), f.getID());
	}

	@Override
	public Med_Farmacie getMed_Farm(int med,int farm)throws RemoteException {
		int id,cant;      
        Med_Farmacie s=null;  
	        try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	            stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "SELECT mf.* FROM med_farmacie mf WHERE mf.id_medicament="+med+"  AND mf.id_farmacie="+farm;
			      ResultSet rs = stmt.executeQuery(sql);
			      
	                      while(rs.next())
	                      {id  = rs.getInt("id_medfarm");
	                      cant  = rs.getInt("cantitate");
	                       s=new Med_Farmacie(farm, med, id, cant, host, dbase);
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
	       return s;
	}

	

	@Override
	public Med_Farmacie getMed_Farm(String m,String f) throws RemoteException {
		int id,fid,pid,cant;      
        Med_Farmacie s=null; 
	        try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	            stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "SELECT m.* FROM farmacie f,med_farmacie mf,medicament m WHERE mf.id_medicament=m.id_medicament AND f.id_farmacie=mf.id_farmacie AND m.nume="+m+"AND f.nume='"+f+"'";
			      ResultSet rs = stmt.executeQuery(sql);
			      
	                      while(rs.next())
	                      {id  = rs.getInt("id_medfarm");
	                       fid  = rs.getInt("id_farmacie");
	                       pid  = rs.getInt("id_medicament");
	                      cant  = rs.getInt("cantitate");
	                       s=new Med_Farmacie(fid, pid, id, cant, host, dbase);
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
	       return s;
	}

	

}