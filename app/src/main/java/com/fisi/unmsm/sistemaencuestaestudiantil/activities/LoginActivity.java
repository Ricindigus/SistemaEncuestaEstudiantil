package com.fisi.unmsm.sistemaencuestaestudiantil.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fisi.unmsm.sistemaencuestaestudiantil.R;
import com.fisi.unmsm.sistemaencuestaestudiantil.pojos.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText txtUsuario;
    private TextInputEditText txtPassword;
    private Button btnIngresar;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private static final String TAG = "LoginActivity";
    private static final String LOGIN_NODE = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsuario = (TextInputEditText) findViewById(R.id.txtUsuario);
        txtPassword = (TextInputEditText) findViewById(R.id.txtPassword);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);

        inicializar();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = txtUsuario.getText().toString()+"@unmsm.edu.pe";
                iniciarSesion(usuario, txtPassword.getText().toString());
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

    public void iniciarSesion(String user, String pass){
        firebaseAuth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(! task.isSuccessful())
                    Toast.makeText(LoginActivity.this, "Problemas para inciar sesion", Toast.LENGTH_SHORT).show();
                else{
                    databaseReference.child(LOGIN_NODE).child(txtUsuario.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Login l = dataSnapshot.getValue(Login.class);

                            if(l.getParticipo() == 1){
                                Toast.makeText(LoginActivity.this, "Usted ya participo en la encuesta", Toast.LENGTH_SHORT).show();
                            }else{
                                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                                if(l.getPermisoUsuario() == 2) intent = new Intent(getApplicationContext(),ReportesActivity.class);
                                if (l.getPermisoUsuario() == 3) intent = new Intent(getApplicationContext(),AdministradorActivity.class);
                                intent.putExtra("codigoPersona",txtUsuario.getText().toString());
                                startActivity(intent);
                                finish();
                            }

                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }
}
