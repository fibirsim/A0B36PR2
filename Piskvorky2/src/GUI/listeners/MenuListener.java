package GUI.listeners;

import GUI.MainFrame;
import GUI.NewLanGameDialog;
import GUI.NewLocalGameDialog;
import Games.Game;
import Games.LocalGame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import main.Const;
import main.Point;
import main.Value;
import players.HumanPlayer;
import players.AIPlayer;

public class MenuListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JMenuItem) {
            switch (e.getActionCommand()) {
                case Const.LOCAL_GAME: {
                    new NewLocalGameDialog(MainFrame.getInstance());
                    break;
                }
                case Const.LAN_GAME: {
                    new NewLanGameDialog(MainFrame.getInstance());
                    break;
                }
                case Const.SAVE_GAME: {
                    saveGame();
                    break;
                }
                case Const.LOAD_GAME: {
                    loadGame();
                    break;
                }
                case Const.EXIT: {
                    System.exit(0);
                    break;
                }

            }
        }
    }

    private void createSaveFolder() {
        File theDir = new File("saves");
        if (!theDir.exists()) {
            boolean result = theDir.mkdir();
            if (result) {
                System.out.println("DIR created");
            }
        }
    }

    private void saveGame() {
        if (MainFrame.getInstance().getGame() == null) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "There is nothing to save!", "Save failed", JOptionPane.ERROR_MESSAGE);
        } else {
            createSaveFolder();
            JFileChooser fileChooser = new JFileChooser("./saves");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Saved Games", "sav5"));

            if (fileChooser.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (!file.getName().endsWith(".sav5")) {
                    file = new File(file.getAbsoluteFile() + ".sav5");
                }

                try (DataOutputStream out = new DataOutputStream(new FileOutputStream(file))) {
                    out.writeUTF(MainFrame.getInstance().getGame().getPlayer1().getName());
                    out.writeInt(MainFrame.getInstance().getGame().getPlayer1().getPlayerType());
                    out.writeUTF(MainFrame.getInstance().getGame().getPlayer2().getName());
                    out.writeInt(MainFrame.getInstance().getGame().getPlayer2().getPlayerType());
                    out.writeInt(MainFrame.getInstance().getGameField().getCheckCount());
                    for (int i = 0; i < MainFrame.getInstance().getGameField().GAME_SIZE; i++) {
                        for (int j = 0; j < MainFrame.getInstance().getGameField().GAME_SIZE; j++) {
                            out.writeUTF(MainFrame.getInstance().getGameField().getField()[i][j].toString());
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
        }
    }

    private void loadGame() {
        createSaveFolder();
        JFileChooser fileChooser = new JFileChooser("./saves");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Saved Games", "sav5"));

        if (fileChooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (DataInputStream in = new DataInputStream(new FileInputStream(file))) {

                String p1Name = in.readUTF();
                int p1Type = in.readInt();
                String p2Name = in.readUTF();
                int p2Type = in.readInt();

                Game game = null;
                switch (p1Type - p2Type) {
                    case Const.HUMAN - Const.LAN: {
                        JOptionPane.showMessageDialog(MainFrame.getInstance(), "Tak tohle jeste neslape", "NE", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    case Const.LAN - Const.HUMAN: {
                       JOptionPane.showMessageDialog(MainFrame.getInstance(), "Tohle jeste nefunguje... sorac", "NE", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    case Const.HUMAN - Const.AI: {
                        game = new LocalGame(new HumanPlayer(p1Name, Value.O), new AIPlayer(p2Name, Value.X));
                        break;
                    }
                    case Const.AI - Const.HUMAN: {
                        game = new LocalGame(new AIPlayer(p1Name, Value.O), new HumanPlayer(p2Name, Value.X));
                        break;
                    }

                    // 0 - two same player (HUMAN-HUMAN, UI-UI)
                    case 0: {
                        if (p1Type == Const.HUMAN) {
                            game = new LocalGame(new HumanPlayer(p1Name, Value.O), new HumanPlayer(p2Name, Value.X));
                        } else {
                            game = new LocalGame(new AIPlayer(p1Name, Value.O), new AIPlayer(p2Name, Value.X));
                        }
                        break;
                    }
                    default: {
                        JOptionPane.showMessageDialog(MainFrame.getInstance(), "Invalid players type combination", "Invalid combination", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }

                if (game != null) {
                    MainFrame.getInstance().getGameField().setCheckCount(in.readInt());
                    for (int i = 0; i < MainFrame.getInstance().getGameField().GAME_SIZE; i++) {
                        for (int j = 0; j < MainFrame.getInstance().getGameField().GAME_SIZE; j++) {
                            MainFrame.getInstance().getGameField().markPoint(new Point(j, i), Value.parseValue(in.readUTF()));
                        }
                    }

                    if (MainFrame.getInstance().getGame() != null) {
                        MainFrame.getInstance().getGame().destroy();
                    } else {
                        MainFrame.getInstance().getGameField().setEnabled(true);
                    }
                    MainFrame.getInstance().setGame(game);
                    game.start();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
