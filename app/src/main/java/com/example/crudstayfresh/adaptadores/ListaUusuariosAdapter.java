package com.example.crudstayfresh.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudstayfresh.MainActivity;
import com.example.crudstayfresh.R;
import com.example.crudstayfresh.VerActivity;
import com.example.crudstayfresh.entidades.Usuarios;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ListaUusuariosAdapter extends RecyclerView.Adapter<ListaUusuariosAdapter.ContactoViewHolder> {



    ArrayList<Usuarios> listaUsuarios;


    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    String currentDateandTime = simpleDateFormat.format(new Date());



    public ListaUusuariosAdapter(ArrayList<Usuarios> listaUsuarios){
           this.listaUsuarios = listaUsuarios;
    }


    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_usuarios, null, false);
        return new ContactoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {

        holder.viewNombre.setText(listaUsuarios.get(position).getNombre());
        holder.viewTelefono.setText(listaUsuarios.get(position).getTelefono());
        holder.viewCorreo.setText(listaUsuarios.get(position).getCorreo_electronico());
        holder.viewFecha.setText(currentDateandTime);
        holder.viewLab.setText(listaUsuarios.get(position).getLaboratorio());

    }

    @Override
    public int getItemCount() {
       return listaUsuarios.size();



    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewTelefono, viewCorreo, viewFecha, viewLab;


        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);
            viewCorreo = itemView.findViewById(R.id.viewCorreo);
            viewFecha = itemView.findViewById(R.id.viewFecha);
            viewLab = itemView.findViewById(R.id.viewLab);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   Context context = viewCorreo.getContext();
                   Intent  intent = new Intent(context, VerActivity.class);
                   intent.putExtra("ID", listaUsuarios.get(getAdapterPosition()).getId());
                   context.startActivity(intent);


               }
           });
        }
    }
}
