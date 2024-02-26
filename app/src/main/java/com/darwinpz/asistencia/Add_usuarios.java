package com.darwinpz.asistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.darwinpz.asistencia.Objetos.Usuario;
import com.google.firebase.auth.FirebaseUser;

public class Add_usuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_usuarios);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnClickListener(v -> finish());

        EditText editText_correo = findViewById(R.id.editText_correo);
        EditText editText_clave = findViewById(R.id.editText_clave);
        EditText editText_nombre = findViewById(R.id.editText_nombre);
        Spinner spinner_rol = findViewById(R.id.spinner_rol);

        ArrayAdapter<CharSequence> adapter_rol  = ArrayAdapter.createFromResource(this,R.array.roles, android.R.layout.simple_spinner_item);
        adapter_rol.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_rol.setAdapter(adapter_rol);

        Button btn_add_user = findViewById(R.id.btn_add_user);


        btn_add_user.setOnClickListener(v -> {

            FirebaseUser firebaseUsercurrent = MainActivity.auth.getCurrentUser();

            String usuario = editText_correo.getText().toString().trim();
            String clave = editText_clave.getText().toString().trim();
            String nombre = editText_nombre.getText().toString().trim();
            String rol = spinner_rol.getSelectedItem().toString();

            if(!usuario.isEmpty() && !clave.isEmpty() && !nombre.isEmpty() && !rol.equalsIgnoreCase("Selecciona")){

                MainActivity.auth.createUserWithEmailAndPassword(usuario,clave).addOnCompleteListener(this,task -> {

                    if(task.isSuccessful()){

                        if(firebaseUsercurrent!= null){

                            Usuario user = new Usuario();
                            user.nombre = nombre;
                            user.correo = usuario;
                            user.rol = rol;
                            MainActivity.ctlUsuario.crear_usuario(MainActivity.auth.getUid(),user);
                            MainActivity.auth.updateCurrentUser(firebaseUsercurrent);
                            Toast.makeText(this,"Usuario creado correctamente", Toast.LENGTH_SHORT).show();

                        }

                    }

                }).addOnFailureListener(this, e -> {
                    Toast.makeText(this,"Error al crear el usuario", Toast.LENGTH_LONG).show();
                });


            }else{
                Toast.makeText(this, "Completa la información requerida", Toast.LENGTH_SHORT).show();
            }


        });


    }
}