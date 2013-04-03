package graphics;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class MFrame extends JFrame {
    
    JComponent akt = null;

    public MFrame() {
        super("Piskvorky");
        this.setLocation(100, 100);
        setSize(500, 500);
//        this.pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        this.add(new PlayField());
        
        setVisible(true);
    }

    public void zobrazComp(JComponent komponenta) {
        if (akt != null) {
            this.remove(akt);
        }
        this.add(komponenta);
        akt = komponenta;
//        this.repaint();
    }
}
