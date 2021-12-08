package com.work.afinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_page extends AppCompatActivity {
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        LottieAnimationView lottie = findViewById(R.id.lottie_sign);
        auth = FirebaseAuth.getInstance();
        TextView sign = findViewById(R.id.sign);
        EditText password = findViewById(R.id.password1);
        EditText email = findViewById(R.id.email);
        Button register = findViewById(R.id.register);
        TextView forgot = findViewById(R.id.forgot);

        forgot.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          EditText resetEmail = new EditText(view.getContext());
                                          AlertDialog.Builder passwordReset = new AlertDialog.Builder(view.getContext());
                                          passwordReset.setTitle("Reset Password of Email");
                                          passwordReset.setView(resetEmail);

                                          passwordReset.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                                              @Override
                                              public void onClick(DialogInterface dialogInterface, int i) {
                                                  String mail = resetEmail.getText().toString();

                                                  auth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                      @Override
                                                      public void onSuccess(Void unused) {
                                                          Toast.makeText(login_page.this, "Reset Link sent successful", Toast.LENGTH_SHORT).show();

                                                      }
                                                  }).addOnFailureListener(new OnFailureListener() {
                                                      @Override
                                                      public void onFailure(@NonNull Exception e) {
                                                          Toast.makeText(login_page.this, "Reset Link not sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                      }
                                                  });

                                              }
                                          });
                                      }
                                  });

        register.setOnClickListener(v -> {
            String txt_email = email.getText().toString();
            String txt_password = password.getText().toString();

            if(txt_email.isEmpty()){
                email.setError("Fill the spaces");
                email.requestFocus();
            }else if(txt_password.isEmpty()){
                password.setError("Fill the spaces");
                password.requestFocus();
            }else if(txt_email.isEmpty() && txt_password.isEmpty()){
                email.setError("Fill the spaces");
                email.requestFocus();
                password.setError("Fill the spaces");
                password.requestFocus();
                Toast.makeText(login_page.this,"Ensure all blanks are filled",Toast.LENGTH_SHORT).show();
            }
            loginUser(txt_email,txt_password);

        });

        sign.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(login_page.this, question.class));

            }
        });
    }

    private void loginUser(String txt_email, String txt_password) {
        auth.signInWithEmailAndPassword(txt_email,txt_password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(login_page.this, "Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(login_page.this, MainActivity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(login_page.this, "Login Error" + e.getMessage(), Toast.LENGTH_SHORT);
            }
        });
    }
}
