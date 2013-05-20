package Games;

import GUI.MainFrame;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;
import main.Const;
import main.Point;
import main.Value;
import players.Player;

public class LANGame extends Game {

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    public LANGame(Player p1, Player p2, Socket socket) {
        super(p1, p2);
        this.socket = socket;
        try {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void run() {       
        if (players[0].getPlayerType() == Const.LAN) {
            try {
                String name = in.readUTF();
                this.setPlayer1Name(name);
                out.writeUTF(players[1].getName());
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else {
            try {
                out.writeUTF(players[0].getName());
                String name = in.readUTF();
                this.setPlayer2Name(name);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

        Value win;

        while ((win = MainFrame.getInstance().getGameField().getWinner()) == null) {
            onMove = MainFrame.getInstance().getGameField().getCheckCount() % 2;
            Point played = players[onMove].play();
            if(players[onMove].getPlayerType()==Const.HUMAN){
                try {
                    out.writeInt(played.getY());
                    out.writeInt(played.getX());
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
            MainFrame.getInstance().getGameField().markPoint(played, players[onMove].getMark());
        }


        if (win == Value.O) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "The Winner is " + players[0]);
        } else {
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "The Winner is " + players[1]);
        }

        try {
            socket.close();
            in.close();
            out.close();
            players[0].close();
            players[1].close();
        } catch (IOException ex) {
        }
    }

    @Override
    public void destroy() {
        if (this.isAlive()) {
            try {
                socket.close();
                in.close();
                out.close();
                players[0].close();
                players[1].close();
            } catch (IOException ex) {
            }
            this.stop();
        }
    }
}
