package Lab7_code.Interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface KInterface extends Remote{
    /**
     * Will send message to client
     * @param s message to send
     * @param notifyConnected if true will also notify client connected to this client about message
     * @throws RemoteException
     */
    void pisz (String s, boolean notifyConnected) throws RemoteException;

    /**
     * Will rise event which will update list of clients in all of them
     * @param allClients
     * @throws RemoteException
     */
    void UpdateAllClientsData(String[] allClients) throws RemoteException;
}
