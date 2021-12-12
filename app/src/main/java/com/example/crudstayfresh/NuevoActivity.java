package com.example.crudstayfresh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.crudstayfresh.adaptadores.rut;
import com.example.crudstayfresh.db.DbUsuarios;

import java.util.ArrayList;

public class NuevoActivity extends AppCompatActivity {

    Spinner spinner;
    EditText txtNombre, txtTelefono, txtCorreo;
    Button btnguardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

      txtNombre = findViewById(R.id.txtNombre);
      txtTelefono = findViewById(R.id.txtTelefono);
      txtCorreo = findViewById(R.id.txtCorreo);
      btnguardar = findViewById(R.id.btnguardar);

        spinner = findViewById(R.id.spinner);

        ArrayList<String> campos =new ArrayList<String>();
        campos.add("C2");
        campos.add("LINF");
        campos.add("LINF");
        campos.add("LEICA");
        campos.add("LNET");
        campos.add("LTEL");



        ArrayAdapter adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,campos);
        spinner.setAdapter(adapter);
        String spinnert = spinner.getSelectedItem().toString();



      btnguardar.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              rut Rut = new rut();
              String crut = txtTelefono.getText().toString();
              String.format(crut);
              boolean valid = false;

              valid = Rut.validar(crut);

              String spinnert = spinner.getSelectedItem().toString();
              DbUsuarios db = new DbUsuarios(NuevoActivity.this);
              if (valid) {
                  long id = db.insertarUsuario(txtNombre.getText().toString(), txtTelefono.getText().toString(), txtCorreo.getText().toString(), spinnert);


                      Toast.makeText(NuevoActivity.this, "INCIDENTE GUARDADO DE FORMA CORRECTA", Toast.LENGTH_LONG).show();
                      limpiar();
                  } else {
                      Toast.makeText(NuevoActivity.this, "REGISTRO DE INCIDENTE DE FORMA FALLIDA PORFAVOR REVISE QUE EL RUT TENGA PUNTO Y GUION", Toast.LENGTH_LONG).show();
                  }

              }

      });
    }
    private void limpiar(){
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");

    }
}
