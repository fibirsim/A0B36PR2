package Games;

import GUI.MainFrame;
import players.Player;

public abstract class Game extends Thread {

    protected Player[] players;
    protected int onMove;

    public Player getPlayer1() {
        return players[0];
    }

    public Player getPlayer2() {
        return players[1];
    }

    public void setPlayer1Name(String name) {
        players[0].setName(name);
        MainFrame.getInstance().setL1Text(name + " - " + players[0].getMark());
    }

    public void setPlayer2Name(String name) {
        players[1].setName(name);
        MainFrame.getInstance().setL2Text(name + " - " + players[1].getMark());
    }

    public Game(Player p1, Player p2) {
        players = new Player[2];
        players[0] = p1;
        players[1] = p2;
        onMove = 0;
        MainFrame.getInstance().setL1Text(p1.getName() + " - " + p1.getMark());
        MainFrame.getInstance().setL2Text(p2.getName() + " - " + p2.getMark());
        MainFrame.getInstance().setEnabled(true);
    }

    @Override
    public abstract void run();

    @Override
    public abstract void destroy();
}
