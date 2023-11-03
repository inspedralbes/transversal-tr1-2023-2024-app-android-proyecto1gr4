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

    private String BASE_URL = "http://10.0.2.2:3001/";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Producto> addedProducts;

        if(getArguments() == null) {
            addedProducts = new ArrayList<>();
        } else {
            addedProducts = (List<Producto>) getArguments().getSerializable("addedProducts");
        }


        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if there are any added products
                if (!addedProducts.isEmpty()) {
                    // pass the addedProducts list
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("addedProducts", (Serializable) addedProducts);

                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);

                } else {
                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.action_FirstFragment_to_SecondFragment);
                }
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        api.getProductes().enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                List<Producto> productes = response.body();

                List<Producto> prod_disponibles = new ArrayList<>();
                for (Producto producto : productes) {
                    if (producto.getDisponibilidad() == 1) {
                        prod_disponibles.add(producto);
                    }
                }

                RecyclerView recyclerView = view.findViewById(R.id.my_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                ProductAdapter productAdapter = new ProductAdapter(prod_disponibles, 1, new ProductAdapter.OnProductAddedListener() {
                    @Override
                    public void onProductAdded(List<Producto> addedProductsFromAdapter) {
                        // Update addedProducts in FirstFragment with addedProducts from ProductAdapter
                        addedProducts.clear();
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
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}