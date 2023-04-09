package com.example.trabajoinicial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addFloatingActionButton;

    AsignaturasSQLiteHelper asignaturasdb;
    ArrayList<String> nombre_asignatura;
    ArrayList<Integer> id_asignatura, calificacion_asignatura;
    AsignaturasCustomAdapter asignaturasCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.listRecyclerView);
        addFloatingActionButton = findViewById(R.id.addFloatingActionButton);
        addFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });

        asignaturasdb = new AsignaturasSQLiteHelper(MainActivity.this);
        id_asignatura = new ArrayList<>();
        nombre_asignatura = new ArrayList<>();
        calificacion_asignatura = new ArrayList<>();

        mostrarTabla();

        asignaturasCustomAdapter = new AsignaturasCustomAdapter(MainActivity.this,
                id_asignatura, nombre_asignatura, calificacion_asignatura);

        recyclerView.setAdapter(asignaturasCustomAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    public void mostrarTabla() {
        Cursor cursor = asignaturasdb.leerTabla();
        while (cursor.moveToNext()) {
            id_asignatura.add(cursor.getInt(0));
            nombre_asignatura.add(cursor.getString(1));
            calificacion_asignatura.add(cursor.getInt(2));
        }
    }

}