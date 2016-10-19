package devjiel.org.tanontime.model.tempsattente;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by devjiel on 16/10/2016.
 */
public enum TYPE_LIGNE {
    TRAMWAY(1),
    BUSWAY(2),
    BUS(3),
    NAVIBUS(4),
    UNKNOW(-1);

    private int numType = -1;

    private static Map<Integer, TYPE_LIGNE> map = new HashMap<>();

    static {
        for (TYPE_LIGNE typeLigne : TYPE_LIGNE.values()) {
            map.put(typeLigne.numType, typeLigne);
        }
    }

    TYPE_LIGNE(int numType) {
        this.numType =  numType;
    }

    public int getNumType() {
        return numType;
    }

    public static TYPE_LIGNE valueOf(int numType) {
        return map.get(numType);
    }
}
