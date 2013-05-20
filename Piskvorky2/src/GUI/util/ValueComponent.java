package GUI.util;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import main.Const;
import main.Value;

public class ValueComponent extends JPanel {

    private Value val;
    private final int SIZE = 50;

    public ValueComponent(Value val) {
        this.val = val;
        this.setPreferredSize(new Dimension(SIZE, SIZE));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (val == Value.O) {
            if (Const.getCircle() != null) {
                g.drawImage(Const.getCircle(), 0, 0, SIZE, SIZE, null);
            } else {
                g.drawOval(0, 0, SIZE, SIZE);
            }
        }
        if (val == Value.X) {
            if (Const.getCross() != null) {
                g.drawImage(Const.getCross(), 0, 0, SIZE, SIZE, null);
            } else {
                g.drawLine(0, 0, SIZE, SIZE);
                g.drawLine(SIZE, 0, 0, SIZE);
            }
        }
    }
}
