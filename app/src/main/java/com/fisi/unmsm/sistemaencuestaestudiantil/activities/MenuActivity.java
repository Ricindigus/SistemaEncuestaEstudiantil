package com.fisi.unmsm.sistemaencuestaestudiantil.activities;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fisi.unmsm.sistemaencuestaestudiantil.R;
import com.fisi.unmsm.sistemaencuestaestudiantil.adpaters.EncuestaAdapter;
import com.fisi.unmsm.sistemaencuestaestudiantil.pojos.Alumno;
import com.fisi.unmsm.sistemaencuestaestudiantil.pojos.Encuesta;
import com.fisi.unmsm.sistemaencuestaestudiantil.pojos.Login;
import com.fisi.unmsm.sistemaencuestaestudiantil.pojos.Profesor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MenuActivity extends AppCompatActivity {
    private static final String ALUMNOS_NODE = "Alumnos";
    private RecyclerView recyclerView;
    private EncuestaAdapter encuestaAdapter;
    private LinearLayoutManager linearLayoutManager;
    private TextView txtBarraTitulo;
    private FirebaseStorage firebaseStorage;
    private TextView txtCodigoAlumno;
    private TextView txtNombreAlumno;
    private Button btnCerrarSesion;
    private DatabaseReference databaseReference;
    private Alumno alumno;
    private FirebaseAuth firebaseAuth;
    private CircleImageView circleImageView;
    private FirebaseAuth.AuthStateListener authStateListener;
    private static final String TAG = "MenuActivity";
    private String codigoAlumno;
    private ArrayList<Encuesta> encuestas;
    public static Activity actividad;
    private int disponibles = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        showToolbar("ENCUESTA ESTUDIANTIL 2017");

        actividad = this;

        Bundle datos = this.getIntent().getExtras();
        codigoAlumno = datos.getString("codigoPersona");
        encuestas = new ArrayList<Encuesta>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_cursos);
        btnCerrarSesion = (Button) findViewById(R.id.btnCerrarSesionAlumno);
        txtNombreAlumno = (TextView) findViewById(R.id.txtNombreAlumno);
        txtCodigoAlumno = (TextView) findViewById(R.id.txtCodigoAlumno);
        circleImageView = (CircleImageView) findViewById(R.id.profile_image);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        inicializar();
//        iniciarDatos();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageRef = firebaseStorage.getReferenceFromUrl("gs://encuesta-estudiantil.appspot.com/");
        encuestaAdapter = new EncuestaAdapter(encuestas, new EncuestaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final CardView rootView = (CardView) view;
                int color1 = ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryLight);
                int color2 = ContextCompat.getColor(getApplicationContext(),R.color.icons);
                ValueAnimator valueAnimator = new ValueAnimator().ofObject(new ArgbEvaluator(),color1,color2);
                valueAnimator.setDuration(800);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        rootView.setBackgroundColor((int) valueAnimator.getAnimatedValue());
                    }
                });
                valueAnimator.start();

                Intent intent = new Intent(getApplicationContext(), EncuestaActivity.class);
                intent.putExtra("nombreCurso",encuestas.get(position).getNombreCurso());
                intent.putExtra("tipoCurso",encuestas.get(position).getTipoCurso());
                intent.putExtra("nomProfesor", encuestas.get(position).getProfesor().getNombre() + " "
                        + encuestas.get(position).getProfesor().getApellido());
                intent.putExtra("codProfesor",encuestas.get(position).getProfesor().getCodigo());
                intent.putExtra("codAlumno",codigoAlumno);
                intent.putExtra("posicion",position);
                intent.putExtra("disponibles",disponibles);

                startActivity(intent);
            }
        });
        recyclerView.setAdapter(encuestaAdapter);

        databaseReference.child(ALUMNOS_NODE).child(codigoAlumno).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                encuestas.clear();
                alumno = dataSnapshot.getValue(Alumno.class);
                txtCodigoAlumno.setText(alumno.getCodigo());
                txtNombreAlumno.setText(alumno.getNombres() + " " + alumno.getApellidos());
                int acumulador = 0;
                for (Encuesta e:alumno.getEncuestas()) {
                    encuestas.add(e);
                    acumulador = acumulador + e.getDisponible();
                }
                disponibles = acumulador;
                encuestaAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarSesion();
            }
        });


    }

    public void showToolbar(String titulo){
        txtBarraTitulo = (TextView) findViewById(R.id.txtBarraTitulo);
        txtBarraTitulo.setText(titulo);
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
