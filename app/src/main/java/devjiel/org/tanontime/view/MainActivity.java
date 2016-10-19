package devjiel.org.tanontime.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import devjiel.org.tanontime.R;
import devjiel.org.tanontime.model.tempsattente.CardModel;
import devjiel.org.tanontime.model.tempsattente.InfoTrafic;
import devjiel.org.tanontime.service.RestTanService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager recyclerLayoutManager;
    private SwipeRefreshLayout swipeRefresh;

    private List<CardModel> cardModels;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // TODO tmp -> static creation
        cardModels = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerList);
        recyclerView.setHasFixedSize(true);

        recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerLayoutManager);

        // Call service on startup
        new ServiceCallTempsArret().execute();

        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipeRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        new ServiceCallTempsArret().execute();
                    }
                }
        );

        FloatingActionButton floatingButton = (FloatingActionButton) findViewById(R.id.floatingButton);
        floatingButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent createBusStopActivity = new Intent(v.getContext(),CreateBusStopActivity.class);

                /*Bundle bndlanimation =
                        ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slidein_start,R.anim.slideout_start).toBundle();*/
                startActivity(createBusStopActivity);
                overridePendingTransition(R.anim.slidein_start, R.anim.slideout_start);
            }
        });

    }

    // Class with extends AsyncTask class

    private class ServiceCallTempsArret extends AsyncTask<String, Void, Void> {

        private RestTanService service;

        // Required initialization
        private String Error = null;

        public ServiceCallTempsArret() {
            service = new RestTanService();
        }

        @Override
        protected void onPreExecute() {


        }

        // Call after onPreExecute method
        protected Void doInBackground(String... urls) {



            /************ Make Post Call To Web Server ***********/
            try {
                List<InfoTrafic> infoTrafics = service.callTempAttenteService("RPAR");
                cardModels.add(new CardModel("Rond Point de Paris", infoTrafics));
            } catch (IOException ioe) {
                Error = ioe.getMessage();
            }

            /*****************************************************/
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {

            // Close progress dialog
            swipeRefresh.setRefreshing(false);
            if(null == recyclerAdapter) {
                // Create adapter on first call
                recyclerAdapter = new CardModelAdapter(cardModels);
                recyclerView.setAdapter(recyclerAdapter);
            } else {
                recyclerAdapter.notifyDataSetChanged();
            }

            if (Error != null) {

                // TODO print Error (Toaster)

            }
        }

    }

}
