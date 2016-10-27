package devjiel.org.tanontime.view.newbusstop;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import devjiel.org.tanontime.R;
import devjiel.org.tanontime.model.arret.Arret;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by devjiel on 21/10/2016.
 */
public class NewBusStopListAdapter extends RecyclerView.Adapter<NewBusStopListAdapter.NewBusStopHolder> {

    private List<Arret> arrets;
    private final OnBusStopClickListener listener;


    public NewBusStopListAdapter(List<Arret> arrets, OnBusStopClickListener listener) {
        this.arrets = arrets;
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(NewBusStopHolder newBusStopHolder, int i) {
        final Arret model = arrets.get(i);
        newBusStopHolder.bind(model, listener);
    }

    @Override
    public NewBusStopHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bus_stop_list_card_layout, viewGroup, false);
        return new NewBusStopHolder(view);
    }

    @Override
    public int getItemCount() {
        return arrets.size();
    }

    public void setFilter(List<Arret> arrets) {
        this.arrets = new ArrayList<>();
        this.arrets.addAll(arrets);
        notifyDataSetChanged();
    }

    public static class NewBusStopHolder extends RecyclerView.ViewHolder {

        public TextView libelle;
        public TextView lignes;

        public NewBusStopHolder(View itemView) {
            super(itemView);

            libelle = (TextView) itemView.findViewById(R.id.libelle);
            lignes = (TextView) itemView.findViewById(R.id.lignes);
        }

        public void bind(final Arret arret, final OnBusStopClickListener listener) {

            libelle.setText(arret.getLibelle());
            String lignesContent = "Lignes: ";
            for (int i = 0; i < arret.getLignes().size(); i++) {
                lignesContent += arret.getLignes().get(i).getNumLigne();
                if (i < arret.getLignes().size() - 1) {
                    lignesContent += "-";
                }
            }
            lignes.setText(lignesContent);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(arret);
                }
            });

        }
    }

}
