package main;

import GUI.MainFrame;
import GUI.WaitConnectionDialog;
import Games.Game;
import Games.LANGame;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import players.HumanPlayer;
import players.LanPlayer;

public class CreateLanGame implements Runnable {

    private ServerSocket serverSocket;
    private final int WAIT = 4000;
    private Socket clientSocket;
    private Thread timer = null;
    private JDialog ownerDialog;
    private String name;
    private boolean connected = false;

    public CreateLanGame(String name, JDialog ownerDialog) {
        this.name = name;
        this.ownerDialog = ownerDialog;
    }

    @Override
    public void run() {
        WaitConnectionDialog wcd = new WaitConnectionDialog(WAIT, ownerDialog);
        try {
            serverSocket = new ServerSocket(Const.PORT);
            serverSocket.setSoTimeout(WAIT);

            timer = new Thread(wcd);
            timer.start();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "You can\'t crate the game!", "Connection Failed", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }

        if (serverSocket != null) {
            try {
                clientSocket = serverSocket.accept();
                wcd.dispose();
                ownerDialog.dispose();
                connected = true;
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(MainFrame.getInstance(), "Times Up!", "Connection Failed", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (connected) {
            if (MainFrame.getInstance().getGame() != null) {
                MainFrame.getInstance().getGame().destroy();
                MainFrame.getInstance().getGameField().init();
            } else {
                MainFrame.getInstance().getGameField().setEnabled(true);
            }

            Game game = new LANGame(new HumanPlayer(name, Value.O), new LanPlayer(null, Value.X, clientSocket), clientSocket);
            MainFrame.getInstance().setGame(game);
            game.start();
        }
    }
}
