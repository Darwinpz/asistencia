    package com.darwinpz.asistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

    public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText_correo = findViewById(R.id.editText_correo);
        EditText editText_clave = findViewById(R.id.editText_clave);
        Button btn_ingresar = findViewById(R.id.btn_ingresar);


        btn_ingresar.setOnClickListener(v -> {

            startActivity(new Intent(this, Principal.class));


        });


    }
}