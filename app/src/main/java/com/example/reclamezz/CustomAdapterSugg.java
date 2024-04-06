package com.example.reclamezz;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterSugg extends RecyclerView.Adapter<CustomAdapterSugg.MyViewHolder> {
    private Context context;
    private ArrayList id_suggestion, adresse_suggestion, description_suggestion;
    private Activity activity;

    CustomAdapterSugg(Activity activity,Context context, ArrayList id_suggestion, ArrayList adresse_suggestion, ArrayList description_suggestion){
        this.context=context;
        this.activity=activity;
        this.id_suggestion=id_suggestion;
        this.adresse_suggestion=adresse_suggestion;
        this.description_suggestion=description_suggestion;
    }

    @NonNull
    @Override
    public CustomAdapterSugg.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id_txt.setText(String.valueOf(id_suggestion.get(position)));
        holder.adresse_txt.setText(String.valueOf(adresse_suggestion.get(position)));
        holder.description_txt.setText(String.valueOf(description_suggestion.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SuggUpDelActivity.class);
                intent.putExtra("id", String.valueOf(id_suggestion.get(position)));
                intent.putExtra("adresse", String.valueOf(adresse_suggestion.get(position)));
                intent.putExtra("description", String.valueOf(description_suggestion.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id_suggestion.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt, adresse_txt, description_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt=itemView.findViewById(R.id.id_txt);
            adresse_txt=itemView.findViewById(R.id.adresse_txt);
            description_txt=itemView.findViewById(R.id.description_txt);
            mainLayout=itemView.findViewById(R.id.mainLayout);

        }
    }

}
