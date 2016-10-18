package devjiel.org.tanontime.model;

import java.util.List;

/**
 * Created by devjiel on 18/10/2016.
 */
public class CardModel {

    private String title;
    private List<InfoTrafic> infoTrafics;

    public CardModel(String title, List<InfoTrafic> infoTrafics) {
        this.title = title;
        this.infoTrafics = infoTrafics;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<InfoTrafic> getInfoTrafics() {
        return infoTrafics;
    }

    public void setInfoTrafics(List<InfoTrafic> infoTrafics) {
        this.infoTrafics = infoTrafics;
    }
}
