package devjiel.org.tanontime.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import devjiel.org.tanontime.R;
import devjiel.org.tanontime.model.arret.Arret;
import devjiel.org.tanontime.service.RestTanService;

import java.io.IOException;
import java.util.List;

/**
 * Created by devjiel on 19/10/2016.
 */
public class CreateBusStopActivity extends AppCompatActivity {

    private TextView text;
    private String content;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_bus_stop_layout);

        text = (TextView) findViewById(R.id.title);

        // TODO: Add Progress Bar
        new ServiceCallArret().execute();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CreateBusStopActivity.this.finish();
        overridePendingTransition(R.anim.slidein_end, R.anim.slideout_end);
    }

    private class ServiceCallArret extends AsyncTask<String, Void, Void> {

        private RestTanService service;

        // Required initialization
        private String Error = null;

        public ServiceCallArret() {
            service = new RestTanService();
        }

        @Override
        protected void onPreExecute() {


        }

        // Call after onPreExecute method
        protected Void doInBackground(String... urls) {



            /************ Make Post Call To Web Server ***********/
            try {
                content = "";
                List<Arret> arrets = service.callArretService();
                for(Arret arret: arrets) {
                    content += arret.getLibelle() + "\n";
                }

            } catch (IOException ioe) {
                Error = ioe.getMessage();
            }

            /*****************************************************/
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {

            if (Error != null) {

                // TODO print Error (Toaster)

            } else {
                text.setText(content);
            }
        }

    }

}
