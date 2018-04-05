package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;
import classes.*;

public interface Farmacieinter extends Remote {
    public List<Produs> getProductsFarmacie(Farmacie f) throws RemoteException;
    public List<Produs> getProductsFarmacie(int fid) throws RemoteException;
    public List<Produs> getProductsFarmacie(String f) throws RemoteException;
    public List<Stoc> getStocFarmacie(Farmacie f) throws RemoteException;
    public List<Stoc> getStocFarmacie(int fid) throws RemoteException;
    public List<Stoc> getStocFarmacie(String f) throws RemoteException;
    
}
