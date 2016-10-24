package devjiel.org.tanontime.view.newbusstop;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.*;
import devjiel.org.tanontime.R;
import devjiel.org.tanontime.model.arret.Arret;
import devjiel.org.tanontime.service.RestTanService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by devjiel on 22/10/2016.
 */
public class BusStopListFragment extends Fragment implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private List<Arret> arrets;
    private NewBusStopAdapter newBusStopAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        new ServiceCallArret().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bus_stop_list_layout, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerviewNewBusStop);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.new_bus_stop_menu, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);
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
                    recyclerView.setAdapter(newBusStopAdapter);
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
