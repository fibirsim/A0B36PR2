package GUI;

import GUI.util.ValueComponent;
import Games.Game;
import Games.LANGame;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import main.Const;
import main.CreateLanGame;
import main.Value;
import players.HumanPlayer;
import players.LanPlayer;

public class NewLanGameDialog extends JDialog implements ActionListener {

    private JTextField name = new JTextField(9);
    private String[] LanTypes = {"Create Game", "Join Game"};
    private JComboBox LanConn = new JComboBox(LanTypes);
    private JTextField ipAdress = new JTextField(9);
    private JLabel ipadr = new JLabel("Host:");
    private JLabel myAdress;
    private ValueComponent vc1 = new ValueComponent(Value.O);
    private ValueComponent vc2 = new ValueComponent(Value.X);


    public NewLanGameDialog(Frame owner) {
        super(owner,true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(400, 400);
        this.setTitle("New Lan Game");
        this.setLocationRelativeTo(owner);
        this.setLayout(new GridBagLayout());
        init();
        this.setVisible(true);
    }

    private void init() {
        try {
            myAdress = new JLabel(InetAddress.getLocalHost().getHostName() + ": " + InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
        }

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.NONE;
        c.ipady = 10;
        c.insets = new Insets(8, 0, 8, 0);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        this.add(vc1, c);
        this.add(vc2, c);
        c.gridwidth = 1;

        c.fill = GridBagConstraints.BOTH;

        c.gridx = 1;
        c.gridy = 2;
        this.add(new JLabel("Name:"), c);

        c.gridx = 2;
        c.gridy = 2;
        this.add(name, c);

        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 2;
        this.add(LanConn, c);
        LanConn.addActionListener(this);
        c.gridwidth = 1;

        c.gridx = 1;
        c.gridy = 5;
        this.add(ipadr, c);

        c.gridx = 2;
        c.gridy = 5;
        this.add(ipAdress, c);

        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 2;
        this.add(myAdress, c);
        c.gridwidth = 1;

        JButton butt = new JButton("Play");
        c.insets = new Insets(30, 0, 0, 0);
        c.ipady = 15;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 6;
        this.add(butt, c);
        butt.addActionListener(this);

        ipAdress.setVisible(!(LanConn.getSelectedIndex() == 0));
        ipadr.setVisible(!(LanConn.getSelectedIndex() == 0));
        vc1.setVisible(LanConn.getSelectedIndex() == 0);
        vc2.setVisible(!(LanConn.getSelectedIndex() == 0));
        myAdress.setVisible((LanConn.getSelectedIndex() == 0));
        myAdress.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JComboBox) {
            if (((JComboBox) e.getSource()).getSelectedIndex() == 0) {
                ipadr.setVisible(false);
                ipAdress.setVisible(false);
                myAdress.setVisible(true);
                vc1.setVisible(true);
                vc2.setVisible(false);
            } else {
                ipadr.setVisible(true);
                ipAdress.setVisible(true);
                myAdress.setVisible(false);
                vc1.setVisible(false);
                vc2.setVisible(true);
            }
        }

        if (e.getSource() instanceof JButton) {
            switch (LanConn.getSelectedIndex()) {
                case 0: {
                    if (name.getText().trim().length() > 0) {
                        createGame(name.getText().trim());
                    }
                    break;
                }
                case 1: {
                    if (name.getText().trim().length() > 0 && ipAdress.getText().length() > 0) {
                        if (joinGame(name.getText().trim(), ipAdress.getText())) {
                            this.dispose();
                        }
                    }
                    break;
                }
            }
        }
    }

    private void createGame(String name) {
        CreateLanGame clg = new CreateLanGame(name, this);
        Thread th = new Thread(clg);
        th.start();
    }

    private boolean joinGame(String name, String IP) {
        Socket clientSocket;
        try {
            clientSocket = new Socket(IP, Const.PORT);            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "No game found.", "Connection Failed", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (MainFrame.getInstance().getGame() != null) {
                MainFrame.getInstance().getGame().destroy();
                MainFrame.getInstance().getGameField().init();
            } else {
                MainFrame.getInstance().getGameField().setEnabled(true);
            }

            Game game = new LANGame(new LanPlayer(null, Value.O,clientSocket),new HumanPlayer(name, Value.X), clientSocket);
            MainFrame.getInstance().setGame(game);
            game.start();
        return true;
    }
}
