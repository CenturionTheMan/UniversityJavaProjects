package Lab7_code.Server;

import java.rmi.RemoteException;

public class ServerProgram{

    private static int _port = 1001;
    private static String _hostName = "localhost";

    /**
     * Entry point for server app
     * @param args
     * args[0] - port number
     * args[1] - host name
     */
    public static void main(String[] args) {
        ServerModel serverModel;

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

       

        try {
            serverModel = new ServerModel();
            serverModel.RunServer(_port, _hostName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
