package com.example.crudstayfresh;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crudstayfresh.db.DbUsuarios;
import com.example.crudstayfresh.entidades.Usuarios;

public class EditarActivity extends AppCompatActivity {

    Spinner spinner;
    EditText txtNombre, txtTelefono, txtCorreo;
    Button btnguardar;

    boolean bien = false;
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
        spinner = findViewById(R.id.spinner);


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


        DbUsuarios dbUsuarios = new DbUsuarios(EditarActivity.this);
        usuarios = dbUsuarios.verUsuarios(id);

        if(usuarios !=null){

            txtNombre.setVisibility(View.INVISIBLE);
            txtTelefono.setVisibility(View.INVISIBLE);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtTelefono.setInputType(InputType.TYPE_NULL);

            txtNombre.setText(usuarios.getNombre());
            txtTelefono.setText(usuarios.getTelefono());
            txtCorreo.setText(usuarios.getCorreo_electronico());

        }

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("") && !txtCorreo.getText().toString().equals("") ){

              bien =  dbUsuarios.editarUsuario(id, txtNombre.getText().toString(), txtTelefono.getText().toString(), txtCorreo.getText().toString());

              if(bien){
                  Toast.makeText(EditarActivity.this, "Registro modificado de forma correcta", Toast.LENGTH_LONG).show();
                  veraRegistro();
              }else{
                  Toast.makeText(EditarActivity.this, "Registro modificado de forma incorrecta", Toast.LENGTH_LONG).show();
              }
                }else {
                    Toast.makeText(EditarActivity.this, "Llene los campos porfavor", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

              private void veraRegistro(){

                  Intent intent = new Intent(this, VerActivity.class);
                  intent.putExtra("ID", id);
                  startActivity(intent);
              }
}