package com.udb.sosalert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class emerg extends AppCompatActivity {

    private Button pol;
    private Button amb;
    private Button whlpine;
    private Button domabuse;
    private Button nationemer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emerg);

        pol = findViewById(R.id.police);
        amb = findViewById(R.id.amb);
        whlpine = findViewById(R.id.wmn);
        domabuse = findViewById(R.id.domestic);
        nationemer = findViewById(R.id.emernum);


        pol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) ==
                        PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:100"));
                    startActivity(intent);
                }
                else {
                    String[] prs = {Manifest.permission.CALL_PHONE};

                    ActivityCompat.requestPermissions(emerg.this,
                            prs, 2);
                }
            }
        });  amb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) ==
                        PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:102"));
                    startActivity(intent);
                }
                else {
                    String[] prs = {Manifest.permission.CALL_PHONE};

                    ActivityCompat.requestPermissions(emerg.this,
                            prs, 2);
                }
            }
        });  whlpine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) ==
                        PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:1091"));
                    startActivity(intent);
                }
                else {
                    String[] prs = {Manifest.permission.CALL_PHONE};

                    ActivityCompat.requestPermissions(emerg.this,
                            prs, 2);
                }
            }
        });  domabuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) ==
                        PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:181"));
                    startActivity(intent);
                }
                else {
                    String[] prs = {Manifest.permission.CALL_PHONE};

                    ActivityCompat.requestPermissions(emerg.this,
                            prs, 2);
                }
            }
        });  nationemer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) ==
                        PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:112"));
                    startActivity(intent);
                }
                else {
                    String[] prs = {Manifest.permission.CALL_PHONE};

                    ActivityCompat.requestPermissions(emerg.this,
                            prs, 2);
                }
            }
        });
    }
}
