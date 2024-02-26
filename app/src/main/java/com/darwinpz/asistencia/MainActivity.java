package com.darwinpz.asistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.darwinpz.asistencia.Controladores.Ctl_usuario;
import com.darwinpz.asistencia.Controladores.Interfaces;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    public static FirebaseAuth auth;
    public static FirebaseUser firebaseUser;
    public static Ctl_usuario ctlUsuario;
    DatabaseReference databaseReference;
    FirebaseDatabase DB = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText_correo = findViewById(R.id.editText_correo);
        EditText editText_clave = findViewById(R.id.editText_clave);
        Button btn_ingresar = findViewById(R.id.btn_ingresar);
        Button btn_registro = findViewById(R.id.btn_registro);

        auth = FirebaseAuth.getInstance();

        databaseReference = DB.getReference();

        ctlUsuario = new Ctl_usuario(databaseReference);

        btn_ingresar.setOnClickListener(v -> {

            String usuario = editText_correo.getText().toString().trim();
            String clave = editText_clave.getText().toString().trim();

            if(!usuario.isEmpty() && !clave.isEmpty()){

                auth.signInWithEmailAndPassword(usuario,clave).addOnCompleteListener(this,task -> {

                    if(task.isSuccessful()){

                        firebaseUser = auth.getCurrentUser();

                        if(firebaseUser!=null){
                            startActivity(new Intent(this, Principal.class));
                        }

                    }

                }).addOnFailureListener(this,e -> {
                    Toast.makeText(this,"Error al iniciar sesión",Toast.LENGTH_LONG).show();
                });

            }else{
                Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show();
            }

        });

        btn_registro.setOnClickListener(v -> {

            startActivity(new Intent(this, Registro.class));

        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        if(auth.getCurrentUser()!=null){
            startActivity(new Intent(this, Principal.class));
        }

    }
}