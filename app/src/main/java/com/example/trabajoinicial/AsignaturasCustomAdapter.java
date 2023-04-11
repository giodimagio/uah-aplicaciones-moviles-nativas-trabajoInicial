package com.example.trabajoinicial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AsignaturasCustomAdapter extends RecyclerView.Adapter<AsignaturasCustomAdapter.MyViewHolder> {

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

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.frame_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //holder.id_asignatura_text.setText(String.valueOf(id_asignatura.get(position)));
        holder.nombre_asignatura_text.setText(String.valueOf(nombre_asignatura.get(position)));
        //holder.calificacion_asignatura_text.setText(String.valueOf(calificacion_asignatura.get(position)));
    }

    @Override
    public int getItemCount() {
        return id_asignatura.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nombre_asignatura_text;
        // TextView id_asignatura_text, calificacion_asignatura_text;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //id_asignatura_text = itemView.findViewById(R.id.id_asignatura_text);
            nombre_asignatura_text = itemView.findViewById(R.id.nombre_asignatura_text);
            //calificacion_asignatura_text = itemView.findViewById(R.id.calificacion_asignatura_text);
        }
    }
}
