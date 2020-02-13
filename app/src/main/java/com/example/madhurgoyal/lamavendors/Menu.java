package com.example.madhurgoyal.lamavendors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Menu extends AppCompatActivity {

    private EditText item,price,key;
    private Button upload;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference myRef = database.getReference("Vendors");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

//        key = findViewById(R.id.email);
        item = findViewById(R.id.item1);
        price = findViewById(R.id.price);
        upload = findViewById(R.id.upload);




        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });
    }

    private void upload(){
        String i = item.getText().toString();
        String p = price.getText().toString();

//        signup s = new signup();
//        String vid = s.getVendor();
//        String vid = "8949274902";
//        String vid = key.getText().toString();

//        MainActivity m = new MainActivity();
//        item.setText(m.getVendor());

        Bundle bundle = getIntent().getExtras();
        String vid = bundle.getString("vid");
        item.setText("");
        price.setText("");
        DatabaseReference myRef = database.getReference("Vendors/".concat(vid));
        Toast.makeText(this, "Item Added", Toast.LENGTH_LONG);


//        Items items = new Items(i,p);
        myRef.child("Menu/".concat(i)).setValue(p);
    }
}
