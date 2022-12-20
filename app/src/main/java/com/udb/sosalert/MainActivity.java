package com.udb.sosalert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    String latitude, longitude;

    private Button sos;
    private Button add;
    private Button abt;
    private Button edtmsg;
    private Button emergency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sos = findViewById(R.id.sosbtn);
        add = findViewById(R.id.addbtn);
        abt = findViewById(R.id.abtbtn);
        emergency = findViewById(R.id.emergencybtn);
        edtmsg = findViewById(R.id.edtmsg);

        abt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),about.class);
                startActivity(intent);
            }
        });

        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), emerg.class);
                startActivity(intent);
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), addcontacts.class);
                startActivity(intent);
            }


        });

        sos.setOnClickListener(new View.OnClickListener() {
            private Context con;

            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS) ==
                        PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                                PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) ==
                                PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET) ==
                                PackageManager.PERMISSION_GRANTED) {


                    if (!locationServicesEnabled(getApplicationContext())) {
                        GPSOn();
                    } else {

                        SharedPreferences sh
                                = getSharedPreferences("MySharedPref",
                                MODE_PRIVATE);

                        String num1 = sh.getString("num1", "0");
                        if (num1 == "0"){
                            Toast.makeText(getApplicationContext(), "Please Add Emergency Contacts",
                                    Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            String num2 = sh.getString("num2", "");
                            find_Location(getApplicationContext());
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                            SharedPreferences sh1
                                    = getSharedPreferences("MySharedPref1",
                                    MODE_PRIVATE);
                            String msgtxt = sh1.getString("mymsg", "SOS ALERT! I am in danger!");

                            find_Location(getApplicationContext());


                            String uri = "https://maps.google.com/?q=" + String.valueOf(latitude) + "," + String.valueOf(longitude);

                            String msgf;
                            String Toastm;

                            if (String.valueOf(latitude) == "null"){
                                msgf = msgtxt;
                                Toastm = "Message sent without Location, try again for sending location data as well";
                            }
                            else {
                                msgf = msgtxt + "  Location: " + uri;
                                Toastm = "Message sent successfully";
                            }

                            SmsManager sms = SmsManager.getDefault();
                            sms.sendTextMessage(("+91" + num1), null, msgf, pi, null);
                            sms.sendTextMessage(("+91" + num2), null, msgf, pi, null);
                            pi.cancel();
                            Toast.makeText(getApplicationContext(), Toastm,
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                } else {

                    String[] prs = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.SEND_SMS};

                    ActivityCompat.requestPermissions(MainActivity.this,
                            prs, 1);
                }
            }

            @SuppressLint("MissingPermission")
            public void find_Location(Context con) {
                this.con = con;
                String location_context = Context.LOCATION_SERVICE;
                locationManager = (LocationManager) con.getSystemService(location_context);
                List<String> providers = locationManager.getProviders(true);
                for (String provider : providers) {
                    locationManager.requestLocationUpdates(provider, 1000, 0,
                            new LocationListener() {

                                public void onLocationChanged(Location location) {}

                                public void onProviderDisabled(String provider) {}

                                public void onProviderEnabled(String provider) {}

                                public void onStatusChanged(String provider, int status,
                                                            Bundle extras) {}
                            });
                    Location location = locationManager.getLastKnownLocation(provider);
                    if (location != null) {

                        latitude = String.valueOf(location.getLatitude());
                        longitude = String.valueOf(location.getLongitude());
                    }
                }
            }

            public boolean locationServicesEnabled (Context context){
                int mode =Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE,
                        Settings.Secure.LOCATION_MODE_OFF);
                final boolean enabled = (mode != android.provider.Settings.Secure.LOCATION_MODE_OFF);
                return enabled;
            }

            private void GPSOn() {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));


                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                final  AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

        });
        edtmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), edt.class);
                startActivity(intent);
            }
        });
    }

}