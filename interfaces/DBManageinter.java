package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;
import classes.*;

public interface DBManageinter extends Remote {
    public List<Farmacie> getFarmacii() throws RemoteException;
    public Farmacie getFarmacieId(int FID) throws RemoteException;
    public Farmacie getFarmacieNume(String nume) throws RemoteException;
    public Med_Farmacie getStocID(int SID) throws RemoteException;
    public String getHost() throws RemoteException;
    public Medicament getMedicamentID(int PID) throws RemoteException;
    public Medicament getMedicamentName(String name) throws RemoteException;
    public Farmacist getFarmacist(int id_farmacist) throws RemoteException;
    public Farmacist getFarmacist(Farmacie farmacie) throws RemoteException;
    public Farmacist getFarmacist(String farmacist) throws RemoteException;
}
