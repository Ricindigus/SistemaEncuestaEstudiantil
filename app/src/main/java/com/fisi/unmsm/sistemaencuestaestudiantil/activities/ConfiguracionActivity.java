package com.fisi.unmsm.sistemaencuestaestudiantil.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fisi.unmsm.sistemaencuestaestudiantil.R;
import com.fisi.unmsm.sistemaencuestaestudiantil.pojos.Alumno;
import com.fisi.unmsm.sistemaencuestaestudiantil.pojos.Encuesta;
import com.fisi.unmsm.sistemaencuestaestudiantil.pojos.Login;
import com.fisi.unmsm.sistemaencuestaestudiantil.pojos.Profesor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ConfiguracionActivity extends AppCompatActivity {
    private static final String LOGIN_NODE = "Login";
    private static final String ALUMNO_NODE = "Alumnos";

    private Button btnCargarDatos;
    private DatabaseReference databaseReference;
    private ArrayList<Login> logins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        btnCargarDatos = (Button) findViewById(R.id.btnCargarDatos);
        
        databaseReference = FirebaseDatabase.getInstance().getReference();

        btnCargarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                cargarUsuarios();
                cargarAlumnos();
            }
        });

    }

    public void cargarUsuarios(){
        Login login = new Login("05200100",1,0);
        databaseReference.child(LOGIN_NODE).child(login.getCodigoUsuario()).setValue(login);
        login = new Login("06200100",1,0);
        databaseReference.child(LOGIN_NODE).child(login.getCodigoUsuario()).setValue(login);
        login = new Login("07200100",1,0);
        databaseReference.child(LOGIN_NODE).child(login.getCodigoUsuario()).setValue(login);
        login = new Login("08200100",1,0);
        databaseReference.child(LOGIN_NODE).child(login.getCodigoUsuario()).setValue(login);
        login = new Login("09200100",1,0);
        databaseReference.child(LOGIN_NODE).child(login.getCodigoUsuario()).setValue(login);
        login = new Login("10200100",1,0);
        databaseReference.child(LOGIN_NODE).child(login.getCodigoUsuario()).setValue(login);
        login = new Login("11200100",1,0);
        databaseReference.child(LOGIN_NODE).child(login.getCodigoUsuario()).setValue(login);
        login = new Login("12200100",1,0);
        databaseReference.child(LOGIN_NODE).child(login.getCodigoUsuario()).setValue(login);
        login = new Login("13200100",1,0);
        databaseReference.child(LOGIN_NODE).child(login.getCodigoUsuario()).setValue(login);
        login = new Login("14200100",1,0);
        databaseReference.child(LOGIN_NODE).child(login.getCodigoUsuario()).setValue(login);
        login = new Login("15200100",1,0);
        databaseReference.child(LOGIN_NODE).child(login.getCodigoUsuario()).setValue(login);
        login = new Login("16200100",1,0);
        databaseReference.child(LOGIN_NODE).child(login.getCodigoUsuario()).setValue(login);
        login = new Login("17200100",1,0);
        databaseReference.child(LOGIN_NODE).child(login.getCodigoUsuario()).setValue(login);

        login = new Login("dmorales",2,0);
        databaseReference.child(LOGIN_NODE).child(login.getCodigoUsuario()).setValue(login);
        login = new Login("jsanchez",2,0);
        databaseReference.child(LOGIN_NODE).child(login.getCodigoUsuario()).setValue(login);
        login = new Login("mcarranza",2,0);
        databaseReference.child(LOGIN_NODE).child(login.getCodigoUsuario()).setValue(login);
        login = new Login("pperez",2,0);
        databaseReference.child(LOGIN_NODE).child(login.getCodigoUsuario()).setValue(login);
        login = new Login("jmeza",2,0);
        databaseReference.child(LOGIN_NODE).child(login.getCodigoUsuario()).setValue(login);

        login = new Login("admin1",3,0);
        databaseReference.child(LOGIN_NODE).child(login.getCodigoUsuario()).setValue(login);
        login = new Login("admin2",3,0);
        databaseReference.child(LOGIN_NODE).child(login.getCodigoUsuario()).setValue(login);
    }

    public void cargarAlumnos() {
        Profesor profesor1 = new Profesor("dmorales","Denis Ricardo","Morales Retamozo");
        Profesor profesor2 = new Profesor("jsanchez","Junior Stronger","Sanchez Chuqui");
        Profesor profesor3 = new Profesor("mcarranza","Marelynn Stephanie","Carranza Alarcon");
        Profesor profesor4 = new Profesor("pperez","Paolo","Perez Tito");
        Profesor profesor5 = new Profesor("jmeza","Jorge Luis","Meza Uscamaita");
        Profesor profesor6 = new Profesor("cluza","Cesar","Luza Montero");
        Profesor profesor7 = new Profesor("jdiaz","Jorge","Diaz Muñante");
        Profesor profesor8 = new Profesor("smoquillaza","Santiago","Moquillaza Henriquez");
        Profesor profesor9 = new Profesor("jpiedra","Jose","Piedra Isusqui");
        Profesor profesor10 = new Profesor("zmamani","Zoraida","Mamani Rodriguez");

        ArrayList<Encuesta> encuestas = new ArrayList<Encuesta>();
        encuestas.clear();
        encuestas.add(new Encuesta("Algorítmica I","Teoría",profesor1,1));
        encuestas.add(new Encuesta("Matemática Básica I","Teoría",profesor2,1));
        encuestas.add(new Encuesta("Matemática Básica I","Práctica",profesor2,1));
        encuestas.add(new Encuesta("Calculo I","Teoría",profesor2,1));
        encuestas.add(new Encuesta("Calculo I","Práctica",profesor2,1));
        encuestas.add(new Encuesta("Teoría de Sistemas","Teoría",profesor3,1));
        encuestas.add(new Encuesta("Taller de Técnicas de Estudio","Teoría",profesor4,1));
        encuestas.add(new Encuesta("Computación e Informática","Teoría",profesor5,1));
        encuestas.add(new Encuesta("Computación e Informática","Laboratorio",profesor5,1));
        Alumno alumno = new Alumno("13200100","Renzo Jose","Huaman Torres",encuestas);
        databaseReference.child(ALUMNO_NODE).child(alumno.getCodigo()).setValue(alumno);

        encuestas.clear();
        encuestas.add(new Encuesta("Algorítmica II","Teoría",profesor6,1));
        encuestas.add(new Encuesta("Algorítmica II","Práctica",profesor6,1));
        encuestas.add(new Encuesta("Algorítmica II","Laboratorio",profesor7,1));
        encuestas.add(new Encuesta("Matemática Básica II","Teoría",profesor10,1));
        encuestas.add(new Encuesta("Matemática Básica II","Práctica",profesor4,1));
        encuestas.add(new Encuesta("Calculo II","Teoría",profesor8,1));
        encuestas.add(new Encuesta("Calculo II","Práctica",profesor8,1));
        encuestas.add(new Encuesta("Estructura de Datos","Teoría",profesor7,1));
        encuestas.add(new Encuesta("Estructura de Datos","Práctica",profesor7,1));
        encuestas.add(new Encuesta("Fisica General","Teoría",profesor1,1));
        encuestas.add(new Encuesta("Fisica General","Práctica",profesor1,1));
        encuestas.add(new Encuesta("Economia","Teoría",profesor9,1));
        alumno = new Alumno("12200100","Hugo Kenyo","Arbieto Ramirez",encuestas);
        databaseReference.child(ALUMNO_NODE).child(alumno.getCodigo()).setValue(alumno);

        encuestas.clear();
        encuestas.add(new Encuesta("Algorítmica III","Teoría",profesor6,1));
        encuestas.add(new Encuesta("Algorítmica III","Práctica",profesor6,1));
        encuestas.add(new Encuesta("Algorítmica III","Laboratorio",profesor6,1));
        encuestas.add(new Encuesta("Matemática Discreta","Teoría",profesor8,1));
        encuestas.add(new Encuesta("Matemática Discreta","Práctica",profesor8,1));
        encuestas.add(new Encuesta("Estadistica I","Teoría",profesor10,1));
        encuestas.add(new Encuesta("Estadistica I","Práctica",profesor10,1));
        encuestas.add(new Encuesta("Diseño Grafico","Teoría",profesor9,1));
        encuestas.add(new Encuesta("Diseño Grafico","Laboratorio",profesor9,1));
        encuestas.add(new Encuesta("Electromagnetismo","Teoría",profesor7,1));
        encuestas.add(new Encuesta("Electromagnetismo","Práctica",profesor7,1));
        encuestas.add(new Encuesta("Organización y Administración","Teoría",profesor3,1));
        alumno = new Alumno("11200100","Bernabe Rubén","Flores Penadillo",encuestas);
        databaseReference.child(ALUMNO_NODE).child(alumno.getCodigo()).setValue(alumno);

    }
}
