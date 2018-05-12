package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
import classes.*;


public interface Med_Farmacieinter extends Remote {
	 public Farmacie getFarm(int sid)throws RemoteException;
	 public Farmacie getFarm(Med_Farmacie s)throws RemoteException;
	 public Medicament getMedicament(int sid) throws RemoteException;
	 public Medicament getMedicament(Med_Farmacie s) throws RemoteException;
}
