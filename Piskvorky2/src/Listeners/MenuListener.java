package Listeners;

import graphics.MFrame;
import graphics.PlayField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class MenuListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Nova hra": {
                int velikost;

                String str = JOptionPane.showInputDialog(MFrame.getInstance(), "Zadejte velikost pole", "Velikost", JOptionPane.QUESTION_MESSAGE);
                try {
                    velikost = Integer.parseInt(str);
                } catch (Exception ex) {
                    velikost = 50;                    
                    JOptionPane.showMessageDialog(MFrame.getInstance(), "Posral si to, velikost bude nastavena na " + velikost, "Posral si to ERROR", JOptionPane.ERROR_MESSAGE);
                }
                MFrame.getInstance().zobrazComp(new PlayField(velikost));
            }
            break;

            case "Nacist": {
            }
            break;
            case "Konec": {
            }
            break;

        }

    }
}
