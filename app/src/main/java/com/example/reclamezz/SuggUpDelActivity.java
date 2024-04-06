package com.example.reclamezz;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SuggUpDelActivity extends AppCompatActivity {
    EditText adresse_input, description_input;
    Button UpdateButtonS,DeleteButtonS;
    String id, adresse, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugg_up_del);
        adresse_input = findViewById(R.id.adresse_inputS);
        description_input = findViewById(R.id.description_inputS);
        UpdateButtonS = findViewById(R.id.update_buttonS);
        DeleteButtonS=findViewById(R.id.delete_buttonS);
        getAndSetIntentDataS();
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Suggestion "+id);
        }
        UpdateButtonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(SuggUpDelActivity.this);
                adresse = adresse_input.getText().toString().trim();
                description = description_input.getText().toString().trim();
                myDB.updateDataSugg(id, adresse, description);
                finish();
            }
        });
        DeleteButtonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialogS();
            }
        });

    }
    void getAndSetIntentDataS() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("adresse")
                && getIntent().hasExtra("description"))
        {
            id= getIntent().getStringExtra("id");
            adresse = getIntent().getStringExtra("adresse");
            description = getIntent().getStringExtra("description");
            adresse_input.setText(adresse);
            description_input.setText(description);
        }
        else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialogS(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Suppression de la suggestion ");
        builder.setMessage("Etes-vous sure de supprimer cette  suggestion ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(SuggUpDelActivity.this);
                myDB.deleteOneRowS(id);
                myDB.updateIdsAfterDeleteSugg();
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
