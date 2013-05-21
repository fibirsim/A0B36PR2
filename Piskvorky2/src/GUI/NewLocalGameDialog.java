package GUI;

import GUI.util.ValueComponent;
import Games.Game;
import Games.LocalGame;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import main.Value;
import players.HumanPlayer;
import players.AIPlayer;

public class NewLocalGameDialog extends JDialog implements ActionListener {

    private JTextField name1 = new JTextField(9);
    private JTextField name2 = new JTextField(9);
    private String[] players = {"Human Player", "AI Player"};
    private JComboBox pl1 = new JComboBox(players);
    private JComboBox pl2 = new JComboBox(players);

    public NewLocalGameDialog(Frame owner) {
        super(owner,true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600, 400);
        this.setTitle("New Local Game");
        this.setLocationRelativeTo(owner);
        this.setLayout(new GridBagLayout());
        init();
        this.setVisible(true);
    }

    private void init() {
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.NONE;
        c.ipadx = 5;
        c.ipady = 5;
        c.insets = new Insets(5, 20, 5, 20);

        c.gridx = 1;
        c.gridy = 1;
        this.add(new ValueComponent(Value.O), c);

        c.gridx = 1;
        c.gridy = 2;
        this.add(name1, c);

        c.gridx = 1;
        c.gridy = 3;
        this.add(pl1, c);

        c.gridx = 2;
        c.gridy = 1;
        this.add(new ValueComponent(Value.X), c);

        c.gridx = 2;
        c.gridy = 2;
        this.add(name2, c);

        c.gridx = 2;
        c.gridy = 3;
        this.add(pl2, c);


        JButton butt = new JButton("Play");
        c.insets = new Insets(30, 0, 0, 0);
        c.ipady = 15;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 4;
        this.add(butt, c);
        butt.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (name1.getText().trim().length() > 0 && name2.getText().trim().length() > 0) {
            if (MainFrame.getInstance().getGame() != null) {
                MainFrame.getInstance().getGame().destroy();
                MainFrame.getInstance().getGameField().init();
            } else {
                MainFrame.getInstance().getGameField().setEnabled(true);
            }
            Game game = null;

            if ((pl1.getSelectedItem().equals(players[0]) && pl2.getSelectedItem().equals(players[0]))) {
                game = new LocalGame(new HumanPlayer(name1.getText().trim(), Value.O), new HumanPlayer(name2.getText().trim(), Value.X));
            }
            if ((pl1.getSelectedItem().equals(players[0]) && pl2.getSelectedItem().equals(players[1]))) {
                game = new LocalGame(new HumanPlayer(name1.getText().trim(), Value.O), new AIPlayer(name2.getText().trim(), Value.X));
            }
            if ((pl1.getSelectedItem().equals(players[1]) && pl2.getSelectedItem().equals(players[0]))) {
                game = new LocalGame(new AIPlayer(name1.getText().trim(), Value.O), new HumanPlayer(name2.getText().trim(), Value.X));
            }
            if ((pl1.getSelectedItem().equals(players[1]) && pl2.getSelectedItem().equals(players[1]))) {
                game = new LocalGame(new AIPlayer(name1.getText().trim(), Value.O), new AIPlayer(name2.getText().trim(), Value.X));
            }          

            MainFrame.getInstance().setGame(game);
            game.start();
            this.dispose();
        }
    }
}
