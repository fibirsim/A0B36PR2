package graphics;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MFrame extends JFrame {
    
    private static final MFrame instance = new MFrame();
    private JComponent akt = new JLabel("Loading...");

    private MFrame() {
        super("Piskvorky");
        this.setLocation(100, 100);
        setSize(500, 500);
//        this.pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        this.add(akt);
        setVisible(true);
    }

    public static MFrame getInstance() {
        return instance;
    }
    

    public JComponent zobrazComp(JComponent komponenta) {
        JComponent help;
        if (akt != null) {
            this.remove(akt);
        }
        this.add(komponenta);
        help=akt;
        akt = komponenta;
        this.validate();
        this.repaint();
        return help;
    }
}
