package com.darwinpz.asistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.darwinpz.asistencia.Objetos.Usuario;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnClickListener(v -> finish());

        EditText editText_correo = findViewById(R.id.editText_correo);
        EditText editText_clave = findViewById(R.id.editText_clave);
        EditText editText_nombre = findViewById(R.id.editText_nombre);

        Button btn_registrarme = findViewById(R.id.btn_registrarme);


        btn_registrarme.setOnClickListener(v -> {

            String usuario = editText_correo.getText().toString().trim();
            String clave = editText_clave.getText().toString().trim();
            String nombre = editText_nombre.getText().toString().trim();

            if(!usuario.isEmpty() && !clave.isEmpty() && !nombre.isEmpty()){

                MainActivity.auth.createUserWithEmailAndPassword(usuario,clave).addOnCompleteListener(this,task -> {

                   if(task.isSuccessful()){

                       if(MainActivity.auth.getCurrentUser()!= null){

                           Usuario user = new Usuario();
                           user.nombre = nombre;
                           user.correo = usuario;
                           user.rol = "Cliente";
                           MainActivity.ctlUsuario.crear_usuario(MainActivity.auth.getUid(),user);
                           MainActivity.auth.signOut();

                           Toast.makeText(this,"Usuario creado correctamente", Toast.LENGTH_SHORT).show();

                       }

                   }

                }).addOnFailureListener(this, e -> {
                    Toast.makeText(this,"Error al crear el usuario", Toast.LENGTH_LONG).show();
                });


            }else {
                Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show();
            }


        });


    }
}