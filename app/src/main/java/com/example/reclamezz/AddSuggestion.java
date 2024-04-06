package com.example.reclamezz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddSuggestion extends AppCompatActivity {
    EditText adresse_suggestion, description_suggeston;
    Button deposer_button, messuggetions_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_suggestion);
        adresse_suggestion=findViewById(R.id.adresse_suggestion);
        description_suggeston=findViewById(R.id.description_suggestion);
        deposer_button=findViewById(R.id.deposer_button);
        messuggetions_button=findViewById(R.id.messuggestions_button);
        messuggetions_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddSuggestion.this, MesSuggestions.class);
                startActivity(intent);
            }
        });
        deposer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB=new MyDatabaseHelper(AddSuggestion.this);
                myDB.addSuggestion(adresse_suggestion.getText().toString().trim(),
                        description_suggeston.getText().toString().trim());
            }
        });
    }
}