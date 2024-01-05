package Lab7_code.Client.ViewModels;

import java.rmi.RemoteException;

import Lab7_code.Client.Models.ClientModel;
import Lab7_code.Client.Views.MainView;

public class MainViewModel {
    
    private MainView mainView;
    private ClientModel clientModel;

    //CTOR
    public MainViewModel(MainView mainView, ClientModel clientModel) {
        this.mainView = mainView;
        this.clientModel = clientModel;
    }

    /**
     * Event raised when new message should be write in chat area
     * @param text text to write
     */
    public void OnNewMessageReceived(String text)
    {
        mainView.AddNewTextToChatArea(text);
    }

    // /**
    //  * Event raised when new client is being added
    //  * @param clientNames
    //  */
    // public void OnNewClientAdded(String[] clientNames)
    // {
    //     mainView.RefreshConnectedClients(clientNames);
    // }

    /**
     * Event raised when connect button is being pressed
     * @param toConnectName name of client to connect to
     */
    public void OnConnectButtonPressed(String toConnectName)
    {
        clientModel.ConnectToClient(toConnectName);        
    }

    /**
     * Event raised when disconnect button is being pressed
     */
    public void OnDisconnectButtonPressed()
    {
        clientModel.DisconnectFromClient();
    }

    /**
     * Event raised when clients connect to server has been changed
     * @param clientNames clients connected to server
     */
    public void OnDataRefresh(String[] clientNames)
    {
        mainView.RefreshConnectedClients(clientNames);
    }

    /**
     * Event raised when sign in button is being pressed
     * @param name name to sign this client with
     * @return 1 is success, -1 otherwise
     */
    public int OnSignInButtonPressed(String name)
    {
        return clientModel.SignInClient(name);
    }

    /**
     * Event raised when sign out button is being pressed
     * @return 1 is success, -1 otherwise
     */
    public int OnSignOutButtonPressed()
    {
        return clientModel.SignOutClient();
    }

    /**
     * Event raised when send button is being pressed
     * @param inputFieldString text from input text field
     * @return
     */
    public void OnSendButtonPressed(String inputFieldString)
    {
        try {
            clientModel.pisz(inputFieldString, true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
