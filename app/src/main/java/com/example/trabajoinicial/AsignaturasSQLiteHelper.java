package com.example.trabajoinicial;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AsignaturasSQLiteHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Asignaturas.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tabla_asignaturas";
    private static final String COLUMN_ID = "id_asignatura";
    private static final String COLUMN_NAME = "nombre_asignatura";
    private static final String COLUMN_CALIFICACION = "calificacion_asignatura";

    public AsignaturasSQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    String createSQL_query = "CREATE TABLE " + TABLE_NAME +
            " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_CALIFICACION + " INTEGER);";
    String dropSQL_query = "DROP TABLE IF EXISTS " + TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createSQL_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dropSQL_query);
        onCreate(db);
    }

    public void insertarAsignatura(String nombreAsignatura, int calificacionAsignatura) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put(COLUMN_NAME, nombreAsignatura);
        registro.put(COLUMN_CALIFICACION, calificacionAsignatura);
        db.insert(TABLE_NAME, null, registro);
    }
}
