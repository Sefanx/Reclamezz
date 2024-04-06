package com.example.reclamezz;

import static androidx.core.app.ActivityCompat.recreate;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MesReclamations extends AppCompatActivity  {
    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    ArrayList<String> id_reclamation, adresse_reclamation, description_reclamation;
    CustomAdapterRec customAdapterRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_reclamations);
        recyclerView=findViewById(R.id.recyclerview);
        myDB= new MyDatabaseHelper(MesReclamations.this);
        id_reclamation= new ArrayList<>();
        adresse_reclamation= new ArrayList<>();
        description_reclamation= new ArrayList<>();
        storeDataInArrays();
        customAdapterRec = new CustomAdapterRec(MesReclamations.this,MesReclamations.this, id_reclamation, adresse_reclamation, description_reclamation);
        recyclerView.setAdapter(customAdapterRec);
        recyclerView.setLayoutManager(new LinearLayoutManager(MesReclamations.this));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                id_reclamation.add(cursor.getString(0));
                adresse_reclamation.add(cursor.getString(1));
                description_reclamation.add(cursor.getString(2));

            }
        }
    }

}