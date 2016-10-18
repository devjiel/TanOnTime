package devjiel.org.tanontime.view;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import devjiel.org.tanontime.R;
import devjiel.org.tanontime.model.InfoTrafic;
import devjiel.org.tanontime.model.converter.JSon2InfoTrafic;
import devjiel.org.tanontime.service.RestTanService;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // final Button GetServerData = (Button) findViewById(R.id.GetServerData);

        CardView card = (CardView) findViewById(R.id.main_card_view);

        card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // WebServer Request URL
                String serverURL = "http://open.tan.fr/ewp/tempsattente.json/RPAR";

                // Use AsyncTask execute Method To Prevent ANR Problem
                new LongOperation().execute(serverURL);
            }
        });

    }


    // Class with extends AsyncTask class

    private class LongOperation  extends AsyncTask<String, Void, Void> {

        private RestTanService service;

        // Required initialization
        private List<InfoTrafic> infoTrafics;
        private String Error = null;
        private ProgressDialog Dialog = new ProgressDialog(MainActivity.this);
        TextView text = (TextView) findViewById(R.id.info_text_2);


        public LongOperation() {
            service = new RestTanService();
        }

        protected void onPreExecute() {

            //Start Progress Dialog (Message)
            Dialog.setMessage("Récuperation des données en cours ...");
            Dialog.show();

        }

        // Call after onPreExecute method
        protected Void doInBackground(String... urls) {



            /************ Make Post Call To Web Server ***********/
            try {
                infoTrafics = service.callTempAttenteService("RPAR");
            } catch (IOException ioe) {
                Error = ioe.getMessage();
            }

            /*****************************************************/
            return null;
        }

        protected void onPostExecute(Void unused) {
            // NOTE: You can call UI Element here.

            // Close progress dialog
            Dialog.dismiss();

            if (Error != null) {

                // TODO print Error

            } else {

                String OutputData = "";

                if (null != infoTrafics) {
                    for(InfoTrafic info: infoTrafics) {
                        OutputData += info.getLigne().getNumLigne() + " Vers " + info.getTerminus() + ": " + info.getTemps() + "\n";
                    }
                }

                text.setText( OutputData );

            }
        }

    }

}
