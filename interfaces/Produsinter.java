package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import classes.*;

public interface Produsinter extends Remote {
    public List<Stoc> getStoc(Produs p) throws RemoteException;
    public List<Stoc> getStoc(int pid)throws RemoteException;
    public List<Stoc> getStoc(String p)throws RemoteException;
    public Stoc getStoc(Produs p,Farmacie f) throws RemoteException;
    public Stoc getStoc(String p,String f) throws RemoteException;
    public Stoc getStoc(int pid,int fid)throws RemoteException;
    public List<Farmacie> getFarm(int pid)throws RemoteException;
    public List<Farmacie> getFarm(String p)throws RemoteException;
    public List<Farmacie> getFarm(Produs p)throws RemoteException;
}
