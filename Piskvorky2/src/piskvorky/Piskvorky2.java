package piskvorky;

import graphics.MFrame;
import graphics.Menu;
import java.util.Scanner;

public class Piskvorky2 {

    static Scanner sc = new Scanner(System.in); //staticke, aby k nim mohla pristupovat metoda menu i main
    
    static Hra play = null;

    public static void main(String[] args) {
        MFrame.getInstance().setVisible(true);
        MFrame.getInstance().zobrazComp(new Menu());
    }
}
