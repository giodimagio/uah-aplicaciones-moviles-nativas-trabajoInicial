package com.example.trabajoinicial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateActivity extends AppCompatActivity {

    EditText nombreAsignatura, calificacionAsignatura;
    Button añadirButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        nombreAsignatura = findViewById(R.id.asignaturaTextInputEditText);
        añadirButton = findViewById(R.id.añadirButton);
        añadirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsignaturasSQLiteHelper asignaturasdb = new AsignaturasSQLiteHelper(CreateActivity.this);
                asignaturasdb.insertarAsignatura(nombreAsignatura.getText().toString(), 9);
            }
        });
    }
}