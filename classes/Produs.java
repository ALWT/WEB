package classes;
import java.rmi.*;

public class Produs implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
    private String nume,clasa,host,dbase;
    public Produs(int id,String nume,String clasa,String host,String dbase)
    {this.id=id;
     this.nume=nume;
     this.clasa=clasa;
     this.host=host;
     this.dbase=dbase;}
    public int getID() throws RemoteException
    {return this.id;}
    public String getNume() throws RemoteException
    {return this.nume;}
    public String getClasa() throws RemoteException
    {return this.clasa;}
    public String getHost() throws RemoteException
    {return this.host;}
    public String getDBase() throws RemoteException
    {return this.dbase;}
}
