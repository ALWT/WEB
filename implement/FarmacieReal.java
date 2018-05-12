package implement;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.*;
import interfaces.Farmacieinter;
import classes.*;
import classes.Farmacie;
import classes.Medicament;


public class FarmacieReal implements Farmacieinter {
	private String JDBC_DRIVER = "com.mysql.jdbc.Driver",host;  
	private String DB_URL,dbase;
	private String USER;
	private String PASS;
	private Connection conn = null;
	private Statement stmt = null;
	
    public FarmacieReal(String host,String dbase,String USER,String PASS)
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
    
    
    public List<Medicament> getMedicamentsFarmacie(Farmacie f) throws RemoteException
    {System.out.println(f.getHost()+" "+f.getDBase());
    System.out.println(this.host+" "+this.dbase);
    	if(!(this.host.equals(f.getHost())&&this.dbase.equals(f.getDBase())))
    	{System.out.println("getProductsFarmacie null");
    	return null;}
    return this.getMedicamentsFarmacie(f.getID());}
     
	@Override
	public List<Medicament> getMedicamentsFarmacie(int fid) throws RemoteException { 
    List<Medicament> ls=new ArrayList<Medicament>();
    int id_medicament;
    String nume;
    double pret;
    String poza;
    String descriere;
        try{Class.forName(JDBC_DRIVER);
	  System.out.println("Connecting to database...");
	  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "SELECT m.* FROM farmacie f,medicament m,med_farmacie mf WHERE f.id_farmacie="+fid+" AND m.id_medicament=mf.id_medicament";
		      ResultSet rs = stmt.executeQuery(sql);
		      
                      while(rs.next())
                      { id_medicament=rs.getInt("id_medicament");
                     nume=rs.getString("nume");
                      pret=rs.getDouble("pret");
                      poza=rs.getString("poza");
                      descriere=rs.getString("descriere");
                      ls.add(new Medicament(id_medicament,nume,pret,poza,descriere,host,dbase));
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
       return ls;}


	@Override
	public List<Medicament> getMedicamentsFarmacie(String f) throws RemoteException { 
	    List<Medicament> ls=new ArrayList<Medicament>();
	    int id_medicament;
	    String nume;
	    double pret;
	    String poza;
	    String descriere;
	        try{Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
		  conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	            stmt = (Statement) conn.createStatement();
			      String sql;
			      sql = "SELECT m.* FROM farmacie f,medicament m,med_farmacie mf WHERE f.nume='"+f+"' AND m.id_medicament=mf.id_medicament";
			      ResultSet rs = stmt.executeQuery(sql);
			      
	                      while(rs.next())
	                      {id_medicament=rs.getInt("id_medicament");
	                      nume=rs.getString("nume");
	                      pret=rs.getDouble("pret");
	                      poza=rs.getString("poza");
	                      descriere=rs.getString("descriere");
	                      ls.add(new Medicament(id_medicament,nume,pret,poza,descriere,host,dbase));
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
	       return ls;}

	public List<Med_Farmacie> getMed_Farmacie(Farmacie f) throws RemoteException
	{if(!(this.host.equals(f.getHost())&&this.dbase.equals(f.getDBase())))
    	return null;
    return this.getMed_Farmacie(f.getID());
    }
    
	
	@Override
	public List<Med_Farmacie> getMed_Farmacie(int fid) throws RemoteException
	{ 
		List<Med_Farmacie> ls=new ArrayList<Med_Farmacie>();
	int id_medicament,id_med_farm,cantitate;     
	   try{
		   Class.forName(JDBC_DRIVER);
	 System.out.println("Connecting to database...");
	 conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	      stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "SELECT mf.* FROM med_farmacie mf WHERE mf.id_farmacie="+fid;
		      ResultSet rs = stmt.executeQuery(sql);
		      
	                 while(rs.next())
	                 {id_medicament=rs.getInt("id_medicament");
	                 id_med_farm=rs.getInt("id_med_farm");
	                 cantitate=rs.getInt("cantitate");
	           	 ls.add(new Med_Farmacie(fid,id_medicament,id_med_farm,cantitate,host,dbase));}
		    
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
	  return ls;}


	@Override
	public List<Med_Farmacie> getMed_Farmacie(String f) throws RemoteException { 
		List<Med_Farmacie> ls=new ArrayList<Med_Farmacie>();
		int fid,id_medicament,id_med_farm,cantitate; 
	   try{Class.forName(JDBC_DRIVER);
	 System.out.println("Connecting to database...");
	 conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
	      stmt = (Statement) conn.createStatement();
		      String sql;
		      sql = "SELECT mf.* FROM med_farmacie mf,farmacie f WHERE mf.id_farmacie=f.id_farmacie AND f.nume='"+f+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		      
	                 while(rs.next())
	                 {fid=rs.getInt("id_farmacie");
	                 id_medicament=rs.getInt("id+medicament");
	                 id_med_farm=rs.getInt("id_med_farm");
	                 cantitate=rs.getInt("cantitate");
	           	 ls.add(new Med_Farmacie(fid,id_medicament,id_med_farm,cantitate,host,dbase));}
		     
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
	  return ls;}
}
