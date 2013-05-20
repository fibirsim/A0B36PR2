package Games;

import GUI.MainFrame;
import javax.swing.JOptionPane;
import main.Point;
import main.Value;
import players.Player;

public class LocalGame extends Game {

    public LocalGame(Player p1, Player p2) {
        super(p1, p2);
    }

    @Override
    public void run() {
        Value win;
        while ((win = MainFrame.getInstance().getGameField().getWinner()) == null) {
            onMove = MainFrame.getInstance().getGameField().getCheckCount() % 2;
            Point played = players[onMove].play();
            MainFrame.getInstance().getGameField().markPoint(played, players[onMove].getMark());
        }
        if (win == Value.O) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "The Winner is " + players[0]);
        } else {
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "The Winner is " + players[1]);
        }

    }

    @Override
    public void destroy() {
        if (this.isAlive()) {
            this.stop();
        }
    }
}
