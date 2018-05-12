package implement;
import java.rmi.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import classes.*;
import interfaces.*;

public class DBManageReal implements DBManageinter 
{
    private String JDBC_DRIVER = "com.mysql.jdbc.Driver",host;  
	private String DB_URL,dbase;
	private String USER;
	private String PASS;
	private Connection conn = null;
	private Statement stmt = null;
	
    public DBManageReal(String host,String dbase,String USER,String PASS)
    {
    	this.host=host;
     this.dbase=(dbase==null)?"Test":dbase;
     this.USER=(USER==null)?"root":USER;
     this.PASS=(PASS==null)?"":PASS;
     this.DB_URL= "jdbc:mysql://"+host+"/"+this.dbase;
    }
   
   public String getHost() throws RemoteException
   {return this.host;}
   
   public String getDBase() throws RemoteException
   {return this.dbase;}

   public List<Farmacie> getFarmacii() throws RemoteException
   {
	  List<Farmacie> ls=new ArrayList<Farmacie>();
      int id;      
      String nume,adresa,nrtel;   
      try
      {
    	  Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
          stmt = (Statement) conn.createStatement();
          
	      String sql;
	      sql = "SELECT * FROM farmacie";
	      ResultSet rs = stmt.executeQuery(sql);
		      
          while(rs.next())
          {
	    	  id  = rs.getInt("id_farmacie");
	    	  nume = rs.getString("nume");
              adresa = rs.getString("adresa");
              nrtel = rs.getString("telefon");
              ls.add(new Farmacie(id,nume,adresa,nrtel,host,dbase));
          }
		     
	      rs.close();
	      stmt.close();
	      conn.close();
		   }
      	catch(SQLException se)
      	{
		      se.printStackTrace();
		}
      
      	catch(Exception e)
        {
		      e.printStackTrace();   
        }
      finally
      {
		      try
		      {
		         if(stmt!=null)
		            stmt.close();
		      }
		      catch(SQLException se2){}
		      try
		      { 
		    	  if(conn!=null)
		            conn.close();
		      }
		      catch(SQLException se)
		      {
		    	  se.printStackTrace();
		      }
      }
       return ls;
   } 
   //astA AM SCRIS EU
   
    public Farmacie getFarmacieId(int FID) throws RemoteException
     {int id;    
     Farmacie f=null;
      String nume,adresa,nrtel;   
        try{Class.forName(JDBC_DRIVER);
	  System.out.println("Connecting to database...");
	  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
           stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM farmacie WHERE id_farmacie="+FID;
		      ResultSet rs = stmt.executeQuery(sql);
		      
                      if(rs.next())
                      {id  = rs.getInt("id_farmacie");
		         nume = rs.getString("nume");
                         adresa = rs.getString("adresa");
                         nrtel = rs.getString("telefon");
		     f=new Farmacie(id,nume,adresa,nrtel,host,dbase);}
                      
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
       return f;}

    public Farmacie getFarmacieNume(String nume) throws RemoteException
         {int id; 
         Farmacie f=null;
      String adresa,nrtel;   
            try{Class.forName(JDBC_DRIVER);
	  System.out.println("Connecting to database...");
	  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
                   stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM farmacie WHERE nume='"+nume+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		      
                      if(rs.next())
                      {id  = rs.getInt("id_farmacie");
                         adresa = rs.getString("adresa");
                         nrtel = rs.getString("telefon");
		      f=new Farmacie(id,nume,adresa,nrtel,host,dbase);}
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
       return f;}

    public Med_Farmacie getStocID(int id_medfarm) throws RemoteException
         {int id,fid,pid,cant;      
         Med_Farmacie s=null;
        try{Class.forName(JDBC_DRIVER);
	  System.out.println("Connecting to database...");
	  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
           stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM med_farmacie WHERE id_medfarm="+id_medfarm;
		      ResultSet rs = stmt.executeQuery(sql);
		      
                      if(rs.next())
                      {id  = rs.getInt("id_medfarm");
                       fid  = rs.getInt("id_farmacie");
                       pid  = rs.getInt("id_medicament");
                      cant  = rs.getInt("cantitate");
                       s=new Med_Farmacie(fid, pid, id, cant, host, dbase);}
		     
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
       return s;}

    public Medicament getMedicamentID(int PID) throws RemoteException
       {int id_medicament;
       Medicament p=null;
        String nume,poza,descriere;
        double pret;
        try{Class.forName(JDBC_DRIVER);
	  System.out.println("Connecting to database...");
	  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM medicament WHERE id_medicament="+PID;
		      ResultSet rs = stmt.executeQuery(sql);
		      
                      if(rs.next())
                      {id_medicament=rs.getInt("id_medicament");
                      nume=rs.getString("nume");
                      pret=rs.getDouble("pret");
                      poza=rs.getString("poza");
                      descriere=rs.getString("descriere");
                      p=new Medicament(id_medicament,nume, pret, poza, descriere, host, dbase);}
		     
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
    
    public Medicament getMedicamentName(String name) throws RemoteException
    {
    	int id_medicament;
		 Medicament p=null;
		 String nume,poza,descriere;
		 double pret;  
		 
		 try{
			 Class.forName("com.mysql.jdbc.Driver");
			 System.out.println("Connecting to database...");
			 
			 conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
			 stmt = (Statement)conn.createStatement();
			 
		     String sql;
		     sql = "SELECT * FROM medicament WHERE nume='"+name+"'";
		     ResultSet rs = stmt.executeQuery(sql);
		      
             if(rs.next())
             {
            	  id_medicament=rs.getInt("id_medicament");
            	  nume=rs.getString("nume");
            	  pret=rs.getDouble("pret");
            	  poza=rs.getString("poza");
            	  descriere=rs.getString("descriere");
            	  p=new Medicament(id_medicament,nume, pret, poza, descriere, host, dbase);
             }
             
		     rs.close();
		     stmt.close();
		     conn.close();
		   	}
		 	catch(SQLException se)
		 	{
		 		se.printStackTrace();
		 	}
		 	catch(Exception e)
		 	{
		      e.printStackTrace();
		 	}
		 	finally
		 	{
		      try
		      {
		         if(stmt!=null)
		            stmt.close();
		      }
		      catch(SQLException se2){}
		      
		      try
		      {  
		    	  if(conn!=null)
		            conn.close();
		      }
		      catch(SQLException se){se.printStackTrace();}
		 	}
       return p;
}


	@Override
	public Farmacist getFarmacist(int id_farmacist) throws RemoteException {
		int id_farmacie;
		String nume,parola;
	       Farmacist p=null;
	        try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	            stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "SELECT * FROM farmacist WHERE id_farmacist="+id_farmacist;
			      ResultSet rs = stmt.executeQuery(sql);
			      
	                      if(rs.next())
	                      {id_farmacie=rs.getInt("id_farmacie");
	                      nume=rs.getString("nume");
	                      parola=rs.getString("parola");
	                      p=new Farmacist(id_farmacie, id_farmacist, nume, parola, host, dbase);}
			     
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
	       return p;
	}

	@Override
	public Farmacist getFarmacist(Farmacie farmacie) throws RemoteException {
		int id_farmacie,id_farmacist;
		String nume,parola;
	       Farmacist p=null;
	        try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	            stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "SELECT * FROM farmacist WHERE id_farmacie="+farmacie.getID();
			      ResultSet rs = stmt.executeQuery(sql);
			      
	                      if(rs.next())
	                      {id_farmacie=rs.getInt("id_farmacie");
	                      id_farmacist=rs.getInt("id_farmacist");
	                      parola=rs.getString("parola");
	                      nume=rs.getString("nume");
	                      p=new Farmacist(id_farmacie, id_farmacist, nume, parola, host, dbase);}
			     
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
	       return p;
	}

	@Override
	public Farmacist getFarmacist(String farmacist) throws RemoteException {
		int id_farmacie,id_farmacist;
		String parola;
	       Farmacist p=null;
	        try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	            stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "SELECT * FROM farmacist WHERE nume="+farmacist;
			      ResultSet rs = stmt.executeQuery(sql);
			      
	                      if(rs.next())
	                      {id_farmacie=rs.getInt("id_farmacie");
	                      id_farmacist=rs.getInt("id_farmacist");
	                      parola=rs.getString("parola");
	                      p=new Farmacist(id_farmacie, id_farmacist, farmacist, parola, host, dbase);}
			     
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
	       return p;
	}

}