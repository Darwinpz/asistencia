package com.darwinpz.asistencia.Fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.darwinpz.asistencia.Controladores.Interfaces;
import com.darwinpz.asistencia.MainActivity;
import com.darwinpz.asistencia.Objetos.Usuario;
import com.darwinpz.asistencia.Principal;
import com.darwinpz.asistencia.R;
import com.google.firebase.auth.FirebaseUser;

public class Fragment_Perfil extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_perfil, container,false);

        TextView txt_correo = vista.findViewById(R.id.txt_correo);
        TextView txt_rol = vista.findViewById(R.id.txt_rol);
        EditText editText_telefono = vista.findViewById(R.id.editText_telefono);
        EditText editText_direccion = vista.findViewById(R.id.editText_direccion);
        EditText editText_nombre = vista.findViewById(R.id.editText_nombre);

        Button btn_salir = vista.findViewById(R.id.btn_salir);
        Button btn_actualizar = vista.findViewById(R.id.btn_actualizar);

        FirebaseUser firebaseUser = MainActivity.auth.getCurrentUser();

        if(firebaseUser!=null){

            txt_correo.setText(firebaseUser.getEmail());

            txt_rol.setText(Principal.rol_usuario);

            MainActivity.ctlUsuario.Obtener_perfil(firebaseUser.getUid(), user -> {

                editText_nombre.setText(user.nombre);
                editText_telefono.setText(user.telefono);
                editText_direccion.setText(user.direccion);

            });


            btn_salir.setOnClickListener(v -> {

                MainActivity.auth.signOut();
                requireActivity().finish();
                startActivity(new Intent(vista.getContext(),MainActivity.class));

            });

            btn_actualizar.setOnClickListener(v -> {

                String nombre = editText_nombre.getText().toString().trim();
                String telefono = editText_telefono.getText().toString().trim();
                String direccion = editText_direccion.getText().toString().trim();

                if(!nombre.isEmpty()){
                    Usuario user = new Usuario();
                    user.nombre = nombre;
                    user.telefono = telefono;
                    user.direccion = direccion;
                    MainActivity.ctlUsuario.actualizar_usuario(firebaseUser.getUid(),user);
                    Toast.makeText(vista.getContext(),"Usuario actualizado",Toast.LENGTH_SHORT).show();
                }else{

                    Toast.makeText(vista.getContext(),"Completa los campos requeridos",Toast.LENGTH_SHORT).show();
                }
            });


        }

        return vista;

    }
}
