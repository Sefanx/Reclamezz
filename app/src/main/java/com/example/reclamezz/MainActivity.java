package com.example.reclamezz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.reclamezz.MesReclamations;
import com.example.reclamezz.MesSuggestions;
import com.google.android.material.navigation.NavigationView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CardView ReclamationCard, SuggestionCard;
    ImageButton menubtn;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    ActionBar actionBar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        ReclamationCard=findViewById(R.id.RecalamationCard);
        SuggestionCard=findViewById(R.id.SuggestionCard);
        SuggestionCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, AddSuggestion.class);
                startActivity(intent);
            }
        });
        ReclamationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(MainActivity.this, AddReclamation.class);
                startActivity(it);
            }
        });
        menubtn=findViewById(R.id.menubtn);
        drawerLayout=findViewById(R.id.drawerlayout);
        /*actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);*/

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
        menubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.reclamations:
                        Intent itr = new Intent(MainActivity.this, MesReclamations.class);
                        startActivity(itr);
                        Toast.makeText(MainActivity.this, "Vos Reclamations", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.suggestions:
                        Intent its = new Intent(MainActivity.this, MesSuggestions.class);
                        startActivity(its);
                        Toast.makeText(MainActivity.this, "Vos Suggestions", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.quitter:
                        finish();
                        break;
                    case R.id.contact:
                        Intent itC=new Intent(MainActivity.this, ContactActivity.class);
                        startActivity(itC);
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

