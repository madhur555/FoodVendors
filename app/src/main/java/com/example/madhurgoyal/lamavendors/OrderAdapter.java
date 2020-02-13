package com.example.madhurgoyal.lamavendors;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<OrderList> orderLists;
    private Context context;

    public OrderAdapter() {
    }

    public OrderAdapter(List<OrderList> orderLists, Context context) {
        this.orderLists = orderLists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        return null;
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_list,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final OrderList orderList = orderLists.get(i);
        viewHolder.oid.setText(orderList.getCustomerid());
        viewHolder.total.setText(orderList.getTotal());
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderStatus.class);
                Bundle b = new Bundle();
                b.putString("key",orderList.getKey());
                b.putString("vid",orderList.getVid());
                intent.putExtras(b);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
//        return 0;
        return orderLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView oid, total;
        public LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            oid = itemView.findViewById(R.id.oid);
            total = itemView.findViewById(R.id.total);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
