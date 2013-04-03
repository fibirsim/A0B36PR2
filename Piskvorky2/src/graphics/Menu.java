package graphics;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class Menu extends JComponent {

    public Menu() {
               
//        this.add(Menu.createPanelMenu());
//        this.setVisible(true);
        
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
    
//    public static JPanel createPanelMenu() {
//        
//        JPanel m = new JPanel();
//        
//        FlowLayout l = new FlowLayout(FlowLayout.CENTER, 50, 50);
//        
//        m.setLayout(l);
//        
//        JButton b1 = new JButton("Nova hra");
//        JButton b2 = new JButton("Ulozit");
//        JButton b3 = new JButton("Nacist");
//        JButton b4 = new JButton("Konec");
//        
//        m.add(b1);
//        m.add(b2);
//        m.add(b3);
//        m.add(b4);
//        
//        return m;
//        
//    }
    
}
