package com.fisi.unmsm.sistemaencuestaestudiantil.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fisi.unmsm.sistemaencuestaestudiantil.R;
import com.fisi.unmsm.sistemaencuestaestudiantil.pojos.Alumno;
import com.fisi.unmsm.sistemaencuestaestudiantil.pojos.Encuesta;
import com.fisi.unmsm.sistemaencuestaestudiantil.pojos.Login;
import com.fisi.unmsm.sistemaencuestaestudiantil.pojos.Profesor;
import com.fisi.unmsm.sistemaencuestaestudiantil.pojos.Respuesta;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EncuestaActivity extends AppCompatActivity {
    private static final String RESPUESTAS_NODE = "Respuestas";
    private static final String ALUMNOS_NODE = "Alumnos";
    private Toolbar toolbar;
    private int idCurso;
    private DatabaseReference databaseReference;
    private String nombreCurso;
    private String tipoCurso;
    private Profesor profesor;
    private TextView txtNombreCurso;
    private TextView txtTipoCurso;
    private TextView txtNombreProfesor;
    private String nombreProfesor;
    private String codigoProfesor;
    private String codigoAlumno;
    private Button btnGuardarEncuesta;
    private double respuestas[];
    private int posicion;
    private RadioGroup rg1;
    private RadioGroup rg2;
    private RadioGroup rg3;
    private RadioGroup rg4;
    private RadioGroup rg5;
    private RadioGroup rg6;
    private RadioGroup rg7;
    private RadioGroup rg8;
    private RadioGroup rg9;
    private RadioGroup rg10;
    private RadioGroup rg11;
    private int encuestasFaltantes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);
        txtNombreCurso = (TextView) findViewById(R.id.encuesta_txtCurso);
        txtTipoCurso = (TextView) findViewById(R.id.encuesta_txtTipo);
        txtNombreProfesor = (TextView) findViewById(R.id.encuesta_txtProfesor);
        btnGuardarEncuesta = (Button) findViewById(R.id.btnGuardarEncuesta);
        rg1 = (RadioGroup) findViewById(R.id.rg_conocimiento_p1);
        rg2 = (RadioGroup) findViewById(R.id.rg_conocimiento_p2);
        rg3 = (RadioGroup) findViewById(R.id.rg_conocimiento_p3);
        rg4 = (RadioGroup) findViewById(R.id.rg_conocimiento_p4);
        rg5 = (RadioGroup) findViewById(R.id.rg_asistencia_p1);
        rg6 = (RadioGroup) findViewById(R.id.rg_asistencia_p2);
        rg7 = (RadioGroup) findViewById(R.id.rg_etica_p1);
        rg8 = (RadioGroup) findViewById(R.id.rg_capacidad_p1);
        rg9 = (RadioGroup) findViewById(R.id.rg_capacidad_p2);
        rg10 = (RadioGroup) findViewById(R.id.rg_capacidad_p3);
        rg11 = (RadioGroup) findViewById(R.id.rg_cumplimiento_p1);

        respuestas = new double[11];
        for (int i = 0; i < respuestas.length; i++) {
            respuestas[i] = 0;
        }

        showToolbar("Algoritmica I");
        Bundle datos = this.getIntent().getExtras();
        nombreCurso = datos.getString("nombreCurso");
        tipoCurso = datos.getString("tipoCurso");
        nombreProfesor = datos.getString("nomProfesor");
        codigoProfesor = datos.getString("codProfesor");
        codigoAlumno = datos.getString("codAlumno");
        posicion = datos.getInt("posicion");
        encuestasFaltantes = datos.getInt("disponibles");
        txtNombreCurso.setText(nombreCurso);
        txtTipoCurso.setText(tipoCurso);
        txtNombreProfesor.setText(nombreProfesor);

        databaseReference = FirebaseDatabase.getInstance().getReference();

//        databaseReference.child(ALUMNOS_NODE).child(codigoAlumno)
//                .child("encuestas").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                int acumulador = 0;
//                if(dataSnapshot.exists()){
//                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//                        Encuesta encuesta = snapshot.getValue(Encuesta.class);
//                        acumulador = acumulador + encuesta.getDisponible();
//                    }
//                    encuestasFaltantes = acumulador;
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch(i){
                    case R.id.rb_conocimiento_p1_025:
                        respuestas[0] = 0.25;break;
                    case R.id.rb_conocimiento_p1_050:
                        respuestas[0] = 0.50;break;
                    case R.id.rb_conocimiento_p1_075:
                        respuestas[0] = 0.75;break;
                    case R.id.rb_conocimiento_p1_100:
                        respuestas[0] = 1.00;break;
                }
            }
        });
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch(i){
                    case R.id.rb_conocimiento_p2_025:
                        respuestas[1] = 0.25;break;
                    case R.id.rb_conocimiento_p2_050:
                        respuestas[1] = 0.50;break;
                    case R.id.rb_conocimiento_p2_075:
                        respuestas[1] = 0.75;break;
                    case R.id.rb_conocimiento_p2_100:
                        respuestas[1] = 1.00;break;
                }
            }
        });
        rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch(i){
                    case R.id.rb_conocimiento_p3_025:
                        respuestas[2] = 0.25;break;
                    case R.id.rb_conocimiento_p3_050:
                        respuestas[2] = 0.50;break;
                    case R.id.rb_conocimiento_p3_075:
                        respuestas[2] = 0.75;break;
                    case R.id.rb_conocimiento_p3_100:
                        respuestas[2] = 1.00;break;
                }
            }
        });
        rg4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch(i){
                    case R.id.rb_conocimiento_p4_025:
                        respuestas[3] = 0.25;break;
                    case R.id.rb_conocimiento_p4_050:
                        respuestas[3] = 0.50;break;
                    case R.id.rb_conocimiento_p4_075:
                        respuestas[3] = 0.75;break;
                    case R.id.rb_conocimiento_p4_100:
                        respuestas[3] = 1.00;break;
                }
            }
        });
        rg5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch(i){
                    case R.id.rb_asistencia_p1_025:
                        respuestas[4] = 0.25;break;
                    case R.id.rb_asistencia_p1_050:
                        respuestas[4] = 0.50;break;
                    case R.id.rb_asistencia_p1_075:
                        respuestas[4] = 0.75;break;
                    case R.id.rb_asistencia_p1_100:
                        respuestas[4] = 1.00;break;
                }
            }
        });
        rg6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch(i){
                    case R.id.rb_asistencia_p2_025:
                        respuestas[5] = 0.25;break;
                    case R.id.rb_asistencia_p2_050:
                        respuestas[5] = 0.50;break;
                    case R.id.rb_asistencia_p2_075:
                        respuestas[5] = 0.75;break;
                    case R.id.rb_asistencia_p2_100:
                        respuestas[5] = 1.00;break;
                }
            }
        });
        rg7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch(i){
                    case R.id.rb_etica_p1_025:
                        respuestas[6] = 0.25;break;
                    case R.id.rb_etica_p1_050:
                        respuestas[6] = 0.50;break;
                    case R.id.rb_etica_p1_075:
                        respuestas[6] = 0.75;break;
                    case R.id.rb_etica_p1_100:
                        respuestas[6] = 1.00;break;
                }
            }
        });
        rg8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch(i){
                    case R.id.rb_capacidad_p1_025:
                        respuestas[7] = 0.25;break;
                    case R.id.rb_capacidad_p1_050:
                        respuestas[7] = 0.50;break;
                    case R.id.rb_capacidad_p1_075:
                        respuestas[7] = 0.75;break;
                    case R.id.rb_capacidad_p1_100:
                        respuestas[7] = 1.00;break;
                }
            }
        });
        rg9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch(i){
                    case R.id.rb_capacidad_p2_025:
                        respuestas[8] = 0.25;break;
                    case R.id.rb_capacidad_p2_050:
                        respuestas[8] = 0.50;break;
                    case R.id.rb_capacidad_p2_075:
                        respuestas[8] = 0.75;break;
                    case R.id.rb_capacidad_p2_100:
                        respuestas[8] = 1.00;break;
                }
            }
        });
        rg10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch(i){
                    case R.id.rb_capacidad_p3_025:
                        respuestas[9] = 0.25;break;
                    case R.id.rb_capacidad_p3_050:
                        respuestas[9] = 0.50;break;
                    case R.id.rb_capacidad_p3_075:
                        respuestas[9] = 0.75;break;
                    case R.id.rb_capacidad_p3_100:
                        respuestas[9] = 1.00;break;
                }
            }
        });
        rg11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch(i){
                    case R.id.rb_cumplimiento_p1_025:
                        respuestas[10] = 0.25;break;
                    case R.id.rb_cumplimiento_p1_050:
                        respuestas[10] = 0.50;break;
                    case R.id.rb_cumplimiento_p1_075:
                        respuestas[10] = 0.75;break;
                    case R.id.rb_cumplimiento_p1_100:
                        respuestas[10] = 1.00;break;
                }
            }
        });

        btnGuardarEncuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarEncuesta();
            }
        });
    }

    public void guardarEncuesta(){
        if(!hayRespuestasVacias()){
            for (int i = 0; i <respuestas.length ; i++) {
                Respuesta respuesta = new Respuesta(codigoProfesor,nombreProfesor,nombreCurso,tipoCurso,i+1,respuestas[i]);
                databaseReference.child(RESPUESTAS_NODE).child(codigoProfesor).child(databaseReference.push().getKey()).setValue(respuesta);
            }
            databaseReference.child("Alumnos").child(codigoAlumno).child("encuestas").child(""+posicion).child("disponible").setValue(0);
            MenuActivity.actividad.finish();
            Intent intent;
            encuestasFaltantes--;
            if(encuestasFaltantes > 0){
                intent = new Intent(getApplicationContext(),MenuActivity.class);
                intent.putExtra("codigoPersona",codigoAlumno);
            }else{
                databaseReference.child("Login").child(codigoAlumno).child("participo").setValue(1);
                intent = new Intent(getApplicationContext(),FinalizarActivity.class);
            }
            startActivity(intent);
            finish();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Debe contestar todas las preguntas")
                    .setTitle("Aviso")
                    .setCancelable(false)
                    .setPositiveButton("Aceptar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        }

    }

    public void showToolbar(String title){
        toolbar = (Toolbar) findViewById(R.id.toolbar_encuesta);
        setSupportActionBar(toolbar);
        toolbar.setTitle(title);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public boolean hayRespuestasVacias(){
        boolean incorrecto = false;
        int i=0;
        while(i < respuestas.length && !incorrecto){
            if(respuestas[i] == 0) incorrecto = true;
            i++;
        }
        return incorrecto;
    }

}
