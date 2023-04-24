package com.example.trabajoinicial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {

    EditText nombreAsignatura, calificacionAsignatura;
    Button insertarButton;
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // [Views] Elementos de las vistas
        nombreAsignatura = findViewById(R.id.asignaturaTextInputEditText);
        calificacionAsignatura = findViewById(R.id.calificacionTextInputEditText);
        insertarButton = findViewById(R.id.insertarButton);

        // [Toast] Inicialización de los Toast con mensaje de éxito/error
        Toast toastExito = new Toast(CreateActivity.this);
        Toast toastError = new Toast(CreateActivity.this);

        // [Listeners] Manejo del evento onClick al pulsar el botón de insertar
        insertarButton.setOnClickListener(view -> {
            try (AsignaturasSQLiteHelper asignaturasdb = new AsignaturasSQLiteHelper(CreateActivity.this)) {
                asignaturasdb.insertarAsignatura(nombreAsignatura.getText().toString(), Integer.parseInt(calificacionAsignatura.getText().toString()));
                // [Log] de éxito
                Log.i(TAG, "[Calificaciones UAH] Asignatura " + nombreAsignatura.getText().toString() +
                        " calificada con un " + calificacionAsignatura.getText().toString() +
                        " añadida correctamente");
                // [Toast] de éxito
                toastExito.setDuration(Toast.LENGTH_LONG);
                toastExito.setText("Asignatura: " + nombreAsignatura.getText().toString() +
                        "\nañadida correctamente");
                toastExito.show();
            } catch (SQLiteException exception) {
                // [Log] de error desconocido
                Log.e(TAG,"[Calificaciones UAH]  Error desconocido al insertar en BBDD",exception);
                // [Toast] de error desconocido
                toastError.setDuration(Toast.LENGTH_SHORT);
                toastError.setText("Error desconocido. Prueba de nuevo");
                toastError.show();
            }
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                startActivity(intent);
            }, 2000);
        });

        // [Listeners] Manejo de eventos al cambiar el texto de los campos editables
        nombreAsignatura.addTextChangedListener(nombreAsignaturaTextWatcher);
        calificacionAsignatura.addTextChangedListener(calificacionAsignaturaTextWatcher);

        // [Elementos] Botón insertarButton deshabilitado por defecto
        insertarButton.setEnabled(false);
    }

    // [TextWatcher] para el campo nombreAsignatura
    private final TextWatcher nombreAsignaturaTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            validarFormulario();
        }
    };

    // [TextWatcher] para el campo calificacionAsignatura
    private final TextWatcher calificacionAsignaturaTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            validarFormulario();
        }
    };

    // [Función] Habilita/Deshabilita el botón insertarButton en función de si se cumple la validación
    private void validarFormulario() {
        boolean esUnNombreValido = nombreAsignatura.getText().toString().matches("^(\\p{IsLatin}+\\s)*\\p{IsLatin}++(\\s\\d)?$");
        boolean esUnaCalificacionValida = calificacionAsignatura.getText().toString().matches("\\d|10");

        if (!esUnNombreValido) {
            nombreAsignatura.setError("Introduce un nombre válido");
        }

        if (!esUnaCalificacionValida) {
            calificacionAsignatura.setError("Introduce una calificación válida del 0-10");
        }

        insertarButton.setEnabled(esUnNombreValido && esUnaCalificacionValida);
    }
}