package com.example.crudstayfresh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.crudstayfresh.db.DbUsuarios;
import com.example.crudstayfresh.entidades.Usuarios;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity extends AppCompatActivity {

      EditText txtNombre, txtTelefono, txtCorreo;
      Button btnguardar;

      FloatingActionButton fabeditar;
      FloatingActionButton fabeliminar;

      Usuarios usuarios;

      int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);


        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreo);
        btnguardar = findViewById(R.id.btnguardar);
        fabeditar = findViewById(R.id.fabeditar);
        fabeliminar = findViewById(R.id.faneliminar);

         if(savedInstanceState == null){
             Bundle extras = getIntent().getExtras();
             if (extras == null){

                 id = Integer.parseInt(null);

             }else{

                 id = extras.getInt("ID");
             }



         }else{

             id = (int) savedInstanceState.getSerializable("ID");

         }


        DbUsuarios dbUsuarios = new DbUsuarios(VerActivity.this);
         usuarios = dbUsuarios.verUsuarios(id);

         if(usuarios !=null){

               txtNombre.setText(usuarios.getNombre());
               txtTelefono.setText(usuarios.getTelefono());
               txtCorreo.setText(usuarios.getCorreo_electronico());
               btnguardar.setVisibility(View.INVISIBLE);

               txtNombre.setInputType(InputType.TYPE_NULL);
               txtTelefono.setInputType(InputType.TYPE_NULL);
               txtCorreo.setInputType(InputType.TYPE_NULL);
         }


         fabeditar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                 intent.putExtra("ID", id);
                 startActivity(intent);
             }
         });

         fabeliminar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                 builder.setMessage("quieres eliminar el incidente ?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {


                         dbUsuarios.eliminarUsuario(id);

                         if(dbUsuarios.eliminarUsuario(id)){

                             Lista();
                         }
                     }
                 })

                         .setNegativeButton("No", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {

                             }
                         }).show();


             }
         });



    }


    public void Lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}