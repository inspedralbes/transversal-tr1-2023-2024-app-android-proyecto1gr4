package com.example.pruebalistaproductos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    private List<Producto> productos;

    public ProductAdapter(List<Producto> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Producto product = productos.get(position);
        holder.nameTextView.setText(product.getNombre());
        holder.priceTextView.setText(String.valueOf(product.getPrecio()));
        holder.descriptionTextView.setText(product.getDescripcion());

    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView priceTextView;
        TextView descriptionTextView;
        ImageView imageView;

        Button botoAfegir;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textNombre);
            priceTextView = itemView.findViewById(R.id.textPrecio);
            descriptionTextView = itemView.findViewById(R.id.textDescripcion);
            botoAfegir = itemView.findViewById(R.id.botoAfegir);
        }
    }
}

