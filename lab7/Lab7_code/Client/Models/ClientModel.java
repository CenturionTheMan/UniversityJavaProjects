package Lab7_code.Client.Models;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

import Lab7_code.Client.ViewModels.MainViewModel;
import Lab7_code.Interfaces.KInterface;
import Lab7_code.Interfaces.SInterface;
public class ClientModel extends UnicastRemoteObject implements KInterface{

    private MainViewModel viewModel;

    private SInterface server;
    private String serverName;
    private String clientName;
    private KInterface connectedClient;

    /**
     * CTOR
     * @throws RemoteException
     */
    public ClientModel() throws RemoteException {
        super();
    }

    /**
     * Will set view model
     * @param viewModel
     */
    public void SetViewModel(MainViewModel viewModel)
    {
        this.viewModel = viewModel;
    }

    @Override
    public void pisz(String s,  boolean notifyConnected) throws RemoteException {
        if(connectedClient == null) return;

        if(notifyConnected)
        {
            String toSend = "=> " + clientName + ":\n";
            toSend += s + "\n";
            viewModel.OnNewMessageReceived(toSend);
            if(notifyConnected)connectedClient.pisz(toSend, false);
        }
        else
        {
            viewModel.OnNewMessageReceived(s);
        }

        
    }

    /**
     * Will connect client to server
     * @param serverName
     * @param port
     */
    public void ConnectToServer(String serverName, int port)
    {
        this.serverName = serverName;
        try {
            Registry registry = LocateRegistry.getRegistry(serverName, port );
            server = (SInterface)registry.lookup(serverName);
            System.out.println("App has been successfully connected to server");
            } catch (Exception e) {
                e.printStackTrace();
            } 
    }

    /**
     * Will sing in this client by given name
     * @param name
     * @return 1 if success, -1 otherwise
     */
    public int SignInClient(String name)
    {
        clientName = name;
        try {
            int res = server.zapisz(this, name);
            System.out.println("Client " + name + " sing in");
            UpdateAllClientsData(server.listuj());
            return res;

        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Will sign out client from server
     * @return 1 is success, -1 otherwise
     */
    public int SignOutClient()
    {
        if(clientName == null) return -1;

        try {
            int res = server.wypisz(clientName);
            System.out.println("Client " + clientName + " sing out");

            UpdateAllClientsData(server.listuj());
            clientName = null;

            return res;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }

    }

    /**
     * Will connect client to this client
     * @param clientName client name to connect to
     */
    public void ConnectToClient(String clientName)
    {
        try {
            connectedClient = server.pobierz(clientName);
            System.out.println("Connected to " + clientName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Will disconnect connected client
     */
    public void DisconnectFromClient()
    {
        System.out.println("Disconnected from " + clientName);
        connectedClient = null;
    }

    @Override
    public void UpdateAllClientsData(String[] allClients) throws RemoteException {
        if(allClients != null)
        {
            for (String string : allClients) {
                if(string.contentEquals(serverName)) continue;

                var cl = server.pobierz(string);
                cl.UpdateAllClientsData(null);
            }
        }

        var filtered = Arrays.stream(server.listuj()).filter(s -> !s.contentEquals(serverName) && !s.contentEquals(clientName)).toArray(len -> new String[len]);

        if(clientName != null)viewModel.OnDataRefresh(filtered);
        else
            viewModel.OnDataRefresh(new String[0]);
        
    }
}
