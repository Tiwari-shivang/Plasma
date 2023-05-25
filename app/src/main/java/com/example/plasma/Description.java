package com.example.plasma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Description extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        TextView name = findViewById(R.id.nameAct);
        TextView description = findViewById(R.id.descriptionAct);

        name.setText(getIntent().getStringExtra("Name"));
        description.setText(getIntent().getStringExtra("Description"));
    }
}