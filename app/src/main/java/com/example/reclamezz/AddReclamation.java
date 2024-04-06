package com.example.reclamezz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddReclamation extends AppCompatActivity {
    EditText adresse_reclamation, description_reclamation;
    Button deposer_button, mesreclamations_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reclamation);
        adresse_reclamation=findViewById(R.id.adresse_reclamation);
        description_reclamation=findViewById(R.id.description_reclamation);
        deposer_button=findViewById(R.id.deposer_button);
        mesreclamations_button=findViewById(R.id.mesreclamations_button);
        mesreclamations_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddReclamation.this, MesReclamations.class);
                startActivity(intent);
            }
        });
        deposer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB=new MyDatabaseHelper(AddReclamation.this);
                myDB.addReclamation(adresse_reclamation.getText().toString().trim(),
                        description_reclamation.getText().toString().trim());
            }
        });

    }
}