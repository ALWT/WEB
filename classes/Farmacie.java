package classes;
import java.rmi.RemoteException;


public class Farmacie implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id_farmacie;
    private String nume,adresa,host,dbase,telefon;
    
    public Farmacie(int id_farmacie,String nume,String adresa,String telefon,String host,String dbase)
    {
    	this.id_farmacie=id_farmacie;
	    this.nume=nume;
	    this.adresa=adresa;
	    this.telefon=telefon;
	    this.host=host;
	    this.dbase=dbase;
    }
    public int getID() 
    {
    	return this.id_farmacie;
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
    
    public String getAdresa() 
    {
    	return this.adresa;
    }
    public String getNrtel() 
    {
    	return this.telefon;
    }
     
}
