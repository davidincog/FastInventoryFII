package com.fastinventory.fastinventoryfi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InicioSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
    }
    public void inventario (View view){
        Intent invent = new Intent(InicioSesion.this, MainActivity.class);
    }
    public void regresar (View view){
        Intent regHome = new Intent(InicioSesion.this, Inicio1.class);
        startActivity(regHome);
    }
}