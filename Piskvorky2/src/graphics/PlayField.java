package graphics;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class PlayField extends JComponent {

    JPanel field;

    public PlayField() {
        this(40);
    }

    public PlayField(int size) {
        this.setLayout(new BorderLayout());
        field = new JPanel();
        this.add(field, BorderLayout.CENTER);
        
        JComponent comp = new JComponent() {};
            
        comp.setLayout(new FlowLayout());
        JButton b = new JButton("Ulozit hru");
        comp.add(b);
        b= new JButton("Ukoncit");
        comp.add(b);
        
        
    }
    
}
