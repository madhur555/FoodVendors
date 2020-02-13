package com.example.madhurgoyal.lamavendors;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Selection;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText key,pass;
    private Button login;
    private FirebaseAuth auth;
//    private static int time_out = 4000;

    private FirebaseAuth.AuthStateListener stateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        key = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        login = findViewById(R.id.login);

        stateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){

                    Intent i = new Intent(MainActivity.this, Selection.class);
                    startActivity(i);
                }
            }
        };

//        stateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//
//                if(firebaseAuth.getCurrentUser() == null){
//
//                    Intent i = new Intent(MainActivity.this, Selection.class);
//                    startActivity(i);
//                }
//            }
//        };

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String s = key.getText().toString();
                signIn();
            }
        });


    }



//    public void login()
//    {
//        Toast.makeText(MainActivity.this,"Login Part",Toast.LENGTH_LONG);
////         = findViewById(R.id.email);
////        pass = findViewById(R.id.pass);
////        String e = email.getText().toString();
////        String p = email.getText().toString();
////        auth = FirebaseAuth.getInstance();
//
//        if(TextUtils.isEmpty(e) || TextUtils.isEmpty(p)){
//            Toast.makeText(MainActivity.this,"Enter Email or Password",Toast.LENGTH_LONG);
//        }
//        else {
//
//            auth.signInWithEmailAndPassword(e, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if (!task.isSuccessful()) {
//                        Toast.makeText(MainActivity.this, "Check Email or Password", Toast.LENGTH_LONG);
//                    }
//                }
//            });
//        }


//    }

    private void signIn(){
        final String email = key.getText().toString().concat("@lama.com");
        String password = pass.getText().toString();
        auth = FirebaseAuth.getInstance();


//        Intent intent = new Intent(this,Menu.class);

//        startActivity(intent);

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(MainActivity.this, "Empty", Toast.LENGTH_LONG).show();
        }
        else {
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(!task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Sign In Problem", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent i = new Intent(MainActivity.this, Home.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("vid",key.getText().toString());
                        i.putExtras(bundle);
                        startActivity(i);
                    }
                }
            });
        }

    }

    public void signup(View view){
        Intent i = new Intent(this, signup.class);
        startActivity(i);
    }

    public String getVendor(){
//        String r = key.getText().toString();
//        String r = key.getText().toString();
        return "123456";
    }
}