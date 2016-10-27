package devjiel.org.tanontime.view.newbusstop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import devjiel.org.tanontime.R;

/**
 * Created by devjiel on 27/10/2016.
 */

public class NewBusStopEditFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bus_stop_edit_layout, container, false);
        return view;
    }


}
