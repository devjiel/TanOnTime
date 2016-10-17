package devjiel.org.tanontime.model.converter;

import devjiel.org.tanontime.model.Arret;
import devjiel.org.tanontime.model.InfoTrafic;
import devjiel.org.tanontime.model.Ligne;
import devjiel.org.tanontime.model.TYPE_LIGNE;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by devjiel on 16/10/2016.
 */
public class JSon2InfoTrafic implements JSon2Object<InfoTrafic> {

    public static final String TAG_SENS = "sens";
    public static final String TAG_TERMINUS = "terminus";
    public static final String TAG_INFOTRAFIC = "infotrafic";
    public static final String TAG_TEMPS = "temps";
    public static final String TAG_LIGNE = "ligne";
    public static final String TAG_ARRET = "arret";

    @Override
    public InfoTrafic toObject(JSONObject jsonObject) throws JSONException{
        Ligne ligne = new JSon2Ligne().toObject(jsonObject.getJSONObject(TAG_LIGNE));
        Arret arret = new JSon2Arret().toObject(jsonObject.getJSONObject(TAG_ARRET));
        return new InfoTrafic(jsonObject.getInt(TAG_SENS),
                jsonObject.getString(TAG_TERMINUS),
                jsonObject.getBoolean(TAG_INFOTRAFIC),
                jsonObject.getString(TAG_TEMPS),
                ligne,
                arret);
    }

}
