package com.example.reclamezz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MesSuggestions extends AppCompatActivity {
    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    ArrayList<String> id_suggestion, adresse_suggestion, description_suggestion;
    CustomAdapterSugg customAdapterSugg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_suggestions);
        recyclerView=findViewById(R.id.recyclerview);
        myDB= new MyDatabaseHelper(MesSuggestions.this);
        id_suggestion= new ArrayList<>();
        adresse_suggestion= new ArrayList<>();
        description_suggestion= new ArrayList<>();
        storeDataInArrays();
        customAdapterSugg = new CustomAdapterSugg(MesSuggestions.this,MesSuggestions.this, id_suggestion, adresse_suggestion, description_suggestion);
        recyclerView.setAdapter(customAdapterSugg);
        recyclerView.setLayoutManager(new LinearLayoutManager(MesSuggestions.this));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
    void storeDataInArrays(){
        Cursor cursor = myDB.readAllDataS();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                id_suggestion.add(cursor.getString(0));
                adresse_suggestion.add(cursor.getString(1));
                description_suggestion.add(cursor.getString(2));

            }
        }
    }
}