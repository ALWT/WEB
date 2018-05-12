package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import classes.*;

public interface Medicamentinter extends Remote {
    public List<Med_Farmacie> getMed_Farm(Medicament p) throws RemoteException;
    public List<Med_Farmacie> getMed_Farm(int pid)throws RemoteException;
    public List<Med_Farmacie> getMed_Farm(String p)throws RemoteException;
    
    public Med_Farmacie getMed_Farm(Medicament p,Farmacie f) throws RemoteException;
    public Med_Farmacie getMed_Farm(String p,String f) throws RemoteException;
    public Med_Farmacie getMed_Farm(int pid,int fid)throws RemoteException;
    
    public List<Farmacie> getFarm(int pid)throws RemoteException;
    public List<Farmacie> getFarm(String p)throws RemoteException;
    public List<Farmacie> getFarm(Medicament p)throws RemoteException;
}
