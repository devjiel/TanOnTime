package devjiel.org.tanontime.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import devjiel.org.tanontime.R;
import devjiel.org.tanontime.model.arret.Arret;
import devjiel.org.tanontime.service.RestTanService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by devjiel on 19/10/2016.
 */
public class CreateBusStopActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerview;
    private List<Arret> arrets;
    private NewBusStopAdapter newBusStopAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_bus_stop_layout);

        recyclerview = (RecyclerView) findViewById(R.id.recyclerviewNewBusStop);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);

        // TODO: Add Progress Bar
        new ServiceCallArret().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_bus_stop_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CreateBusStopActivity.this.finish();
        overridePendingTransition(R.anim.slidein_end, R.anim.slideout_end);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (null != newBusStopAdapter) {
            final List<Arret> filteredArrets = filter(arrets, newText);
            newBusStopAdapter.setFilter(filteredArrets);
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private List<Arret> filter(List<Arret> arrets, String query) {
        query = query.toLowerCase();

        final List<Arret> filteredArret = new ArrayList<>();
        for (Arret arret : arrets) {
            final String text = arret.getLibelle().toLowerCase();
            if (text.contains(query)) {
                filteredArret.add(arret);
            }
        }
        return filteredArret;
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
                arrets = service.callArretService();
            } catch (IOException ioe) {
                Error = ioe.getMessage();
            }

            /*****************************************************/
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {

            if (Error == null) {
                if(null == newBusStopAdapter) {
                    // Create adapter on first call
                    newBusStopAdapter = new NewBusStopAdapter(arrets);
                    recyclerview.setAdapter(newBusStopAdapter);
                } else {
                    newBusStopAdapter.notifyDataSetChanged();
                }

            } else {
                // TODO print Error (Toaster)
                //Log.e("ERROR", Error);
            }
        }

    }

}
