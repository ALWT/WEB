package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;
import classes.*;

public interface DBManageinter extends Remote {
    public List<Farmacie> getFarmacii() throws RemoteException;
    public Farmacie getFarmacieId(int FID) throws RemoteException;
    public Farmacie getFarmacieNume(String nume) throws RemoteException;
    public Stoc getStocID(int SID) throws RemoteException;
    public String getHost() throws RemoteException;
    public Produs getProductID(int PID) throws RemoteException;
    public Produs getProductName(String name) throws RemoteException;
    public Produs getProductClass(String clasa) throws RemoteException;
}
