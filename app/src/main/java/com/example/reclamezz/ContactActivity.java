package com.example.reclamezz;

import androidx.annotation.NonNull;
import android.Manifest;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity {
    Button btnTel,btnSms;
    private static final int REQUEST_CALL_PERMISSION = 1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        btnSms=findViewById(R.id.btnSms);
        btnTel=findViewById(R.id.btnTel);
        btnTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCallPermission();
            }
        });

        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Methode envoyer sms
                sendSms();
            }
        });
    }
    private void checkCallPermission() {
        if (ContextCompat.checkSelfPermission(ContactActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ContactActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        } else {
            //methode telephoner
            makePhoneCall();
        }
    }
    private void makePhoneCall() {
        String phoneNumber = "tel:212625430346";

        Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse(phoneNumber));

        startActivity(dialIntent);
    }
    private void sendSms() {
        String phoneNumber = "212625430346";

        String smsBody = "Bonjour! Test message";

        Uri smsUri = Uri.parse("smsto:" + phoneNumber);

        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, smsUri);

        smsIntent.putExtra("sms_body", smsBody);

        startActivity(smsIntent);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission refusée, impossible de passer un appel téléphonique", Toast.LENGTH_SHORT).show();
            }
        }
    }

}