package devjiel.org.tanontime.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import devjiel.org.tanontime.R;
import devjiel.org.tanontime.model.arret.Arret;
import devjiel.org.tanontime.model.arret.Ligne;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by devjiel on 21/10/2016.
 */
public class NewBusStopAdapter extends RecyclerView.Adapter<NewBusStopAdapter.NewBusStopHolder> {

    private List<Arret> arrets;

    public NewBusStopAdapter(List<Arret> arrets) {
        this.arrets = arrets;
    }

    @Override
    public void onBindViewHolder(NewBusStopHolder newBusStopHolder, int i) {
        final Arret model = arrets.get(i);
        newBusStopHolder.bind(model);
    }

    @Override
    public NewBusStopHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_new_bus_stop_layout, viewGroup, false);
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

        public void bind(Arret arret) {
            libelle.setText(arret.getLibelle());
            String lignesContent = "Lignes: ";
            for (int i = 0; i < arret.getLignes().size(); i++) {
                lignesContent += arret.getLignes().get(i).getNumLigne();
                if (i < arret.getLignes().size() - 1) {
                    lignesContent += "-";
                }
            }
            lignes.setText(lignesContent);
        }
    }

}
