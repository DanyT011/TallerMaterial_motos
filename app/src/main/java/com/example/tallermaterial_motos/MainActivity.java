package com.example.tallermaterial_motos;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdaptadorMoto.OnMotoClickListener {
    private RecyclerView listadoMotos;
    private AdaptadorMoto adapter;
    private LinearLayoutManager llm;
    private ArrayList<Moto> motos;
    private DatabaseReference databaseReference;
    private String db = "Motos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        listadoMotos = findViewById(R.id.lstMotos);

        motos = new ArrayList<>();
        llm = new LinearLayoutManager(this);
        adapter = new AdaptadorMoto(motos, this);
        llm.setOrientation(RecyclerView.VERTICAL);

        listadoMotos.setLayoutManager(llm);
        listadoMotos.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(db).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                motos.clear();
                if(snapshot.exists()){
                    for (DataSnapshot snap: snapshot.getChildren()){
                        Moto m = snap.getValue(Moto.class);
                        motos.add(m);
                    }
                }
                adapter.notifyDataSetChanged();
                Datos.setMotos(motos);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void crear(View v){
        Intent intent;
        intent = new Intent(MainActivity.this, CrearMoto.class);
        startActivity(intent);
    }


    @Override
    public void onMotoClick(Moto m) {
        Intent intent;
        Bundle bundle;

        bundle = new Bundle();
        bundle.putString("id", m.getId());
        bundle.putString("Modelo", m.getModelo());
        bundle.putString("Marca", m.getMarca());
        bundle.putString("Placa", m.getPlacas());

        intent = new Intent(MainActivity.this, DetalleMoto.class);
        intent.putExtra("datos",bundle);
        startActivity(intent);
    }
}