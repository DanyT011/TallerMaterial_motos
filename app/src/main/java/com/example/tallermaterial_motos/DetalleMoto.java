package com.example.tallermaterial_motos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleMoto extends AppCompatActivity {
    private ImageView foto;
    private TextView modelo, marca, placa;
    private Intent intent;
    private Bundle bundle;
    private Moto m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_moto);

        String mod, marc, plc;
        foto = findViewById(R.id.imgFotoDetalle);
        modelo = findViewById(R.id.txtModeloDet);
        marca = findViewById(R.id.txtMarcaDet);
        placa = findViewById(R.id.txtPlacaDet);

        intent = getIntent();
        bundle = intent.getBundleExtra("datos");

        foto.setImageResource(bundle.getInt("Foto"));
        mod = bundle.getString("Modelo");
        marc = bundle.getString("Marca");
        plc = bundle.getString("Placa");


        modelo.setText(mod);
        marca.setText(marc);
        placa.setText(plc);

        m = new Moto(mod, marc, plc , bundle.getInt("Foto"));

    }

    public void onBackPressed(){
        finish();
        Intent intent = new Intent(DetalleMoto.this, MainActivity.class);
        startActivity(intent);
    }

    public void eliminar(View v){
        String positivo, negativo;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar Persona");
        builder.setMessage("¿Está seguro de que desea eliminar esta persona?");
        positivo = "SI";
        negativo = "NO";

        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m.eliminar();
                onBackPressed();
            }
        });

        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}