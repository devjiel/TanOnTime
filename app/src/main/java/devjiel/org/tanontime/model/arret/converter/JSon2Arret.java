package devjiel.org.tanontime.model.arret.converter;

import devjiel.org.tanontime.model.arret.Ligne;
import devjiel.org.tanontime.model.converter.JSon2Object;
import devjiel.org.tanontime.model.arret.Arret;
import devjiel.org.tanontime.model.tempsattente.converter.JSon2InfoTrafic;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by devjiel on 16/10/2016.
 */
public class JSon2Arret implements JSon2Object<Arret> {

    public static final String TAG_CODE_LIEU = "codeLieu";
    public static final String TAG_LIBELLE = "libelle";
    public static final String TAG_DISTANCE = "distance";
    public static final String TAG_LIGNES = "ligne";

    @Override
    public Arret toObject(JSONObject jsonObject) throws JSONException{
        List<Ligne> lines = new ArrayList<>();
        JSon2Ligne lineConverter = new JSon2Ligne();
        JSONArray jsonArray = jsonObject.getJSONArray(TAG_LIGNES);
        for(int i=0; i < jsonArray.length(); i++)
        {
            lines.add(lineConverter.toObject(jsonArray.getJSONObject(i)));
        }

        jsonObject.getJSONArray(TAG_LIGNES);
        return new Arret(jsonObject.getString(TAG_CODE_LIEU), jsonObject.getString(TAG_LIBELLE), jsonObject.getString(TAG_DISTANCE), lines);
    }

}
