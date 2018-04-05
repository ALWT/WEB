package classes;
import java.rmi.RemoteException;


public class Farmacie implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
    private String nume,adresa,nrtel,oras,program,host,dbase;
    public Farmacie(int id,String nume,String adresa,String nrtel,String oras,String program,String host,String dbase)
   {this.id=id;
    this.nume=nume;
    this.adresa=adresa;
    this.nrtel=nrtel;
    this.oras=oras;
    this.program=program;
    this.host=host;
    this.dbase=dbase;}
    public int getID() throws RemoteException
   {return this.id;}
    public String getNume() throws RemoteException
    {return this.nume;}
    public String getHost() throws RemoteException
    {return this.host;}
    public String getDBase() throws RemoteException
    {return this.dbase;}
    public String getAdresa() throws RemoteException
    {return this.adresa;}
    public String getNrtel() throws RemoteException
    {return this.nrtel;}
    public String getOras() throws RemoteException
    {return this.oras;}
    public String getProgram() throws RemoteException
    {return this.program;}   
}
