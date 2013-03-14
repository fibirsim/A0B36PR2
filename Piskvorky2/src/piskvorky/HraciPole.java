package piskvorky;

import java.io.Serializable;

public class HraciPole implements Serializable {

    private Policko[][] pole;
    private int pocPlnych = 0;
    private Hodnota naTahu = Hodnota.O;

    public HraciPole(int velikost) {

        this.pole = new Policko[velikost][velikost];

        for (int i = 0; i < velikost; i++) {
            for (int j = 0; j < velikost; j++) {
                pole[i][j] = new Policko();
            }
        }
    }

    public int getVelikost() {
        return pole.length;
    }

    public Hodnota getNaTahu() {
        return naTahu;
    }

    public void switchPlayer() { //prohazuje hrace ktery je na tahu
        if (naTahu == Hodnota.O) {
            naTahu = Hodnota.X;
        } else {
            naTahu = Hodnota.O;
        }
    }

    public boolean zapis(Hodnota hodnota, int x, int y) { //zapisuje hodnoty X nebo O a kontroluje spravne meze i jestli neni policko zaplnene
        x--;
        y--;
        if (x < 0 || x >= getVelikost() || y < 0 || y >= getVelikost()) {
            System.out.println("Souradnice jsou mimo meze papiru!");
            System.out.println("");
            return false;
        }
        if (pole[y][x].setHodnota(hodnota)) {
            pocPlnych++;
            return true;
        } else {
            System.out.println("Policko je zaplnene!");
            System.out.println("");
            return false;
        }
    }

    @Override
    public String toString() {
        String s = " ";
        for (int i = 1; i <= getVelikost(); i++) {
            s+=(" "+i);
        }
        s+=" X\n";
        for (int i = 0; i < getVelikost(); i++) {
            s+=((i+1)+" ");
            for (int j = 0; j < getVelikost(); j++) {
                s += pole[i][j] + " ";
            }
            s += "\n";
        }
        s+="Y\n";
        return s;
    }

    public Hodnota jePet() { //kontroluje, zda nekdo nevyhral
        int pocet;
        Hodnota h;

        for (int i = 0; i < getVelikost(); i++) { //horizontalni
            pocet = 1;
            h = (pole[i][0]).getHodnota();
            for (int j = 1; j < getVelikost(); j++) {

                if ((pole[i][j]).getHodnota() != Hodnota._ && (pole[i][j]).getHodnota() == h) {
                    pocet++;
                    if (pocet == 5) {
                        return h;
                    }
                } else {
                    pocet = 1;
                    h = (pole[i][j]).getHodnota();
                }
            }
        }

        for (int j = 0; j < getVelikost(); j++) { //vertikalni
            pocet = 1;
            h = (pole[j][0]).getHodnota();
            for (int i = 1; i < getVelikost(); i++) {

                if ((pole[i][j]).getHodnota() != Hodnota._ && (pole[i][j]).getHodnota() == h) {
                    pocet++;
                    if (pocet == 5) {
                        return h;
                    }
                } else {
                    pocet = 1;
                    h = (pole[i][j]).getHodnota();
                }
            }
        }

        for (int i = 0; i < getVelikost() - 4; i++) { // horni diagonala zleva doprava
            int k = 0;
            pocet = 0;
            h = (pole[k][i]).getHodnota();
            for (int j = i; j < getVelikost() && k < getVelikost(); j++, k++) {
                if ((pole[k][j]).getHodnota() != Hodnota._ && (pole[k][j]).getHodnota() == h) {
                    pocet++;
                    if (pocet == 5) {
                        return h;
                    }
                } else {
                    pocet = 1;
                    h = (pole[k][j]).getHodnota();
                }
            }
        }

        for (int i = 4; i < getVelikost(); i++) { // dolni diagonala zprava doleva
            int k = getVelikost() - 1;
            pocet = 0;
            h = (pole[k][i]).getHodnota();
            for (int j = i; j >= 0 && k >= 0; j--, k--) {
                if ((pole[k][j]).getHodnota() != Hodnota._ && (pole[k][j]).getHodnota() == h) {
                    pocet++;
                    if (pocet == 5) {
                        return h;
                    }
                } else {
                    pocet = 1;
                    h = (pole[k][j]).getHodnota();
                }
            }
        }

        for (int i = 4; i < getVelikost(); i++) { // horni diagonala zprava doleva
            int k = 0;
            pocet = 0;
            h = (pole[k][i]).getHodnota();
            for (int j = i; j >= 0 && k < getVelikost(); j--, k++) {
                if ((pole[k][j]).getHodnota() != Hodnota._ && (pole[k][j]).getHodnota() == h) {
                    pocet++;
                    if (pocet == 5) {
                        return h;
                    }
                } else {
                    pocet = 1;
                    h = (pole[k][j]).getHodnota();
                }
            }
        }

        for (int i = 0; i < getVelikost() - 4; i++) { // dolni diagonala zleva doprava
            int k = getVelikost() - 1;
            pocet = 0;
            h = (pole[k][i]).getHodnota();
            for (int j = i; j < getVelikost() && k >= 0; j++, k--) {
                if ((pole[k][j]).getHodnota() != Hodnota._ && (pole[k][j]).getHodnota() == h) {
                    pocet++;
                    if (pocet == 5) {
                        return h;
                    }
                } else {
                    pocet = 1;
                    h = (pole[k][j]).getHodnota();
                }
            }
        }

        if (pocPlnych == getVelikost() * getVelikost()) { //zda neni papir plny
            return Hodnota._;
        }

        return null;
    }
}
