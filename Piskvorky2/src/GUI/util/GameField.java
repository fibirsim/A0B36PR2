package GUI.util;

import GUI.MainFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import main.Const;
import main.Point;
import main.Value;

public final class GameField extends JPanel implements MouseListener, ActionListener {

    public final Dimension WINDOW_SIZE;
    public final int GAME_SIZE;
    public final Dimension FIELD_SIZE;
    public volatile Point lastPressed;
    private Value[][] field;
    private int checkCount;
    private Timer tim;
    private final int BORDER_SIZE;

    public GameField(int size) {
        this.addMouseListener(this);
        BORDER_SIZE = 7;
        GAME_SIZE = size;
        WINDOW_SIZE = new Dimension(570 + (BORDER_SIZE * 2), 570 + (BORDER_SIZE * 2));
        FIELD_SIZE = new Dimension((WINDOW_SIZE.width - (2 * BORDER_SIZE)) / GAME_SIZE, (WINDOW_SIZE.height - (2 * BORDER_SIZE)) / GAME_SIZE);
        field = new Value[GAME_SIZE][GAME_SIZE];
        this.setPreferredSize(WINDOW_SIZE);
        this.setBackground(Color.WHITE);
        this.setBorder(new LineBorder(Color.BLACK, BORDER_SIZE));
        init();
        tim = new Timer(5, this);
        tim.start();
    }

    public void init() {
        for (int i = 0; i < GAME_SIZE; i++) {
            for (int j = 0; j < GAME_SIZE; j++) {
                field[i][j] = Value._;
            }
        }
        checkCount = 0;
    }

    public Value[][] getField() {
        return field;
    }

    public int getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(int checkCount) {
        this.checkCount = checkCount;
    }

    public void markPoint(Point p, Value mark) {
        this.checkCount++;
        field[p.getY()][p.getX()] = mark;
    }

    private void drawCross(int x, int y, Graphics g) {
        if (Const.getCross() != null) {
            g.drawImage(Const.getCross(), x * FIELD_SIZE.width + BORDER_SIZE, y * FIELD_SIZE.height + BORDER_SIZE, FIELD_SIZE.width, FIELD_SIZE.height, null);
        } else {
            g.drawLine(x * FIELD_SIZE.width + BORDER_SIZE, y * FIELD_SIZE.height + BORDER_SIZE, (x + 1) * FIELD_SIZE.width + BORDER_SIZE, (y + 1) * FIELD_SIZE.height + BORDER_SIZE);
            g.drawLine((x + 1) * FIELD_SIZE.width + BORDER_SIZE, y * FIELD_SIZE.height + BORDER_SIZE, x * FIELD_SIZE.width + BORDER_SIZE, (y + 1) * FIELD_SIZE.height + BORDER_SIZE);
        }
    }

    private void drawCircle(int x, int y, Graphics g) {
        if (Const.getCircle() != null) {
            g.drawImage(Const.getCircle(), x * FIELD_SIZE.width + BORDER_SIZE, y * FIELD_SIZE.height + BORDER_SIZE, FIELD_SIZE.width, FIELD_SIZE.height, null);
        } else {
            g.drawOval(x * FIELD_SIZE.width + BORDER_SIZE, y * FIELD_SIZE.height + BORDER_SIZE, FIELD_SIZE.width, FIELD_SIZE.height);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i <= GAME_SIZE; i++) {
            g.drawLine(i * FIELD_SIZE.width + BORDER_SIZE, BORDER_SIZE, i * FIELD_SIZE.width + BORDER_SIZE, FIELD_SIZE.height * GAME_SIZE + BORDER_SIZE);
            g.drawLine(BORDER_SIZE, i * FIELD_SIZE.height + BORDER_SIZE, FIELD_SIZE.width * GAME_SIZE + BORDER_SIZE, i * FIELD_SIZE.height + BORDER_SIZE);
        }
        for (int i = 0; i < GAME_SIZE; i++) {
            for (int j = 0; j < GAME_SIZE; j++) {
                if (field[i][j] == Value.O) {
                    drawCircle(j, i, g);
                }
                if (field[i][j] == Value.X) {
                    drawCross(j, i, g);
                }
            }
        }
    }

    public Value getWinner() { //kontroluje, zda nekdo nevyhral
        int count;
        final int FULL = 5;
        Value h;

        for (int i = 0; i < GAME_SIZE; i++) { //horizontalni
            count = 1;
            h = (field[i][0]);
            for (int j = 1; j < GAME_SIZE; j++) {

                if ((field[i][j]) != Value._ && (field[i][j]) == h) {
                    count++;
                    if (count == FULL) {
                        return h;
                    }
                } else {
                    count = 1;
                    h = (field[i][j]);
                }
            }
        }

        for (int j = 0; j < GAME_SIZE; j++) { //vertikalni
            count = 1;
            h = (field[j][0]);
            for (int i = 1; i < GAME_SIZE; i++) {

                if ((field[i][j]) != Value._ && (field[i][j]) == h) {
                    count++;
                    if (count == FULL) {
                        return h;
                    }
                } else {
                    count = 1;
                    h = (field[i][j]);
                }
            }
        }

        for (int i = 0; i < GAME_SIZE - (FULL - 1); i++) { // horni diagonala zleva doprava
            int k = 0;
            count = 0;
            h = (field[k][i]);
            for (int j = i; j < GAME_SIZE && k < GAME_SIZE; j++, k++) {
                if ((field[k][j]) != Value._ && (field[k][j]) == h) {
                    count++;
                    if (count == FULL) {
                        return h;
                    }
                } else {
                    count = 1;
                    h = (field[k][j]);
                }
            }
        }

        for (int i = FULL - 1; i < GAME_SIZE; i++) { // dolni diagonala zprava doleva
            int k = GAME_SIZE - 1;
            count = 0;
            h = (field[k][i]);
            for (int j = i; j >= 0 && k >= 0; j--, k--) {
                if ((field[k][j]) != Value._ && (field[k][j]) == h) {
                    count++;
                    if (count == FULL) {
                        return h;
                    }
                } else {
                    count = 1;
                    h = (field[k][j]);
                }
            }
        }

        for (int i = FULL - 1; i < GAME_SIZE; i++) { // horni diagonala zprava doleva
            int k = 0;
            count = 0;
            h = (field[k][i]);
            for (int j = i; j >= 0 && k < GAME_SIZE; j--, k++) {
                if ((field[k][j]) != Value._ && (field[k][j]) == h) {
                    count++;
                    if (count == FULL) {
                        return h;
                    }
                } else {
                    count = 1;
                    h = (field[k][j]);
                }
            }
        }

        for (int i = 0; i < GAME_SIZE - (FULL - 1); i++) { // dolni diagonala zleva doprava
            int k = GAME_SIZE - 1;
            count = 0;
            h = (field[k][i]);
            for (int j = i; j < GAME_SIZE && k >= 0; j++, k--) {
                if ((field[k][j]) != Value._ && (field[k][j]) == h) {
                    count++;
                    if (count == FULL) {
                        return h;
                    }
                } else {
                    count = 1;
                    h = (field[k][j]);
                }
            }
        }
        if (checkCount == GAME_SIZE * GAME_SIZE) { //zda neni papir plny
            return Value._;
        }

        return null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = (e.getX() - BORDER_SIZE) / FIELD_SIZE.width;
        int y = (e.getY() - BORDER_SIZE) / FIELD_SIZE.height;

        if (x < GAME_SIZE && y < GAME_SIZE && field[y][x] == Value._) {
            lastPressed = new Point(x, y);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().repaint();
    }
}
