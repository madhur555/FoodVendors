package com.example.madhurgoyal.lamavendors;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
    private EditText name,pass,loc,key;
    private Button signup;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        key = findViewById(R.id.email1);

        pass = findViewById(R.id.pass1);
        signup = findViewById(R.id.button3);


        ref = database.getReference("Vendors");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signup();

            }
        });
    }

    public void signup(){
        String email = key.getText().toString().concat("@lama.com");
        String password = pass.getText().toString();
        auth = FirebaseAuth.getInstance();
        String k = key.getText().toString();

        name = findViewById(R.id.sname);
        String n = name.getText().toString();
        loc = findViewById(R.id.loc);
        String l = loc.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(signup.this, "Empty", Toast.LENGTH_LONG).show();
        }
        else {
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(!task.isSuccessful()){
                        Toast.makeText(signup.this, "Sign Up Problem", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(signup.this, "Sign Up Completed", Toast.LENGTH_LONG).show();
                    }

                }
            });



            Vendors vendor = new Vendors(n,l);
            ref.child(key.getText().toString()).setValue(vendor);

        }
    }

}
