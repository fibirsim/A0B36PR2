package GUI;

import Games.Game;
import GUI.util.GameField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import main.Const;

public final class MainFrame extends JFrame {

    private static final MainFrame instance = new MainFrame();
    private GameField gamefield = new GameField(19);
    private Game game = null;
    private JLabel l1 = new JLabel("First Player");
    private JLabel l2 = new JLabel("Second Player");
    private JPanel pan = new JPanel(new BorderLayout());

    private MainFrame() {
        super("Piškvorky");
        this.setSize(810, 810);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        initLookAndFeel();
        loadIcon();

        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 300, 0));
        p1.add(l1);
        p1.add(l2);
        pan.add(p1, BorderLayout.NORTH);

        JPanel p2 = new JPanel(new FlowLayout());
        p2.add(gamefield, BorderLayout.CENTER);
        pan.add(p2, BorderLayout.CENTER);

        this.add(pan);
        this.setJMenuBar(new GameMenu());
    }

    private void loadIcon() {
        try (DataInputStream in = new DataInputStream(new FileInputStream("icons/actual.txt"))) {
            Const.setIconType(in.readUTF());
            Const.setCross(ImageIO.read(new File("icons/"+Const.getIconType()+"/cross.png")));
            Const.setCircle(ImageIO.read(new File("icons/"+Const.getIconType()+"/circle.png")));
        } catch (Exception ex) {
            Const.setIconType(null);
            Const.setCircle(null);
            Const.setCross(null);
        }
    }

    private void initLookAndFeel() {
        String lookAndFeel;
        try {
            lookAndFeel = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public static MainFrame getInstance() {
        return instance;
    }

    public GameField getGameField() {
        return gamefield;
    }

    public void setL1Text(String text) {
        l1.setText(text);
    }

    public void setL2Text(String text) {
        l2.setText(text);
    }
}
