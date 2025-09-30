package com.example.parcial.ui.listar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.parcial.R;
import com.example.parcial.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolderProducto> {

    private final List<Producto> listado = new ArrayList<>();
    private final LayoutInflater li;

    public ProductoAdapter(Context context) {
        this.li = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = li.inflate(R.layout.item, parent, false);
        return new ViewHolderProducto(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProducto holder, int position) {
        Producto productoActual = listado.get(position);
        holder.codigo.setText(String.valueOf(productoActual.getCodigo()));
        holder.descripcion.setText("Descripción: " + productoActual.getDescripcion());
        holder.precio.setText("Precio: $" + productoActual.getPrecio());
    }

    @Override
    public int getItemCount() {
        return listado.size();
    }

    public void actualizarLista(List<Producto> nuevaLista) {
        listado.clear();
        listado.addAll(nuevaLista);
        notifyDataSetChanged();
    }

    public static class ViewHolderProducto extends RecyclerView.ViewHolder {
        TextView codigo, descripcion, precio;

        public ViewHolderProducto(@NonNull View itemView) {
            super(itemView);
            codigo = itemView.findViewById(R.id.tvCodigo);
            descripcion = itemView.findViewById(R.id.tvDescripcion);
            precio = itemView.findViewById(R.id.tvPrecio);
        }
    }
}