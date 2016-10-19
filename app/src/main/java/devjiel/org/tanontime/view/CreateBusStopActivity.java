package devjiel.org.tanontime.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import devjiel.org.tanontime.R;

/**
 * Created by devjiel on 19/10/2016.
 */
public class CreateBusStopActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_bus_stop_layout);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CreateBusStopActivity.this.finish();
        overridePendingTransition(R.anim.slidein_end, R.anim.slideout_end);
    }

}
