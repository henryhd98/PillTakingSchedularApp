package com.Henry.poppinsmarter;

import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
public class ProfileActivity  extends AppCompatActivity {
    TextView result;
    Button schedular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        setContentView(R.layout.activity_profile);
        schedular = findViewById(R.id.schedular1);
        schedular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        retrieveMessage();

    }

    private void retrieveMessage() {
        result = findViewById(R.id.alarmDeets);
        result.setText(getIntent().getStringExtra("message"));
    }
}