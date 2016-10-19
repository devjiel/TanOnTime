package devjiel.org.tanontime.model.tempsattente.converter;

import devjiel.org.tanontime.model.converter.JSon2Object;
import devjiel.org.tanontime.model.tempsattente.Arret;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by devjiel on 16/10/2016.
 */
public class JSon2Arret implements JSon2Object<Arret> {

    public static final String TAG_CODE_ARRET = "codeArret";

    @Override
    public Arret toObject(JSONObject jsonObject) throws JSONException{
        return new Arret(jsonObject.getString(TAG_CODE_ARRET));
    }

}
