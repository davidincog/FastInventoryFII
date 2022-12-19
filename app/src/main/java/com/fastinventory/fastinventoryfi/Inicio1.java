package com.fastinventory.fastinventoryfi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Inicio1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio1);
    }
    public void iniciarS(View view) {
        Intent iniSesion = new Intent(Inicio1.this, InicioSesion.class);
        startActivity(iniSesion);
    }
}