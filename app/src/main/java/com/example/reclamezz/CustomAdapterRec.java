package com.example.reclamezz;

import static androidx.core.app.ActivityCompat.recreate;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterRec extends RecyclerView.Adapter<CustomAdapterRec.MyViewHolder> {
    private Context context;
    private ArrayList id_reclamation, adresse_reclamation, description_reclamation;
    private Activity activity;

    CustomAdapterRec(Activity activity, Context context, ArrayList id_reclamation, ArrayList adresse_reclamation, ArrayList description_reclamation){
        this.activity=activity;
        this.context=context;
        this.id_reclamation=id_reclamation;
        this.adresse_reclamation=adresse_reclamation;
        this.description_reclamation=description_reclamation;
    }

    @NonNull
    @Override
    public CustomAdapterRec.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id_reclamation_txt.setText(String.valueOf(id_reclamation.get(position)));
        holder.adresse_reclamation_txt.setText(String.valueOf(adresse_reclamation.get(position)));
        holder.description_reclamation_txt.setText(String.valueOf(description_reclamation.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, RecUpDelActivity.class);
                intent.putExtra("id", String.valueOf(id_reclamation.get(position)));
                intent.putExtra("adresse", String.valueOf(adresse_reclamation.get(position)));
                intent.putExtra("description", String.valueOf(description_reclamation.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
            }

    @Override
    public int getItemCount() {
        return id_reclamation.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_reclamation_txt, adresse_reclamation_txt, description_reclamation_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_reclamation_txt=itemView.findViewById(R.id.id_txt);
            adresse_reclamation_txt=itemView.findViewById(R.id.adresse_txt);
            description_reclamation_txt=itemView.findViewById(R.id.description_txt);
            mainLayout=itemView.findViewById(R.id.mainLayout);

        }
    }



}
