package com.example.testconnection;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    public interface OnProductAddedListener {
        void onProductAdded(List<Producto> addedProducts);
    }

    private OnProductAddedListener listener;
    private List<Producto> productos;
    private List<Producto> addedProducts;

    private TextView textPreuTotal;

    private int numFragment;



    public ProductAdapter(List<Producto> productos, int numFragment, TextView textPreu) {
        this.productos = productos;
        this.addedProducts = new ArrayList<>();
        this.numFragment = numFragment;
        this.textPreuTotal = textPreu;
    }

    public ProductAdapter(List<Producto> productos, OnProductAddedListener listener) {
        this.productos = productos;
        this.listener = listener;
        this.addedProducts = new ArrayList<>();
    }

    public ProductAdapter(List<Producto> productos, int numFragment, OnProductAddedListener listener) {
        this.productos = productos;
        this.listener = listener;
        this.addedProducts = new ArrayList<>();
        this.numFragment = numFragment;
    }

    public double calcularTotal(){
        double total = 0;
        for (Producto p : productos){
            total += p.getPrecio();
        }
        return total;
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Producto product = productos.get(position);
        holder.nameTextView.setText(product.getNombre());
        holder.priceTextView.setText(String.valueOf(product.getPrecio()));
        holder.descriptionTextView.setText(product.getDescripcion());

        if (numFragment == 1) {
            holder.botoAfegir.setText("Comprar");
            holder.botoAfegir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Producto productToAdd = productos.get(position);
                        addedProducts.add(productToAdd);
                        listener.onProductAdded(addedProducts);
                    }
                }
            });
        } else if (numFragment == 2) {
            holder.botoAfegir.setText("Eliminar");
            holder.botoAfegir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productos.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, productos.size());
                    double total = calcularTotal();
                    textPreuTotal.setText("Preu total: " + String.valueOf(total));
                }
            });
        }

        Log.d("RutaImagen", "Ruta de la imagen: " + product.getFotos());
        // Cargar la imagen utilizando Picasso
        Picasso.get().load(product.getFotos()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }
    public void updateProductList(List<Producto> updatedProducts) {
        productos = new ArrayList<>(updatedProducts);
        notifyDataSetChanged();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView priceTextView;
        TextView descriptionTextView;

        Button botoAfegir;
        ImageView imageView;

        public ProductViewHolder(@NonNull View itemView, final ProductAdapter adapter) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textNombre);
            priceTextView = itemView.findViewById(R.id.textPrecio);
            descriptionTextView = itemView.findViewById(R.id.textDescripcion);
            botoAfegir = itemView.findViewById(R.id.botoAfegir);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}