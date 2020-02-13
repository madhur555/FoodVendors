package com.example.madhurgoyal.lamavendors;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderStatus extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    String key,vid;

    List<DetailsList> detailsLists;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private Button accept, preparing, handed, reject;
    private EditText did;
    private TextView address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        address = findViewById(R.id.address);
        accept = findViewById(R.id.accept);
        preparing = findViewById(R.id.preparing);
        handed = findViewById(R.id.handed);
        reject = findViewById(R.id.reject);
        did = findViewById(R.id.did);

        detailsLists = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        key = bundle.getString("key");
        vid = bundle.getString("vid");

        final DatabaseReference ref = database.getReference("Orders/".concat(vid).concat("/").concat(key).concat("/"));

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child("status").setValue("1");
                accept.setEnabled(false);
                reject.setEnabled(false);
                preparing.setEnabled(true);
            }
        });

        preparing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child("status").setValue("2");
                preparing.setEnabled(false);
                handed.setEnabled(true);
            }
        });

        handed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child("status").setValue("3");
                DatabaseReference myRef = database.getReference("Orders/".concat(vid).concat("/").concat(key));
                myRef.child("did").setValue(""+did.getText());
                handed.setEnabled(false);
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child("status").setValue("5");
                accept.setEnabled(false);
                reject.setEnabled(false);
                preparing.setEnabled(false);
                handed.setEnabled(false);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference myRef = database.getReference("Orders/".concat(vid).concat("/").concat(key).concat("/Order"));
        DatabaseReference myRef1 = database.getReference("Orders/".concat(vid).concat("/").concat(key));

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                detailsLists.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    String item = dataSnapshot1.getKey();
                    String qty = dataSnapshot1.getValue(String.class);

                    DetailsList detailsList = new DetailsList();
                    detailsList.setQitem(item);
                    detailsList.setQty(qty);
                    detailsLists.add(detailsList);
                }
                adapter = new DetailsAdapter(detailsLists, OrderStatus.this);
                recyclerView.setAdapter(adapter);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                address.setText("Address : "+dataSnapshot.child("Address").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
