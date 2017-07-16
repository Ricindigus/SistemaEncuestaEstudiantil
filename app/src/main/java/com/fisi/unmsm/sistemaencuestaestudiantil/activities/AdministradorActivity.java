package com.fisi.unmsm.sistemaencuestaestudiantil.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fisi.unmsm.sistemaencuestaestudiantil.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdministradorActivity extends AppCompatActivity {
    CardView cardViewConfiguracion;
    CardView cardViewReportes;
    Button btnCerrarSesion;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private static final String TAG = "AdministradorActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

        cardViewConfiguracion = (CardView) findViewById(R.id.cardview_configuracion);
        cardViewReportes = (CardView) findViewById(R.id.cardview_reportes);
        btnCerrarSesion = (Button) findViewById(R.id.btnCerrarSesionAdmin);
        inicializar();

        cardViewConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ConfiguracionActivity.class);
                startActivity(intent);
            }
        });
        cardViewReportes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ReportesActivity.class);
                startActivity(intent);
            }
        });
        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarSesion();
            }
        });
    }

    public void inicializar(){
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                authStateListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        if(firebaseUser != null){
                            Log.w(TAG, "onAuthStateChanged - Logueado" + firebaseUser.getUid());
                            Log.w(TAG, "onAuthStateChanged - Logueado" + firebaseUser.getEmail());
                        }else{
                            Log.w(TAG, "onAuthStateChanged - Cerro Session");
                        }
                    }
                };
            }
        };
    }
    private void cerrarSesion(){
        firebaseAuth.signOut();
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }
}
