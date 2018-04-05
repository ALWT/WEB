package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
import classes.*;


public interface Stocinter extends Remote {
	 public Farmacie getFarm(int sid)throws RemoteException;
	 public Farmacie getFarm(Stoc s)throws RemoteException;
	 public Produs getProdus(int sid) throws RemoteException;
	 public Produs getProdus(Stoc s) throws RemoteException;
}
