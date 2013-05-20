package players;

import GUI.MainFrame;
import main.Const;
import main.Point;
import main.Value;

public class HumanPlayer extends Player {

    private Point p;

    public HumanPlayer(String name, Value mark) {
        super(name, mark);
        playerType=Const.HUMAN;
    }

    @Override
    public Point play() {
            MainFrame.getInstance().getGameField().lastPressed=null;
            while(MainFrame.getInstance().getGameField().lastPressed==null){
                try {                   
                    Thread.sleep(10);                    
                } catch (InterruptedException ex) {
                }
            }
            return MainFrame.getInstance().getGameField().lastPressed;
    }
}
