package com.example.crudstayfresh.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.crudstayfresh.entidades.Usuarios;

import java.util.ArrayList;


public class DbUsuarios extends  DbHelper {

    Context context;

    public DbUsuarios(@Nullable Context context) {
        super(context);
        this.context = context;

    }

    public long insertarUsuario(String nombre, String telefono, String correo_electronico, String laboratorio) {

        long id = 0;
        try {


            DbHelper db = new DbHelper(context);
            SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("telefono", telefono);
            values.put("correo_electronico", correo_electronico);
            values.put("laboratorio", laboratorio);

            id = sqLiteDatabase.insert(TABLE_USUARIOS, null, values);

        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }
         public ArrayList<Usuarios> mostrarUsuarios(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
        Usuarios usuarios = null;
        Cursor cursorUsuarios = null;

        cursorUsuarios = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS, null);

        if (cursorUsuarios.moveToFirst()){
          do {

              usuarios =  new Usuarios();
              usuarios.setId(cursorUsuarios.getInt(0));
              usuarios.setNombre(cursorUsuarios.getString(1));
              usuarios.setTelefono(cursorUsuarios.getString(2));
              usuarios.setCorreo_electronico(cursorUsuarios.getString(3));
              usuarios.setLaboratorio(cursorUsuarios.getString(4));
              listaUsuarios.add(usuarios);
          }while (cursorUsuarios.moveToNext());

        }

        cursorUsuarios.close();
        return listaUsuarios;
    }


    public Usuarios verUsuarios(int id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Usuarios usuarios = null;
        Cursor cursorUsuarios = null;

        cursorUsuarios = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS + " WHERE id = " + id +  " LIMIT 1", null );

        if (cursorUsuarios.moveToFirst()){


                usuarios =  new Usuarios();
                usuarios.setId(cursorUsuarios.getInt(0));
                usuarios.setNombre(cursorUsuarios.getString(1));
                usuarios.setTelefono(cursorUsuarios.getString(2));
                usuarios.setCorreo_electronico(cursorUsuarios.getString(3));
                usuarios.setLaboratorio(cursorUsuarios.getString(4));



        }

        cursorUsuarios.close();
        return usuarios;
    }




    public boolean editarUsuario(int id, String nombre, String telefono, String correo_electronico) {

        boolean bien = false;

        DbHelper db = new DbHelper(context);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        try {
            sqLiteDatabase.execSQL("UPDATE " + TABLE_USUARIOS + " SET nombre = '" + nombre + "', telefono = '" + telefono + "', correo_electronico = '" + correo_electronico + "' WHERE id='" + id + "' ");
         bien = true;
        } catch (Exception ex) {
            ex.toString();
            bien = false;
        } finally {
            sqLiteDatabase.close();
        }
        return bien;
    }


    public boolean eliminarUsuario(int id) {

        boolean bien = false;

        DbHelper db = new DbHelper(context);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        try {
            sqLiteDatabase.execSQL("DELETE FROM " + TABLE_USUARIOS + " WHERE id = '" + id + "'");
            bien = true;
        } catch (Exception ex) {
            ex.toString();
            bien = false;
        } finally {
            sqLiteDatabase.close();
        }
        return bien;
    }
}