package com.example.tallermaterial_motos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleMoto extends AppCompatActivity {
    private ImageView foto;
    private TextView modelo, marca, placa;
    private Intent intent;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_moto);

        foto = findViewById(R.id.imgFotoDetalle);
        modelo = findViewById(R.id.txtModeloDet);
        marca = findViewById(R.id.txtMarcaDet);
        placa = findViewById(R.id.txtPlacaDet);

        intent = getIntent();
        bundle = intent.getBundleExtra("datos");

        foto.setImageResource(bundle.getInt("Foto"));
        modelo.setText(bundle.getString("Modelo"));
        marca.setText(bundle.getString("Marca"));
        placa.setText(bundle.getString("Placa"));
    }
}