package devjiel.org.tanontime.model.converter;

import devjiel.org.tanontime.model.Arret;
import devjiel.org.tanontime.model.Ligne;
import devjiel.org.tanontime.model.TYPE_LIGNE;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by devjiel on 16/10/2016.
 */
public class JSon2Ligne implements JSon2Object<Ligne> {

    public static final String TAG_NUM_LIGNE = "numLigne";
    public static final String TAG_TYPE_LIGNE = "typeLigne";

    @Override
    public Ligne toObject(JSONObject jsonObject) throws JSONException{
        return new Ligne(jsonObject.getString(TAG_NUM_LIGNE), TYPE_LIGNE.valueOf(jsonObject.getInt(TAG_TYPE_LIGNE)));
    }

}
