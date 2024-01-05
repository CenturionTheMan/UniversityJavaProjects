package Lab7_code.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
public interface SInterface extends Remote{
    /**
     * Will add given client to server clients
     * @param u client class
     * @param n client name
     * @return 1 if success, -1 otherwise
     * @throws RemoteException
     */
    int zapisz (KInterface u, String n) throws RemoteException;

    /**
     * Will remove client with given name from server
     * @param n client name
     * @return 1 if success, -1 otherwise
     * @throws RemoteException
     */
    int wypisz(String n) throws RemoteException;

    /**
     * 
     * @return list of all clients names registered to server
     * @throws RemoteException
     */
    String[] listuj() throws RemoteException;

    /**
     * 
     * @param n Name of client
     * @return Client class registered by given name
     * @throws RemoteException
     */
    KInterface pobierz(String n) throws RemoteException;
}
