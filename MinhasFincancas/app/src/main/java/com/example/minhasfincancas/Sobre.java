package com.example.minhasfincancas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Sobre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        setTitle(R.string.principal_activity_sobre);
    }
}