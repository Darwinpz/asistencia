package com.darwinpz.asistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.darwinpz.asistencia.Objetos.Usuario;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Det_usuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_det_usuario);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnClickListener(v -> finish());

        String uid = Objects.requireNonNull(getIntent().getExtras()).getString("uid","");

        TextView txt_correo = findViewById(R.id.txt_correo);
        EditText editText_telefono = findViewById(R.id.editText_telefono);
        EditText editText_direccion = findViewById(R.id.editText_direccion);
        EditText editText_nombre = findViewById(R.id.editText_nombre);
        Button btn_actualizar = findViewById(R.id.btn_actualizar);
        Button btn_desactivar = findViewById(R.id.btn_desactivar);

        Spinner spinner_rol = findViewById(R.id.spinner_rol);

        ArrayAdapter<CharSequence> adapter_rol  = ArrayAdapter.createFromResource(this,R.array.roles, android.R.layout.simple_spinner_item);
        adapter_rol.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_rol.setAdapter(adapter_rol);

        if(!uid.isEmpty()){

            MainActivity.ctlUsuario.Obtener_perfil(uid,user -> {

                editText_telefono.setText(user.telefono);
                editText_direccion.setText(user.direccion);
                editText_nombre.setText(user.nombre);
                txt_correo.setText(user.correo);

                int spinnerposition = adapter_rol.getPosition(user.rol);
                if(spinnerposition!=-1){
                    spinner_rol.setSelection(spinnerposition);
                }

            });

            btn_actualizar.setOnClickListener(v -> {

                String nombre = editText_nombre.getText().toString().trim();
                String telefono = editText_telefono.getText().toString().trim();
                String direccion = editText_direccion.getText().toString().trim();
                String rol = spinner_rol.getSelectedItem().toString();

                if(!nombre.isEmpty() && !rol.equalsIgnoreCase("Selecciona")){
                    Usuario user = new Usuario();
                    user.nombre = nombre;
                    user.telefono = telefono;
                    user.direccion = direccion;
                    user.rol = rol;
                    MainActivity.ctlUsuario.actualizar_usuario(uid,user);
                    Toast.makeText(this,"Usuario actualizado",Toast.LENGTH_SHORT).show();
                }else{

                    Toast.makeText(this,"Completa los campos requeridos",Toast.LENGTH_SHORT).show();
                }

            });

            btn_desactivar.setOnClickListener(v -> {

                MainActivity.ctlUsuario.desactivar_usuario(uid);

            });

        }

    }
}