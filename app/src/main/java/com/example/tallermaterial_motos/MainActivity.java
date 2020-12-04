package com.example.tallermaterial_motos;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView listadoMotos;
    private AdaptadorMoto adapter;
    private LinearLayoutManager llm;
    private ArrayList<Moto> motos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listadoMotos = findViewById(R.id.lstMotos);

        motos = new ArrayList<>();
        motos.add(new Moto("RX 2020", "Honda", "AAA1234", R.drawable.ima_1));
        llm = new LinearLayoutManager(this);
        adapter = new AdaptadorMoto(motos);
        llm.setOrientation(RecyclerView.VERTICAL);

        listadoMotos.setLayoutManager(llm);
        listadoMotos.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}