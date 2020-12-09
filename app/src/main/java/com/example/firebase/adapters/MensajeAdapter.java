package com.example.firebase.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.R;
import com.example.firebase.models.Mensaje;

import java.util.ArrayList;

public class MensajeAdapter extends RecyclerView.Adapter<MensajeAdapter.ViewHolder> {

    private int resource;
    private ArrayList<Mensaje> mensajesList;

    public MensajeAdapter(ArrayList<Mensaje> mensajesList, int resource){
        this.mensajesList = mensajesList;
        this.resource = resource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int index) {

        Mensaje mensaje = mensajesList.get(index);

        viewHolder.mtvTitulo.setText(mensaje.getTitulo());
        viewHolder.mtvMensaje.setText(mensaje.getMensaje());
    }

    @Override
    public int getItemCount() {
        return mensajesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mtvTitulo;
        private final TextView mtvMensaje;

        public View view;

        public ViewHolder(View view) {
            super(view);

            this.view = view;
            this.mtvTitulo = view.findViewById(R.id.tvTitulo);
            this.mtvMensaje = view.findViewById(R.id.tvMensaje);
        }
    }
}
