package devjiel.org.tanontime.model.arret;

import java.util.List;

/**
 * Created by devjiel on 20/10/2016.
 */
public class Arret {

    private String codeLieu;
    private String libelle;
    private String distance;
    private List<Ligne> lignes;

    public Arret(String codeLieu, String libelle, String distance, List<Ligne> lignes) {
        this.codeLieu = codeLieu;
        this.libelle = libelle;
        this.distance = distance;
        this.lignes = lignes;
    }

    public String getCodeLieu() {
        return codeLieu;
    }

    public void setCodeLieu(String codeLieu) {
        this.codeLieu = codeLieu;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public List<Ligne> getLignes() {
        return lignes;
    }

    public void setLignes(List<Ligne> lignes) {
        this.lignes = lignes;
    }
}
