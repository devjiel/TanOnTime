package devjiel.org.tanontime.service;

import devjiel.org.tanontime.model.arret.Arret;
import devjiel.org.tanontime.model.arret.converter.JSon2Arret;
import devjiel.org.tanontime.model.tempsattente.InfoTrafic;
import devjiel.org.tanontime.model.tempsattente.converter.JSon2InfoTrafic;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class RestTanService {

    public static final String TAN_OPEN_DATA_URL = "http://open_preprod.tan.fr/ewp/";
    public static final String TAN_TEMPS_ATTENTE = TAN_OPEN_DATA_URL + "tempsattente.json";
    public static final String TAN_ARRET = TAN_OPEN_DATA_URL + "arrets.json";
    public static final String REQUEST_ENCODING = "ISO-8859-1";

    public String callService(String serverURL, String... parameters) throws IOException {

        for(String param: parameters) {
            serverURL += "/" + param;
        }

        BufferedReader reader=null;
        String result;

        // Send data
        try {
            // Defined URL  where to send data
            URL url = new URL(serverURL);

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), REQUEST_ENCODING));
            StringBuilder sb = new StringBuilder();
            String line;

            // Read Server Response
            while ((line = reader.readLine()) != null) {
                // Append server response in string
                sb.append(line + "");
            }

            // Append Server Response To Content String
            result = sb.toString();
        } finally {
            if (null != reader) {
                reader.close();
            }
        }

        return result;
    }

    public List<InfoTrafic> callTempAttenteService(String arret) throws IOException {

        String result = callService(TAN_TEMPS_ATTENTE, arret);

        JSONArray jsonResponse;
        List<InfoTrafic> infoTrafics = new ArrayList<>();

        try {

            /****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
            jsonResponse = new JSONArray(result);

            for(int i=0; i < jsonResponse.length(); i++)
            {
                /****** Get Object for each JSON node.***********/
                JSONObject jsonChildNode = jsonResponse.getJSONObject(i);
                infoTrafics.add(new JSon2InfoTrafic().toObject(jsonChildNode));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return infoTrafics;
    }

    public List<Arret> callArretService() throws IOException {

        String result = callService(TAN_ARRET);

        JSONArray jsonResponse;
        List<Arret> arrets = new ArrayList<>();

        try {

            /****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
            jsonResponse = new JSONArray(result);

            for(int i=0; i < jsonResponse.length(); i++)
            {
                /****** Get Object for each JSON node.***********/
                JSONObject jsonChildNode = jsonResponse.getJSONObject(i);
                arrets.add(new JSon2Arret().toObject(jsonChildNode));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrets;
    }

}
