package graphics;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class PlayField extends JComponent {
    
    JPanel field;
    
    public PlayField() {
        
        field = new JPanel();
        
        FlowLayout l = new FlowLayout(FlowLayout.CENTER);
        
        field.setLayout(l);
        
        JButton b1 = new JButton("Menu");
        
        field.add(b1);
        
//        field.setVisible(true);
//        
//        this.add(field);
//        this.setVisible(true);
        
    }
   
//    public JPanel createPanelPlayField() {
//        
//        JPanel p = new JPanel();
//        
//        FlowLayout l = new FlowLayout(FlowLayout.CENTER);
//        
//        p.setLayout(l);
//        
//        p.add(new JButton("Menu"));
//        
//        return p;       
//    }
    
    
    
}
