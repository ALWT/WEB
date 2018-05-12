package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;
import classes.*;

public interface Farmacieinter extends Remote {
    public List<Medicament> getMedicamentsFarmacie(Farmacie f) throws RemoteException;
    public List<Medicament> getMedicamentsFarmacie(int fid) throws RemoteException;
    public List<Medicament> getMedicamentsFarmacie(String f) throws RemoteException;
    public List<Med_Farmacie> getMed_Farmacie(Farmacie f) throws RemoteException;
    public List<Med_Farmacie> getMed_Farmacie(int fid) throws RemoteException;
    public List<Med_Farmacie> getMed_Farmacie(String f) throws RemoteException;
    
}
