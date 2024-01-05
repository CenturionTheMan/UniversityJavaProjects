package Lab7_code.Client.Views;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Lab7_code.Client.ViewModels.MainViewModel;

public class MainView extends JFrame {

    //  GUI

    private JPanel container;

    private JTextArea chatTextArea;
    private JTextField inputField;
    private JTextField userNameField;
    private JButton connectButton;
    private JButton disconnectButton;
    private JButton sendButton;
    private JButton singInButton;
    private JButton singOutButton;
    private JComboBox<String> usersComboBox;


    private int width, height;

    private MainViewModel viewModel;

    private String connectedToName = null;

    private boolean isSignedIn = false;

    /**
     * Will run gui
     */
    public void RunGui()
    {
        
        width = 650;
        height = 400;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
            
        this.setPreferredSize(new Dimension(width,height));
        this.setTitle("Zadanie 7 gui");

        container = new JPanel();
        this.setContentPane(container);
        Setup();

        this.pack();
        this.setVisible(true);
    }

    /**
     * Will set mainViewModel
     * @param mainViewModel
     */
    public void SetMainViewModel(MainViewModel mainViewModel)
    {
        this.viewModel = mainViewModel;
    }

    /**
     * Gui specified stuff -> some window setting, some components, some events connections
     */
    private void Setup()
    {
        //LEFT / RIGHT side
        JPanel left = new JPanel();
        JPanel right = new JPanel();
        left.setPreferredSize(new Dimension(Math.round(width * 0.60f),height));
        right.setPreferredSize(new Dimension(Math.round(width * 0.35f),height));
        container.add(left);
        container.add(right);


        //CHAT AREA
        chatTextArea = new JTextArea();
        chatTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatTextArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(Math.round(width * 0.60f),Math.round(height * 0.7f)));
        left.add(scrollPane);

        //INPUT FIELD
        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(Math.round(width * 0.39f),Math.round(height * 0.15f)));
        left.add(inputField);

        //SEND BUTTON
        sendButton = new JButton("Wyslij");
        sendButton.setPreferredSize(new Dimension(Math.round(width * 0.19f),Math.round(height * 0.15f)));
        sendButton.addActionListener(e -> {
            if(viewModel != null)
            {
                viewModel.OnSendButtonPressed(inputField.getText());
                inputField.setText("");
            }
        });
        sendButton.setEnabled(false);
        left.add(sendButton);

        //USER NAME FIELD
        JLabel label1 = new JLabel("Nazwa uzytkownika:");
        label1.setPreferredSize(new Dimension(Math.round(width * 0.35f),Math.round(height * 0.10f)));
        label1.setVerticalAlignment(JLabel.BOTTOM);
        right.add(label1);

        userNameField = new JTextField();
        userNameField.setPreferredSize(new Dimension(Math.round(width * 0.35f),Math.round(height * 0.15f)));
        right.add(userNameField);

        //SIGN IN BUTTON
        singInButton = new JButton("Zarejestruj");
        singInButton.setPreferredSize(new Dimension(Math.round(width * 0.16f),Math.round(height * 0.10f)));
        singInButton.addActionListener(e -> {
            if(viewModel != null)
            {
                isSignedIn = true;
                if(userNameField.getText().contentEquals("")) return;
                if(viewModel.OnSignInButtonPressed(userNameField.getText()) > 0)
                {
                    SignedInStatus();
                }
                else
                {
                    JOptionPane.showMessageDialog(this,"Name " + userNameField.getText() + " is already taken");
                    userNameField.setText("");
                    isSignedIn = false;
                }
            }
        });
        right.add(singInButton);

        //SING OUT BUTTON
        singOutButton = new JButton("Wyrejestruj");
        singOutButton.setPreferredSize(new Dimension(Math.round(width * 0.16f),Math.round(height * 0.10f)));
        singOutButton.addActionListener(e -> {
            if(viewModel != null)
            {
                if(viewModel.OnSignOutButtonPressed() > 0)
                {
                    SignedOutStatus();
                    viewModel.OnDisconnectButtonPressed();
                }
                else
                {
                }
                
            }
        });
        singOutButton.setEnabled(false);
        right.add(singOutButton);

        //CLIENTS COMBO BOX
        JLabel label2 = new JLabel("Lista osob:");
        label2.setPreferredSize(new Dimension(Math.round(width * 0.35f),Math.round(height * 0.10f)));
        label2.setVerticalAlignment(JLabel.BOTTOM);
        right.add(label2);

        usersComboBox = new JComboBox<>();
        usersComboBox.setPreferredSize(new Dimension(Math.round(width * 0.35f),Math.round(height * 0.10f)));
        right.add(usersComboBox);
  
        //CONNECT BUTTON
        connectButton = new JButton("Polacz");
        connectButton.setPreferredSize(new Dimension(Math.round(width * 0.16f),Math.round(height * 0.10f)));
        connectButton.addActionListener(e -> {
            if(viewModel != null && usersComboBox.getSelectedItem() != null)
            {
                ConnectedStatus();
                connectedToName = (String)usersComboBox.getSelectedItem();
                viewModel.OnConnectButtonPressed(connectedToName);
            }
        });
        connectButton.setEnabled(false);
        right.add(connectButton);

        //DISCONNECT BUTTON
        disconnectButton = new JButton("Rozlacz");
        disconnectButton.setPreferredSize(new Dimension(Math.round(width * 0.16f),Math.round(height * 0.10f)));
        disconnectButton.addActionListener(e -> {
            if(viewModel != null)
            {
                DisconnectedStatus();
                viewModel.OnDisconnectButtonPressed();
            }
        });
        disconnectButton.setEnabled(false);
        right.add(disconnectButton);
    }

    //Will set given buttons to nonedible state and others to editable
    public void SignedInStatus()
    {
        isSignedIn = true;

        userNameField.setEditable(false);
        singInButton.setEnabled(false);
        singOutButton.setEnabled(true);
        usersComboBox.setEnabled(true);

        connectButton.setEnabled(true);
        disconnectButton.setEnabled(true);

    }

    //Will set given buttons to nonedible state and others to editable
    public void SignedOutStatus()
    {
        isSignedIn = false;

        DisconnectedStatus();
        singInButton.setEnabled(true);
        singOutButton.setEnabled(false);
        userNameField.setEditable(true);
        usersComboBox.setEnabled(false);
        connectButton.setEnabled(false);
        disconnectButton.setEnabled(false);
        RefreshConnectedClients(new String[0]);

    }

    //Will set given buttons to nonedible state and others to editable
    public void ConnectedStatus()
    {
        connectButton.setEnabled(false);
        disconnectButton.setEnabled(true);
        usersComboBox.setEnabled(false);
        sendButton.setEnabled(true);
    }

    //Will set given buttons to nonedible state and others to editable
    public void DisconnectedStatus()
    {
        connectButton.setEnabled(true);
        disconnectButton.setEnabled(false);
        usersComboBox.setEnabled(true);
        sendButton.setEnabled(false);

        connectedToName = null;
    }

    //Will add new text to chat area
    public void AddNewTextToChatArea(String text)
    {
        String t = chatTextArea.getText();
        t += text;
        chatTextArea.setText(t);
        chatTextArea.repaint();
    }

    //Will refresh clients list
    public void RefreshConnectedClients(String[] clientNames)
    {        
        usersComboBox.removeAllItems();
        if(isSignedIn == false){
            usersComboBox.repaint();
            return;
        }

        boolean isConnectedNameStill = false;
        for (int i = 0; i < clientNames.length; i++) {
            if(connectedToName != null && clientNames[i].contentEquals(connectedToName)) isConnectedNameStill = true;

            usersComboBox.addItem(clientNames[i]);
        }

        if(connectedToName != null && isConnectedNameStill == false) 
        {
            DisconnectedStatus();
            usersComboBox.repaint();
            return;
        }
        else if (connectedToName != null)
        {
            usersComboBox.setSelectedItem(connectedToName);
        }
        usersComboBox.repaint();

    }

}
