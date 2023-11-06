package com.example.testconnection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.testconnection.databinding.FragmentFirstBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirstFragment extends Fragment {
    private FragmentFirstBinding binding;
    private List<Producto> allProducts;
    private List<Producto> filteredProducts;
    private ProductAdapter productAdapter;
    private String BASE_URL = "http://10.0.2.2:3001/";
    private List<Producto> addedProducts = new ArrayList<>();  // Variable para mantener la lista de productos agregados

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Si se ha pasado la lista de productos agregados desde el SecondFragment, úsala
        if (getArguments() != null) {
            addedProducts = (List<Producto>) getArguments().getSerializable("addedProducts");
        }
        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("addedProducts", (Serializable) addedProducts);

                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
            }
        });



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        api.getProductes().enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>>response) {
                allProducts = response.body();
                filteredProducts = new ArrayList<>();
                for (Producto producto : allProducts) {
                    if (producto.getDisponibilidad() == 1) {
                        filteredProducts.add(producto);
                    }
                }
                RecyclerView recyclerView = view.findViewById(R.id.my_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                productAdapter = new ProductAdapter(filteredProducts, 1, new ProductAdapter.OnProductAddedListener() {
                    @Override
                    public void onProductAdded(List<Producto> addedProductsFromAdapter) {
                        // No borres la lista, agrega los nuevos productos
                        addedProducts.addAll(addedProductsFromAdapter);
                    }
                });
                recyclerView.setAdapter(productAdapter);
            }
            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Log.d("ErrorConnection", t.getMessage());
            }
        });
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterProductsByTipoId(1);
                productAdapter.updateProductList(filteredProducts);
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterProductsByTipoId(2);
                productAdapter.updateProductList(filteredProducts);
            }
        });
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterProductsByTipoId(3);
                productAdapter.updateProductList(filteredProducts);
            }
        });
    }
    private void filterProductsByTipoId(int tipoId) {
        filteredProducts.clear();
        for (Producto producto : allProducts) {
            if (producto.getTipo_id() == tipoId) {
                filteredProducts.add(producto);
            }
        }
        for (Producto producto : filteredProducts) {
            Log.d("ProductoFiltrado", "Nombre: " + producto.getNombre() + ", Precio: " + producto.getPrecio());
        }
        productAdapter.updateProductList(filteredProducts);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}