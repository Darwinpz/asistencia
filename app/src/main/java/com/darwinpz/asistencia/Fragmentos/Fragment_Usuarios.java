package com.darwinpz.asistencia.Fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.darwinpz.asistencia.Adaptadores.Adapter_usuario;
import com.darwinpz.asistencia.Add_usuarios;
import com.darwinpz.asistencia.MainActivity;
import com.darwinpz.asistencia.Principal;
import com.darwinpz.asistencia.R;

public class Fragment_Usuarios extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_usuarios,container,false);

        RecyclerView recyclerView = vista.findViewById(R.id.recyclerview_usuarios);
        ProgressBar progressBar = vista.findViewById(R.id.progressBar);
        TextView txt_existe = vista.findViewById(R.id.txt_existe);
        TextView txt_contador = vista.findViewById(R.id.txt_contador);
        Button btn_add = vista.findViewById(R.id.btn_add);

        Adapter_usuario lista_usuarios = new Adapter_usuario(vista.getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(vista.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(lista_usuarios);

        MainActivity.ctlUsuario.VerUsuarios(lista_usuarios, MainActivity.auth.getUid(),txt_existe,progressBar,txt_contador);

        btn_add.setOnClickListener(v -> {

            startActivity(new Intent(vista.getContext(), Add_usuarios.class));

        });

        return vista;

    }
}
