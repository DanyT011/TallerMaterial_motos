package com.example.tallermaterial_motos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Random;

public class CrearMoto extends AppCompatActivity {
    private EditText modelo, marca, placa, id;
    private ImageView foto;
    private Uri uri;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_moto);

        modelo = findViewById(R.id.txtModelo);
        marca = findViewById(R.id.txtMarca);
        placa = findViewById(R.id.txtPlaca);
        foto = findViewById(R.id.imgFotoSeleccionada);

        storageReference = FirebaseStorage.getInstance().getReference();

    }

    public void guardar(View v){
        String mod, mar, plc, id;
        Moto moto;
        InputMethodManager imp;
        imp = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imp.hideSoftInputFromWindow(placa.getWindowToken(),0);

        if(validar()){
            mod = modelo.getText().toString();
            mar = marca.getText().toString();
            plc = placa.getText().toString();
            id = Datos.getId();

            moto = new Moto(mod, mar, plc, id);
            moto.guardar();

            subirFoto(id);
            limpiar();
            Snackbar.make(v,R.string.guardado_exitosamente, Snackbar.LENGTH_LONG).show();
            uri = null;
        }
    }

    public void subirFoto(String id){
        StorageReference child = storageReference.child(id);
        UploadTask uploadTask = child.putFile(uri);
    }

    public void limpiar(View v){
        limpiar();
    }
    public void limpiar() {
        modelo.setText("");
        marca.setText("");
        placa.setText("");
        modelo.requestFocus();
    }

    public void onBackPressed(){
        finish();
        Intent intent = new Intent(CrearMoto.this, MainActivity.class);
        startActivity(intent);
    }

    public void seleccionarFoto(View v){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.seleccionar_foto)), 1);
    }

    protected  void onActivityResult(int requeCode, int resultCode, Intent data) {
        super.onActivityResult(requeCode, resultCode, data);
        if(requeCode == 1){
            uri = data.getData();
            if(uri != null){
                foto.setImageURI(uri);
            }
        }
    }

    public  boolean validar(){
        if(modelo.getText().toString().isEmpty()){
            modelo.setError(getString(R.string.digitar_modelo));
            modelo.requestFocus();
            return false;
        }

        if(marca.getText().toString().isEmpty()){
            marca.setError(getString(R.string.digitar_marca));
            marca.requestFocus();
            return false;
        }

        if(placa.getText().toString().isEmpty()){
            placa.setError(getString(R.string.digitar_placa));
            placa.requestFocus();
            return false;
        }

        if (uri == null){
            Snackbar.make((View)placa, R.string.selec_foto_val, Snackbar.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}