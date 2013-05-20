package players;

import GUI.MainFrame;
import main.Const;
import main.Point;
import main.Value;

public class UIPlayer extends Player {

    public UIPlayer(String name, Value mark) {
        super(name, mark);
        playerType = Const.UI;
    }

    @Override
    public Point play() {
        final int NEED = 5;


        Value[][] field = MainFrame.getInstance().getGameField().getField();
        final int SIZE = field.length;
        int[][] marks = new int[SIZE][SIZE];
        int actual;

        int x;
        int y;



        //horizontal
        for (y = 0; y < SIZE; y++) { //od shora dolu
            actual = 0;
            for (x = 0; x < NEED; x++) { //prvnich 5
                actual += field[y][x].toInteger();
            }
            for (x = 0; x < NEED; x++) { //naplnit pole marks
                marks[y][x] += (int) (actual * actual * Math.signum(actual));
            }
            for (x = NEED; x < SIZE; x++) { //od 5 dal
                actual -= field[y][x - NEED].toInteger();
                actual += field[y][x].toInteger();
                for (int k = x - NEED + 1; k <= x; k++) { //naplnit pole
                    marks[y][k] += (int) (actual * actual * Math.signum(actual));
                }
            }
        }

        //vertical
        for (x = 0; x < SIZE; x++) { //po radach
            actual = 0;
            for (y = 0; y < NEED; y++) { //prvnich 5
                actual += field[y][x].toInteger();
            }
            for (y = 0; y < NEED; y++) { //naplnit pole
                marks[y][x] += actual * actual;
            }
            for (y = NEED; y < SIZE; y++) { //od 5 dal
                actual -= field[y - NEED][x].toInteger();
                actual += field[y][x].toInteger();
                for (int k = y - NEED + 1; k <= y; k++) {
                    marks[k][x] += actual * actual;
                }
            }
        }


        // diagonal \
        // up part

        for (x = 0; x < SIZE - (NEED - 1); x++) { //horni osa X
            actual = 0;
            y = 0;

            for (int k = 0; k < NEED; k++) { //prvnch 5
                actual += field[y + k][x + k].toInteger();
            }

            for (int k = 0; k < NEED; k++) { //prvnch 5
                marks[y + k][x + k] += (int) (actual * actual * Math.signum(actual));
            }

            for (int k = NEED; x + k < SIZE && y + k < SIZE; k++) {
                actual -= field[y + k - NEED][x + k - NEED].toInteger();
                actual += field[y + k][x + k].toInteger();

                for (int km = 0; km < NEED; km++) {
                    marks[y + k - km][x + k - km] += (int) (actual * actual * Math.signum(actual));
                }
            }
        }


        //down part
        for (x = NEED; x < SIZE - 2; x++) {
            actual = 0;
            y = SIZE - 1;

            for (int k = 0; k < NEED; k++) {
                actual += field[y - k][x - k].toInteger();
            }

            for (int k = 0; k < NEED; k++) {
                marks[y - k][x - k] += (int) (actual * actual * Math.signum(actual));
            }

            for (int k = NEED; x - k > 0 && y - k > 0; k++) {
                actual -= field[y - k + NEED][x - k + NEED].toInteger();
                actual += field[y - k][x - k].toInteger();
                for (int km = 0; km < NEED; km++) {
                    marks[y - k + km][x - k + km] += (int) (actual * actual * Math.signum(actual));
                }
            }
        }

        //diagonal /
        //up part
        for (x = NEED; x < SIZE - 1; x++) {
            y = 0;
            actual = 0;

            for (int k = 0; k < NEED; k++) {
                actual += field[y + k][x - k].toInteger();
            }
            for (int k = 0; k < NEED; k++) {
                marks[y + k][x - k] += (int) (actual * actual * Math.signum(actual));
            }
            for (int k = NEED; x - k > 0 && y + k < SIZE; k++) {

                actual -= field[y+k - NEED][x-k + NEED].toInteger();
                actual += field[y+k][x-k].toInteger();

                for (int km = 0; km < NEED; km++) {
                    marks[y+k-km][x-k+km] += (int) (actual * actual * Math.signum(actual));
                }
            }
        }

        //down part
        for (x = 1; x < SIZE - (NEED - 1); x++) {
            actual = 0;
            y = SIZE - 1;
            
            for (int k = 0; k < NEED; k++) {
                actual += field[y-k][x+k].toInteger();
            }
            for (int k = 0; k < NEED; k++) {
                marks[y-k][x+k] += (int) (actual * actual * Math.signum(actual));
            }

            for (int k = NEED; x+k < SIZE && y-k > 0; k++) {
                actual -= field[y-k + NEED][x+k - NEED].toInteger();
                actual += field[y-k][x+k].toInteger();
                
                for (int km = 0; km < NEED; km++) {
                    marks[y-k+km][x+k-km] += (int) (actual * actual * Math.signum(actual));
                }
            }
        }

        //best point to play
        Point ret = null;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (ret == null && field[i][j] == Value._) {
                    ret = new Point(j, i);
                }
                if (field[i][j] == Value._ && Math.abs(marks[i][j]) >= Math.abs(marks[ret.getY()][ret.getX()])) {
                    ret = new Point(j, i);
                }
            }
        }
        return ret;
    }
}
