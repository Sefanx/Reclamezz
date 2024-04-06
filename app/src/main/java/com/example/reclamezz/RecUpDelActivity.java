package com.example.reclamezz;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RecUpDelActivity extends AppCompatActivity {
    EditText adresse_input, description_input;
    Button UpdateButton, DeleteButton;
    String id, adresse, description;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_del);
        adresse_input = findViewById(R.id.adresse_input);
        description_input = findViewById(R.id.description_input);
        UpdateButton = findViewById(R.id.update_button);
        DeleteButton=findViewById(R.id.delete_button);
        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Reclamation "+id);
        }
        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(RecUpDelActivity.this);
                adresse = adresse_input.getText().toString().trim();
                description = description_input.getText().toString().trim();
                myDB.updateDataRec(id, adresse, description);
                finish();
            }
        });
        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("adresse")
                && getIntent().hasExtra("description"))
        {
            id = getIntent().getStringExtra("id");
            adresse = getIntent().getStringExtra("adresse");
            description = getIntent().getStringExtra("description");
            adresse_input.setText(adresse);
            description_input.setText(description);
        }
        else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Suppression de la reclamation ");
        builder.setMessage("Etes-vous sure de supprimer cette  reclamation ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(RecUpDelActivity.this);
                myDB.deleteOneRow(id);
                myDB.updateIdsAfterDeleteRec();
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
