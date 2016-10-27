package devjiel.org.tanontime.view.newbusstop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import devjiel.org.tanontime.R;

/**
 * Created by devjiel on 19/10/2016.
 */
public class NewBusStopActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_bus_stop_activity_layout);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            NewBusStopListFragment busStopListFragment = new NewBusStopListFragment();
            busStopListFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, busStopListFragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NewBusStopActivity.this.finish();
        overridePendingTransition(R.anim.slidein_end, R.anim.slideout_end);
    }

}
