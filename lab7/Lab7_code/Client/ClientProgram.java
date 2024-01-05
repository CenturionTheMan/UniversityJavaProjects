package Lab7_code.Client;

import java.rmi.RemoteException;

import Lab7_code.Client.Models.ClientModel;
import Lab7_code.Client.ViewModels.MainViewModel;
import Lab7_code.Client.Views.MainView;

public class ClientProgram {
    private static int _port = 1001;
    private static String _hostName = "localhost";

    /**
     * Entry point for client program
     * @param args
     * args[0] - server port
     * args[1] - server name
     */
    public static void main(String[] args) {
        if(args.length == 2)
        {
            try {
                _port = Integer.parseInt(args[0]);
            } catch (Exception e) {
                System.out.println("ARGS ERR - args[0] must be of type integer (port number)");
                return;
            }
        }
        else
        {
            System.out.println("There is no args given for running application. Will attempt to run with default settings:\nPort = " + _port + "\nHost name = " +_hostName);
        }

        ClientModel clientModel = null;
        try {
            clientModel = new ClientModel();
        } catch (RemoteException e) {
            e.printStackTrace();
            return;
        }
        var mainView = new MainView();
        var mainViewModel = new MainViewModel(mainView, clientModel);

        clientModel.ConnectToServer(_hostName,_port);

        mainView.SetMainViewModel(mainViewModel);
        clientModel.SetViewModel(mainViewModel);

        mainView.RunGui();
    }
}
