package devjiel.org.tanontime.model.arret.converter;

import devjiel.org.tanontime.model.converter.JSon2Object;
import devjiel.org.tanontime.model.arret.Ligne;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by devjiel on 16/10/2016.
 */
public class JSon2Ligne implements JSon2Object<Ligne> {

    public static final String TAG_NUM_LIGNE = "numLigne";

    @Override
    public Ligne toObject(JSONObject jsonObject) throws JSONException{
        return new Ligne(jsonObject.getString(TAG_NUM_LIGNE));
    }

}
