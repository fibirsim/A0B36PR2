package GUI;

import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class WaitConnectionDialog extends JDialog implements Runnable {

    private JLabel lab = new JLabel();
    private int time;
    private JDialog owner;

    public WaitConnectionDialog(int time, JDialog owner) {
        this.time = time;
        this.owner = owner;
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setSize(80, 80);
        this.setTitle("Waiting...");
        this.setLocationRelativeTo(MainFrame.getInstance());
        this.setLayout(new FlowLayout());


        lab.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        this.add(lab);
    }

    @Override
    public final void setVisible(boolean visible) {
        owner.setFocusableWindowState(!visible);
        owner.setEnabled(!visible);
        super.setVisible(visible);
    }

    @Override
    public void dispose() {
        this.setVisible(false);
        super.dispose();
    }

    @Override
    public void run() {
        this.setVisible(true);
        while (time > 0) {
            lab.setText((time / 1000.0) + "");
            time -= 100;
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        this.dispose();
    }
}
