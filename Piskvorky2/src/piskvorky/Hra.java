package piskvorky;

import java.io.*;
import java.util.Scanner;

public class Hra {

    private HraciPole papir;

    public Hra(int velikost) {
        velikost = Math.abs(velikost);
        if (velikost < 5 || velikost > 100) {
            velikost = 30;
        }
        papir = new HraciPole(velikost);
    }

    public Hra(String adresa) {
        File soubor = new File(adresa);
        try {
            FileInputStream fis = new FileInputStream(soubor);
            try (ObjectInputStream vstup = new ObjectInputStream(fis)) {
                papir = (HraciPole) vstup.readObject();
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Soubor nenalezen!");
            System.exit(0);
        }
    }

    public void ulozHru(String adresa) { //uklada hru na zvolenou adresu
        File soubor = new File(adresa);
        try {
            try (ObjectOutputStream vystup = new ObjectOutputStream(new FileOutputStream(soubor, false))) {
                vystup.writeObject(papir);
            }
        } catch (IOException ex) {
            System.err.println("Ukladani se nepodarilo!");
        }
    }

    public void kresliPapir() {
        System.out.println("");
        System.out.println(papir);
    }

    public Hodnota hraj() {
        Scanner sc = new Scanner(System.in);
        int x = 0;
        int y = 0;

        if (papir.jePet() != null) { //aby nebylo mozne hrat na plnem papire
            return papir.jePet();
        }

        do {
            System.out.println(papir);

            System.out.println("Na tahu je: " + papir.getNaTahu());

            while (true) {
                try {
                    System.out.print("Zadej souradnici x: ");
                    x = sc.nextInt();
                    System.out.print("Zadej souradnici y: ");
                    y = sc.nextInt();
                    System.out.println("");
                    if (x == 0 && y == 0) {
                        return Hodnota.Menu;
                    }
                    break;
                } catch (Exception ex) {
                    sc.next();
                }
            }
        } while (!papir.zapis(papir.getNaTahu(), x, y));
        papir.switchPlayer();
        return papir.jePet();
    }
}
