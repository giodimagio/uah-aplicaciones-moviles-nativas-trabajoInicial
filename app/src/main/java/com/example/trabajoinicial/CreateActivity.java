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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Jorge Romero
 *
 * @clase CreateActivity
 * @descripcion Clase que se encarga de crear una nueva asignatura
 * @see AppCompatActivity
 */
public class CreateActivity extends AppCompatActivity {

    /**
     * @descripcion Atributos de la clase
     */
    EditText nombreAsignatura, calificacionAsignatura;
    Button insertarButton;
    private final String TAG = getClass().getSimpleName();

    /**
     * @descripcion Método que se ejecuta al crear la actividad
     * @param savedInstanceState Bundle con el estado de la actividad
     * @see Bundle
     */
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

    /**
     * @descripcion TextWatcher para el campo nombreAsignatura que se encarga de ejecutar la
     * función validarFormulario() solo cuando se cambia el texto del campo
     * @see TextWatcher
     */
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

    /**
     * @descripcion TextWatcher para el campo calificacionAsignatura que se encarga de ejecutar la
     * función validarFormulario() solo cuando se cambia el texto del campo
     * @see TextWatcher
     */
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

    /**
     * @descripcion Función que habilita/deshabilita el botón insertarButton en función de si se cumple la validación
     */
    private void validarFormulario() {

        // [Validación] Regex para validar el nombre de la asignatura y la calificación
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