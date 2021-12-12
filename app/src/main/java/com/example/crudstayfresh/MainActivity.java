package com.example.crudstayfresh;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudstayfresh.adaptadores.ListaUusuariosAdapter;
import com.example.crudstayfresh.db.DbHelper;
import com.example.crudstayfresh.db.DbUsuarios;
import com.example.crudstayfresh.entidades.Usuarios;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView viewFecha;
    Button newagregar;

    RecyclerView listaUsuarios;
    ArrayList<Usuarios> listaArrayUusuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaUsuarios = findViewById(R.id.ListaUsuarios);
        listaUsuarios.setLayoutManager(new LinearLayoutManager(this));


        viewFecha = findViewById(R.id.viewFecha);




        newagregar = findViewById(R.id.newagregar);

        DbUsuarios dbUsuarios = new DbUsuarios(MainActivity.this);

        listaArrayUusuarios = new ArrayList<>();

        ListaUusuariosAdapter adapter = new ListaUusuariosAdapter(dbUsuarios.mostrarUsuarios());
        listaUsuarios.setAdapter(adapter);

        newagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            saltar();
            }
        });
       /*
        btncrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DbHelper db = new DbHelper(MainActivity.this);
                SQLiteDatabase sql = db.getWritableDatabase();
                if (sql != null) {
                    Toast.makeText(MainActivity.this, "BASE DE DATOS CREADA CORRECTAMENTE", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_LONG).show();
                }
            }
        });*/
        }
        public boolean onCreateOptionsMenu(Menu menu){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_principal, menu);
        return true;
      }
        public boolean onOptionsItemSelected(MenuItem item){
          switch (item.getItemId()){
              case R.id.menuNuevo:
              nuevoRegistro();
              return  true;
              default:
               return super.onOptionsItemSelected(item);
          }
        }


       private void nuevoRegistro(){
          Intent intent = new Intent(this, NuevoActivity.class);
          startActivity(intent);
       }

       private void saltar(){
           Intent intent = new Intent(this, NuevoActivity.class);
           startActivity(intent);
       }



}

