package com.akaprod.root.latihan2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnInputData;
    Button btnShowData;
    ImageView imgBaru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInputData = (Button) findViewById(R.id.btnInputData);
        btnShowData = (Button) findViewById(R.id.btnShowData);
        imgBaru = (ImageView) findViewById(R.id.imgBaru);

        imgBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"gambar ditekan",Toast.LENGTH_SHORT).show();
            }
        });

        btnInputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent input = new Intent(getApplicationContext(), Input.class);
                startActivity(input);
            }
        });

        btnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent show = new Intent(getApplicationContext(), Show.class);
                startActivity(show);
            }
        });
    }
}
