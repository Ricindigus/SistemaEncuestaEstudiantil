package com.fisi.unmsm.sistemaencuestaestudiantil.adpaters;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fisi.unmsm.sistemaencuestaestudiantil.R;
import com.fisi.unmsm.sistemaencuestaestudiantil.pojos.Encuesta;

import java.util.ArrayList;

/**
 * Created by RICARDO on 30/06/2017.
 */

public class EncuestaAdapter extends RecyclerView.Adapter<EncuestaAdapter.ViewHolder> {
    private ArrayList<Encuesta> encuestas;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        public void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardViewCurso;
        ImageView imagenTeoria;
        ImageView imagenPractica;
        ImageView imagenLaboratorio;
        TextView txtNombreCurso;
        TextView txtTipoCurso;
        TextView txtProfesor;

        public ViewHolder(View itemView) {
            super(itemView);
            cardViewCurso = (CardView) itemView.findViewById(R.id.cardview_alumno);
            txtNombreCurso = (TextView) itemView.findViewById(R.id.txtNombreCurso);
            txtTipoCurso = (TextView) itemView.findViewById(R.id.txtTipoCurso);
            txtProfesor = (TextView) itemView.findViewById(R.id.txtProfesor);
            imagenTeoria = (ImageView) itemView.findViewById(R.id.imagen_teoria);
            imagenPractica = (ImageView) itemView.findViewById(R.id.imagen_practica);
            imagenLaboratorio = (ImageView) itemView.findViewById(R.id.imagen_laboratorio);
        }
    }

    public EncuestaAdapter(ArrayList<Encuesta> encuestas, OnItemClickListener onItemClickListener) {
        this.encuestas = encuestas;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.curso_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int posicion = position;
        holder.cardViewCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(view, posicion);
            }
        });
        holder.txtNombreCurso.setText(encuestas.get(position).getNombreCurso());
        holder.txtTipoCurso.setText(encuestas.get(position).getTipoCurso());
        holder.txtProfesor.setText(encuestas.get(position).getProfesor().getNombre()
                + " " + encuestas.get(position).getProfesor().getApellido());
        if(encuestas.get(position).getDisponible() == 0) {
            holder.cardViewCurso.setEnabled(false);
            holder.cardViewCurso.setCardBackgroundColor(Color.LTGRAY);
        }else{
            holder.cardViewCurso.setEnabled(true);
            holder.cardViewCurso.setCardBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return encuestas.size();
    }


}

