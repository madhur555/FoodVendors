package com.example.madhurgoyal.lamavendors;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Home extends AppCompatActivity {
    Button menu;

    String vid;
    List<OrderList> orderLists;
    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        menu = findViewById(R.id.add);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        orderLists = new ArrayList<>();

        Bundle b = getIntent().getExtras();
        vid = b.getString("vid");
        orderLists = new ArrayList<>();

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu();
            }
        });

//        for(int i = 0; i<10;i++){
//            OrderList orderList = new OrderList("10"+i,"20"+i,"30");
//            orderLists.add(orderList);
//        }

        adapter = new OrderAdapter(orderLists, this);
        recyclerView.setAdapter(adapter);

//        displayOrders();
    }

    public void menu() {
        Intent i = new Intent(this, Menu.class);
        Bundle bundle = new Bundle();
        bundle.putString("vid",vid);
        i.putExtras(bundle);
        startActivity(i);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        DatabaseReference myRef = database.getReference("Orders/test/try");

//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
//                    String key = dataSnapshot.getKey();
//                    String value = dataSnapshot.getValue(String.class);
//                    OrderList orderList = new OrderList();
//                    orderList.setCustomerid(key);
//                    orderList.setTotal(value);
//
//
//                adapter = new OrderAdapter(orderLists, Home.this);
//                recyclerView.setAdapter(adapter);
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        DatabaseReference myRef = database.getReference("Orders/".concat(vid));
//
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String total = "",cid = "",status = "";

                orderLists.clear();
                for(DataSnapshot order: dataSnapshot.getChildren()){

                    String key = order.getKey();
                    Log.v("E_Value", ""+order.getValue());

                    OrderList orderList = order.getValue(OrderList.class);
                    orderList.setKey(key);
                    orderList.setVid(vid);
                    orderLists.add(orderList);

                }

                adapter = new OrderAdapter(orderLists,Home.this);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//    }

    }
}
