package com.work.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_up extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        EditText name = findViewById(R.id.name);
        EditText email = findViewById(R.id.phone);
        EditText password = findViewById(R.id.password);


        Button register1 = findViewById(R.id.register1);


        TextView login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sign_up.this, login_page.class));
            }
        });

        register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_name = name.getText().toString();
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();

                if (txt_email.isEmpty()) {
                    email.setError("Fill empty field");
                    email.requestFocus();
                } else if (txt_password.isEmpty()) {
                    password.setError("Fill empty field");
                    password.requestFocus();
                } else if (txt_email.isEmpty() && txt_password.isEmpty()) {
                    Toast.makeText(Sign_up.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                    email.setError("Fill all fields");
                    password.setError("Fill all fields");
                    email.requestFocus();
                    password.requestFocus();
                } else if (txt_password.length() < 6) {
                    password.setError("Password too short,Try again!!");
                    password.requestFocus();
                } else {
                    registerUser(txt_email, txt_password);
                }

            }
        });




    }

    private void registerUser(String txt_email, String txt_password) {
        auth.createUserWithEmailAndPassword(txt_email, txt_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Sign_up.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Sign_up.this, MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(Sign_up.this,"Registration Error",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}