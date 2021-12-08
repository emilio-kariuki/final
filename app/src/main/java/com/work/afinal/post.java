package com.work.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class post extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        TextView amount = findViewById(R.id.amount);
        TextView name = findViewById(R.id.name);
        ImageView uploaded = findViewById(R.id.uploaded);
        CheckBox small = findViewById(R.id.small);
        CheckBox high = findViewById(R.id.high);
        Button submit = findViewById(R.id.register);

    }
}