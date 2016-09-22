package com.harish.collpollassignment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by harish on 22/09/16.
 */
public class TrainsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Train> trains;

    public TrainsRecyclerAdapter(Context context, List<Train> trains) {
        this.context = context;
        this.trains = trains;
    }

    class AllTeamsHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvId;
        TextView tvSeats;


        public AllTeamsHolder(View item) {
            super(item);
            tvName = (TextView) item.findViewById(R.id.name);
            tvId = (TextView) item.findViewById(R.id.id);
            tvSeats = (TextView) item.findViewById(R.id.seats);
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View liveView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_list_item, parent, false);
        return new AllTeamsHolder(liveView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {

        AllTeamsHolder allTeamsHolder = (AllTeamsHolder) holder;
        allTeamsHolder.tvName.setText("name : "+trains.get(i).getName());
        allTeamsHolder.tvSeats.setText("seats : "+trains.get(i).getSeats());
        allTeamsHolder.tvId.setText("id : "+trains.get(i).getId());
    }

    @Override
    public int getItemCount() {
        return trains.size();
    }

}
