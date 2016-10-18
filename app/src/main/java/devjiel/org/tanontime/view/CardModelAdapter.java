package devjiel.org.tanontime.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import devjiel.org.tanontime.R;
import devjiel.org.tanontime.model.CardModel;
import devjiel.org.tanontime.model.InfoTrafic;

import java.util.List;

/**
 * Created by devjiel on 18/10/2016.
 */
public class CardModelAdapter extends RecyclerView.Adapter<CardModelAdapter.CardModelHolder> {

    private List<CardModel> cardModels;

    @Override
    public CardModelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        CardModelHolder pvh = new CardModelHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(CardModelHolder holder, int position) {
        CardModel card = cardModels.get(position);
        holder.title.setText(card.getTitle());
        String content = "";
        for(InfoTrafic info: card.getInfoTrafics()) {
            content += info.getLigne().getNumLigne() + " Vers " + info.getTerminus() + ": " + info.getTemps() + "\n";
        }
        holder.infos.setText(content);
    }

    @Override
    public int getItemCount() {
        return cardModels.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public CardModelAdapter(List<CardModel> cardModels) {
        this.cardModels = cardModels;
    }

    public static class CardModelHolder extends RecyclerView.ViewHolder {
        protected TextView title;
        protected TextView infos;

        public CardModelHolder(View v) {
            super(v);
            title =  (TextView) v.findViewById(R.id.title);
            infos = (TextView)  v.findViewById(R.id.infos);
        }
    }
}
