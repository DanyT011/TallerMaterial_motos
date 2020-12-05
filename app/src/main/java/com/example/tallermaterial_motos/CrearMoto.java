package com.example.tallermaterial_motos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Random;

public class CrearMoto extends AppCompatActivity {
    private EditText modelo, marcar, placa;
    private ArrayList<Integer> fotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_moto);

        modelo = findViewById(R.id.txtModelo);
        marcar = findViewById(R.id.txtMarca);
        placa = findViewById(R.id.txtPlaca);


        fotos = new ArrayList<>();
        fotos.add(R.drawable.ima_1);
        fotos.add(R.drawable.ima_2);
        fotos.add(R.drawable.ima_3);

    }

    public void guardar(View v){
        String mod, mar, plc;
        int foto;
        Moto moto;
        mod = modelo.getText().toString();
        mar = marcar.getText().toString();
        plc = placa.getText().toString();
        foto = foto_aleatoria();

        moto = new Moto(mod, mar, plc, foto);
        moto.guardar();
        limpiar();
        Snackbar.make(v,R.string.guardado_exitosamente, Snackbar.LENGTH_LONG).show();
    }

    public  int foto_aleatoria(){
        int id_foto_seleccionada;
        Random r = new Random();
        id_foto_seleccionada = r.nextInt(this.fotos.size());
        return fotos.get(id_foto_seleccionada);
    }

    public void limpiar(View v){
        limpiar();
    }
    public void limpiar(){
        modelo.setText("");
        marcar.setText("");
        placa.setText("");
        modelo.requestFocus();
    }

    public void onBackPressed(){
        finish();
        Intent intent = new Intent(CrearMoto.this, MainActivity.class);
        startActivity(intent);
    }
}