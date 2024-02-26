package com.darwinpz.asistencia.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.darwinpz.asistencia.Det_usuario;
import com.darwinpz.asistencia.Holders.Holder_usuario;
import com.darwinpz.asistencia.Objetos.Usuario;
import com.darwinpz.asistencia.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_usuario extends RecyclerView.Adapter<Holder_usuario> {

    public List<Usuario> list_usuario = new ArrayList<>();
    Context context;

    public Adapter_usuario(Context context) {
        this.context = context;
    }

    public void AddUsuario(Usuario user){
        list_usuario.add(user);
        notifyItemInserted(list_usuario.size());
    }

    public void ClearUsuarios(){
        list_usuario.clear();
    }

    @NonNull
    @Override
    public Holder_usuario onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_usuarios, parent,false);
        return new Holder_usuario(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder_usuario holder, int position) {

        holder.card_nombre.setText(list_usuario.get(position).nombre);
        holder.card_correo.setText(list_usuario.get(position).correo);
        holder.card_rol.setText(list_usuario.get(position).rol);

        holder.cardView.setOnClickListener(v -> {

            Intent i = new Intent();
            i.setClass(context, Det_usuario.class);
            i.putExtra("uid", list_usuario.get(position).uid);
            context.startActivity(i);

        });

    }

    @Override
    public int getItemCount() {
        return list_usuario.size();
    }
}
