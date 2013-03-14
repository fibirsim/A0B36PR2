package piskvorky;

import java.io.Serializable;

public class Policko implements Serializable {

    private Hodnota hodnota;

    public Policko() {

        this.hodnota = Hodnota._;
    }

    public boolean setHodnota(Hodnota hodnota) {
        if (this.hodnota == Hodnota._ && hodnota!=Hodnota.Menu) {
            this.hodnota = hodnota;
            return true;
        }     
        return false;
    }

    public Hodnota getHodnota() {
        return hodnota;
    }

    @Override
    public String toString() {
        return ""+hodnota;
    }
    
    
}
