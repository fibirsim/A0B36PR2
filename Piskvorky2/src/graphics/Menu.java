package graphics;

import Listeners.MenuListener;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComponent;

public class Menu extends JComponent {
    
    MenuListener menuLis = new MenuListener();
    

    public Menu() {
        setLayout(new FlowLayout());
        JButton b1 = new JButton("Nova hra");
        JButton b2 = new JButton("Nacist");
        JButton b3 = new JButton("Konec");
        
        add(b1);
        add(b2);
        add(b3);
        
        b1.addActionListener(menuLis);
        b2.addActionListener(menuLis);
        b3.addActionListener(menuLis);

    }
}
