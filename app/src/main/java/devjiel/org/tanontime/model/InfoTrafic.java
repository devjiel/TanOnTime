package devjiel.org.tanontime.model;

import org.json.JSONObject;

/**
 * Created by devjiel on 16/10/2016.
 */
public class InfoTrafic {

    private int sens;
    private String terminus;
    private boolean infoTrafic;
    private String temps;
    private Ligne ligne;
    private Arret arret;


    public InfoTrafic(int sens, String terminus, boolean infoTrafic, String temps, Ligne ligne, Arret arret) {

        this.sens = sens;
        this.terminus = terminus;
        this.infoTrafic = infoTrafic;
        this.temps = temps;
        this.ligne = ligne;
        this.arret = arret;
    }

    public int getSens() {
        return sens;
    }

    public void setSens(int sens) {
        this.sens = sens;
    }

    public String getTerminus() {
        return terminus;
    }

    public void setTerminus(String terminus) {
        this.terminus = terminus;
    }

    public boolean isInfoTrafic() {
        return infoTrafic;
    }

    public void setInfoTrafic(boolean infoTrafic) {
        this.infoTrafic = infoTrafic;
    }

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public Ligne getLigne() {
        return ligne;
    }

    public void setLigne(Ligne ligne) {
        this.ligne = ligne;
    }

    public Arret getArret() {
        return arret;
    }

    public void setArret(Arret arret) {
        this.arret = arret;
    }
}
