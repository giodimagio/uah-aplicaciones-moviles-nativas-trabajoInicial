package com.example.trabajoinicial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * @author Jorge Romero
 *
 * @clase AsignaturasCustomAdapter
 * @descripcion Clase que se encarga de adaptar los datos de las asignaturas a un RecyclerView
 * @see RecyclerView
 * @see MyViewHolder
 */
public class AsignaturasCustomAdapter extends RecyclerView.Adapter<AsignaturasCustomAdapter.MyViewHolder> {

    /**
     * @descripcion Atributos de la clase, constructor y métodos
     */
    private final Context context;
    private final ArrayList<String> nombre_asignatura;
    private final ArrayList<Integer> id_asignatura;
    private final ArrayList<Integer> calificacion_asignatura;

    public AsignaturasCustomAdapter(Context context, ArrayList<Integer> id_asignatura, ArrayList<String> nombre_asignatura, ArrayList<Integer> calificacion_asignatura) {
        this.context = context;
        this.id_asignatura = id_asignatura;
        this.nombre_asignatura = nombre_asignatura;
        this.calificacion_asignatura = calificacion_asignatura;
    }

    /**
     * @descripcion Metodo que se encarga de inflar el layout de la lista de asignaturas
     * @param parent El ViewGroup donde se infla el layout
     * @param viewType El tipo de vista
     * @return MyViewHolder El ViewHolder de la lista de asignaturas
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_main_list_item, parent, false);
        return new MyViewHolder(view);
    }

    /**
     * @descripcion Metodo que se encarga de asignar los datos de las asignaturas a los elementos del ViewHolder
     * @param holder El ViewHolder de la lista de asignaturas
     * @param position La posicion de la asignatura en la lista
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nombreAsignaturaText.setText(String.valueOf(nombre_asignatura.get(position)));
        holder.calificacionAsignaturaText.setText(String.valueOf(calificacion_asignatura.get(position)));
    }

    /**
     * @descripcion Metodo que se encarga de obtener el numero de asignaturas
     * @return int Número de asignaturas
     */
    @Override
    public int getItemCount() {
        return id_asignatura.size();
    }

    /**
     * @clase MyViewHolder
     * @descripcion Clase que se encarga de inicializar los elementos del ViewHolder
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nombreAsignaturaText;
        TextView calificacionAsignaturaText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreAsignaturaText = itemView.findViewById(R.id.nombreAsignaturaText);
            calificacionAsignaturaText = itemView.findViewById(R.id.calificacionAsignaturaText);
        }
    }
}
