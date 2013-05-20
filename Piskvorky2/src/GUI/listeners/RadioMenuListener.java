package GUI.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import main.Const;

public class RadioMenuListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Default")) {
            Const.setIconType(null);
            Const.setCircle(null);
            Const.setCross(null);
        } else {
            Const.setIconType(e.getActionCommand());
        }

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("icons/actual.txt"))) {
            out.writeUTF(e.getActionCommand());
            if (Const.getIconType() != null) {
                Const.setCross(ImageIO.read(new File("icons/" + Const.getIconType() + "/cross.png")));
                Const.setCircle(ImageIO.read(new File("icons/" + Const.getIconType() + "/circle.png")));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Invalid icon pack!");
            Const.setCircle(null);
            Const.setCross(null);
        }
    }
}
