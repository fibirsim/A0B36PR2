package piskvorky;

import graphics.MFrame;
import graphics.Menu;
import java.util.Scanner;

public class Piskvorky2 {

    static Scanner sc = new Scanner(System.in); //staticke, aby k nim mohla pristupovat metoda menu i main
    
    static Hra play = null;

    public static void menu() { //zobrazuje menu po zadani souradnic 0 0
        MFrame hlokno = new MFrame();
        cyklus:
        while (true) {
            System.out.println("Hlavni menu");
            System.out.println("A - Nova hra\nB - Uloz hru\nC - Nacti hru\nD - Zpet \nE - Konec hry");
            switch (sc.next()) {
                case "A": {
                    int velikost = 0;
                    while (true) {
                        try {
                            System.out.print("Zadej velikost ctvercoveho pole: ");
                            velikost = sc.nextInt();
                            break;
                        } catch (Exception ex) {
                            sc.next();
                        }
                    }
                    play = new Hra(velikost);
                    break cyklus;
                }
                case "B": {
                    if (play == null) {
                        System.out.println("Neni hra, kterou by bylo mozne ulozit.");
                    } else {
                        System.out.println("Zadejte adresu souboru pro ulozeni hry.");
                        play.ulozHru(sc.next());
                    }
                    break cyklus;
                }
                case "C": {
                    System.out.print("Zadej soubor ve kterem je ulozena hra: ");
                    play = new Hra(sc.next());
                    break cyklus;
                }
                case "D":
                    if (play == null) {
                        System.exit(0);
                    } else {
                        break;
                    }
                case "E": {
                    System.exit(0);
                }
                default: {
                    System.out.println("Chybna volba!");
                }
            }
        }
    }

    public static void main(String[] args) {
        do {
            menu();
        } while (play == null);
        
        System.out.println("Pro vstup do menu behem hry zadejte souradnice [0, 0]");
        
        boolean help;
        Hodnota ret;
        do {
            ret = play.hraj();
            if (ret == Hodnota.Menu) {
                help = true;
                menu();
            } else {
                help = (ret == null);
            }


        } while (help);

        if (play.hraj() == Hodnota._) {
            System.out.println("Remiza!"); //pole je zaplnene a nikdo nevyhral
        } else {
            System.out.println("Vyhrava : " + play.hraj() + " !");
        }
        play.kresliPapir();


    }
}
