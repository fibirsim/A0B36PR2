package GUI.listeners;

import GUI.MainFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;


   public class MenuGFBackgroudColListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {            
            Color col = JColorChooser.showDialog(MainFrame.getInstance(),"Choose new background color.",MainFrame.getInstance().getGameField().getBackground());            
            MainFrame.getInstance().getGameField().setBackground(col);
    }


}
