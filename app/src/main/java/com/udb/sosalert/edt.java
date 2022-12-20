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

public class edt extends AppCompatActivity {

    private Button upd;
    private EditText msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edt);

        upd = findViewById(R.id.updatebtn);
        msg = findViewById(R.id.editText);

        SharedPreferences sh1
                = getSharedPreferences("MySharedPref1",
                MODE_PRIVATE);

        msg.setText(sh1.getString("mymsg","SOS ALERT! I am in danger!"));
        msg.setSelection(msg.getText().length());



        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sh1
                        = getSharedPreferences("MySharedPref1",
                        MODE_PRIVATE);

                SharedPreferences.Editor editor = sh1.edit();

                SharedPreferences settings = getApplicationContext().getSharedPreferences("PreferencesName", Context.MODE_PRIVATE);
                settings.edit().clear().commit();

                editor.putString("mymsg", msg.getText().toString());

                editor.apply();

                Toast.makeText(getApplicationContext(), "Message updated successfully!",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
