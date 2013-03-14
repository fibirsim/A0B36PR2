package graphics;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComponent;

public class Menu extends JComponent {

    public Menu() {
        setLayout(new FlowLayout());
        JButton b1 = new JButton("Nova hra");
        JButton b2 = new JButton("Ulozit");
        JButton b3 = new JButton("Nacist");
        JButton b4 = new JButton("Konec");
        
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        this.setVisible(true);
    }
}
