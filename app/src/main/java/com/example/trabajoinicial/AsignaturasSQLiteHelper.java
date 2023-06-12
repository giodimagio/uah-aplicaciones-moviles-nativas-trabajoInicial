package com.example.trabajoinicial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * @author Jorge Romero
 *
 * @clase AsignaturasSQLiteHelper
 * @descripcion Clase que se encarga de crear la base de datos de las asignaturas
 * @see SQLiteOpenHelper
 */
public class AsignaturasSQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Asignaturas.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tabla_asignaturas";
    private static final String COLUMN_ID = "id_asignatura";
    private static final String COLUMN_NAME = "nombre_asignatura";
    private static final String COLUMN_CALIFICACION = "calificacion_asignatura";

    public AsignaturasSQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * @descripcion Querys SQL para crear y borrar la tabla de asignaturas
     */
    String createSQL_query = "CREATE TABLE " + TABLE_NAME +
            " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_CALIFICACION + " INTEGER);";
    String dropSQL_query = "DROP TABLE IF EXISTS " + TABLE_NAME;

    /**
     * @descripcion Método que se encarga de crear la tabla de asignaturas
     * @param db Objeto db que representa la base de datos
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createSQL_query);
    }

    /**
     * @descripcion Método que se encarga de actualizar la tabla de asignaturas
     * @param db Objeto db que representa la base de datos
     * @param oldVersion La version antigua de la base de datos
     * @param newVersion La version nueva de la base de datos
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dropSQL_query);
        onCreate(db);
    }

    /**
     * @descripcion Método que se encarga de insertar una asignatura en la tabla de asignaturas
     * @param nombreAsignatura El nombre de la asignatura
     * @param calificacionAsignatura La calificacion de la asignatura
     */
    public void insertarAsignatura(String nombreAsignatura, int calificacionAsignatura) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put(COLUMN_NAME, nombreAsignatura);
        registro.put(COLUMN_CALIFICACION, calificacionAsignatura);
        db.insert(TABLE_NAME, null, registro);
    }

    /**
     * @descripcion Método que se encarga de actualizar una asignatura en la tabla de asignaturas
     * @see Cursor
     */
    public Cursor leerTabla() {
        String getSQL_query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery(getSQL_query, null);
    }
}
