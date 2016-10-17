package devjiel.org.tanontime.model.converter;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by devjiel on 16/10/2016.
 */
public interface JSon2Object<T> {

   T toObject(JSONObject jsonObject) throws JSONException;

}
