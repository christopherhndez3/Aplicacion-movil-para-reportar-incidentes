package com.example.crudstayfresh.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {


    private static  final int DATABASE_VERSION = 3;
    private static  final String DATABASE_NOMBRE = "usuarios.db";
    public static  final String TABLE_USUARIOS = "t_usuarios";



    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_USUARIOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL, " +
                "telefono TEXT NOT NULL," +
                "correo_electronico TEXT NOT NULL," +
                "laboratorio TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE "+ TABLE_USUARIOS);
        onCreate(db);

    }
}

