package graphics;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class MFrame extends JFrame {
    
    JComponent akt = null;

    public MFrame() {
        super("Piskvorky");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        this.add(new Menu());
        setVisible(true);
    }

    public void zobrazComp(JComponent komponenta) {
        if (akt != null) {
            this.remove(akt);
        }
        this.add(komponenta);
        akt = komponenta;
    }
}
