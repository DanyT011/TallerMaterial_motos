package com.example.tallermaterial_motos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class DetalleMoto extends AppCompatActivity {
    private ImageView foto;
    private TextView modelo, marca, placa;
    private Intent intent;
    private Bundle bundle;
    private StorageReference storageReference;
    private Moto m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_moto);

        String mod, marc, plc, id;
        foto = findViewById(R.id.imgFotoDetalle);
        modelo = findViewById(R.id.txtModeloDet);
        marca = findViewById(R.id.txtMarcaDet);
        placa = findViewById(R.id.txtPlacaDet);
        storageReference = FirebaseStorage.getInstance().getReference();

        intent = getIntent();
        bundle = intent.getBundleExtra("datos");

        id = bundle.getString("id");
        mod = bundle.getString("Modelo");
        marc = bundle.getString("Marca");
        plc = bundle.getString("Placa");

        storageReference.child(id).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(foto);
            }
        });

        modelo.setText(mod);
        marca.setText(marc);
        placa.setText(plc);

    }

      public void eliminar(View v){
        String positivo, negativo;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.eliminar_moto);
        builder.setMessage(R.string.mensaje_advertencia);
        positivo = getString(R.string.si);
        negativo = getString(R.string.no);

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

    public void onBackPressed(){
        finish();
        Intent intent = new Intent(DetalleMoto.this, MainActivity.class);
        startActivity(intent);
    }

}