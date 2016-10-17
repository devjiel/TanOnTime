package devjiel.org.tanontime.model;

/**
 * Created by devjiel on 16/10/2016.
 */
public class Ligne {

    private String numLigne;
    private TYPE_LIGNE type;

    public Ligne(String numLigne, TYPE_LIGNE type) {
        this.numLigne = numLigne;
        this.type = type;
    }

    public String getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(String numLigne) {
        this.numLigne = numLigne;
    }

    public TYPE_LIGNE getType() {
        return type;
    }

    public void setType(TYPE_LIGNE type) {
        this.type = type;
    }


}


