package classes;
import java.rmi.RemoteException;


public class Farmacist implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id_farmacist;
	private int id_farmacie;
    private String nume,parola,host,dbase;
    
    public Farmacist(int id_farmacie,int id_farmacist,String nume,String parola,String host,String dbase)
    {
    	this.id_farmacie=id_farmacie;
    	this.id_farmacist=id_farmacist;
	    this.nume=nume;
	    this.parola=parola;
	    this.host=host;
	    this.dbase=dbase;
    }
    public int getIDFarmacie() 
    {
    	return this.id_farmacie;
    }
    public int getIDFarmacist() 
    {
    	return this.id_farmacist;
    }
    public String getNume() 
    {
    	return this.nume;
    }
    public String getHost() 
    {
    	return this.host;
    }
    public String getDBase() 
    {
    	return this.dbase;
    }
    
    public String getParola() 
    {
    	return this.parola;
    }
   
     
}
