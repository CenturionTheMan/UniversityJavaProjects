package Lab7_code.Server;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

import Lab7_code.Interfaces.KInterface;
import Lab7_code.Interfaces.SInterface;

public class ServerModel extends UnicastRemoteObject implements SInterface{

    private static final long serialVersionUID = 1L;
    private Registry curRegistry;

    /**
     * CTOR
     * @throws RemoteException
     */
    protected ServerModel() throws RemoteException {
        super();
    }

    /**
     * Will rum server
     * @param port 
     * @param hostName
     */
    public void RunServer(int port, String hostName)
    {
        System.setProperty("java.rmi.server.hostname",hostName);


        try{
			curRegistry = java.rmi.registry.LocateRegistry.createRegistry(port);
			System.out.println("RMI Server ready");
		}
		catch(RemoteException e) {
			e.printStackTrace();
            return;
		}


        try {
            curRegistry.rebind(hostName, this);
            System.out.println("Server is running");
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    
    
    @Override
    public int zapisz(KInterface u, String n) throws RemoteException {

        try {    
            if(Arrays.stream(listuj()).anyMatch(s -> s.contentEquals(n)))
            {
                return -1;
            }
            curRegistry.bind(n, u);
            System.out.println("New client: " + n);
            return 1;
          }
          catch(Exception e){
            System.err.println(e);
            return -1;
          }
    
    }

    @Override
    public int wypisz(String n) throws RemoteException {
        try {
            curRegistry.unbind(n);
            System.out.println("Client removed: " + n);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public String[] listuj() throws RemoteException {
        try {
            var names = curRegistry.list();
            return names;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public KInterface pobierz(String n) throws RemoteException {
        try {
            var nam = (KInterface)curRegistry.lookup(n);
            return nam;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
