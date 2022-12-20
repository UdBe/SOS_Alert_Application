package com.udb.sosalert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addcontacts extends AppCompatActivity {

    private EditText cont1 ;
    private EditText num1;
    private EditText cont2 ;
    private EditText num2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontacts);

        cont1 = findViewById(R.id.name1);
        num1 = findViewById(R.id.num1);
        cont2 = findViewById(R.id.name2);
        num2 = findViewById(R.id.num2);

        Button save = findViewById(R.id.Save);

        SharedPreferences sh
                = getSharedPreferences("MySharedPref",
                MODE_PRIVATE);

        SharedPreferences.Editor editor = sh.edit();

        String aname1 = sh.getString("name1","x");
        String aname2 = sh.getString("name2","x");
        String anum1 = sh.getString("num1","x");
        String anum2 = sh.getString("num2","x");

        if (aname1 != "x"){
            cont1.setText(aname1);
            cont1.setSelection(cont1.getText().length());

        }
        if (aname2 != "x"){
            cont2.setText(aname2);
            cont2.setSelection(cont2.getText().length());

        }
        if (anum1 != "x"){
            num1.setText(anum1);
            num1.setSelection(num1.getText().length());

        }
        if (anum2 != "x"){
            num2.setText(anum2);
            num2.setSelection(num2.getText().length());

        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( cont1.getText().toString() == "" ) {
                    Toast toast = Toast.makeText(getApplicationContext()
                            , " Please enter contact name and phone number",
                            Toast.LENGTH_LONG );
                    toast.show();
                }
                else if ( num1.getText().toString() == "" ) {
                    Toast toast = Toast.makeText(getApplicationContext()
                            , " Please enter contact name and phone number",
                            Toast.LENGTH_LONG );
                    toast.show();
                }
                else if ( num1.getText().toString().length() != 10) {
                    Toast toast = Toast.makeText(getApplicationContext()
                            , " Please enter correct mobile number",
                            Toast.LENGTH_LONG);
                    toast.show();
                }
                else if ( cont2.getText().toString() == "" ) {
                    Toast toast = Toast.makeText(getApplicationContext()
                            , " Please enter contact name and phone number",
                            Toast.LENGTH_LONG );
                    toast.show();
                }
                else if ( num2.getText().toString() == "" ) {
                    Toast toast = Toast.makeText(getApplicationContext()
                            , " Please enter contact name and phone number",
                            Toast.LENGTH_LONG );
                    toast.show();
                }
                else if ( num2.getText().toString().length() != 10) {
                    Toast toast = Toast.makeText(getApplicationContext()
                            , " Please enter correct mobile number",
                            Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    SharedPreferences sh
                            = getSharedPreferences("MySharedPref",
                            MODE_PRIVATE);

                    SharedPreferences.Editor editor = sh.edit();

                    SharedPreferences settings = getApplicationContext().getSharedPreferences("PreferencesName", Context.MODE_PRIVATE);
                    settings.edit().clear().apply();

                    String a  = num1.getText().toString();
                    String b  = num2.getText().toString();


                    editor.putString("name1", cont1.getText().toString());
                    editor.putString("name2", cont2.getText().toString());
                    editor.putString("num1",a);
                    editor.putString("num2",b);

                    editor.apply();

                    Toast toast = Toast.makeText(getApplicationContext()
                            , " Contacts saved successfully",
                            Toast.LENGTH_LONG);
                    toast.show();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                }
            }
        });
    };
}

