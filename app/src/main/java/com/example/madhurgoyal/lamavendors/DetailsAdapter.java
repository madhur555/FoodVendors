package com.example.madhurgoyal.lamavendors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {

    private List<DetailsList> detailsLists;
    private Context context;

    public DetailsAdapter(List<DetailsList> detailsLists, Context context) {
        this.detailsLists = detailsLists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_details,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        DetailsList detailsList = detailsLists.get(i);

        viewHolder.oitem.setText(detailsList.getQitem());
        viewHolder.oqty.setText(detailsList.getQty());
    }

    @Override
    public int getItemCount() {
        int s = detailsLists.size();
        return detailsLists.size();
//        return 9;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView oitem, oqty;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            oitem = itemView.findViewById(R.id.oitem);
            oqty = itemView.findViewById(R.id.oqty);
        }
    }

}
