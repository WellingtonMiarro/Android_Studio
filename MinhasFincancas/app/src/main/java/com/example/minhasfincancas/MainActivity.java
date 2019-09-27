package com.example.minhasfincancas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);
    }
    public void calcularPrestacoes(View view) {
        startActivity(new Intent(MainActivity.this, Calcular_Prestacoes.class));
    }
    public void valorFuturo(View view) {
        startActivity(new Intent(MainActivity.this, Valor_Futuro.class));
    }

    public void sobre(View view) {
        startActivity(new Intent(MainActivity.this, Sobre.class));
    }
}
