package com.fisi.unmsm.sistemaencuestaestudiantil.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.fisi.unmsm.sistemaencuestaestudiantil.R;

public class FinalizarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar);
    }


    @SuppressLint("NewApi")
    public void finalizar(View view){
        finishAffinity();
    }
}
