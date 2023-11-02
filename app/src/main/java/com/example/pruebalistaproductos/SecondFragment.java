package com.example.pruebalistaproductos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebalistaproductos.databinding.FragmentSecondBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Producto> addedProducts;

        if(getArguments() == null) {
            addedProducts = new ArrayList<>();
        } else {
            // Retrieve the addedProducts list from the arguments or ViewModel
            addedProducts = (List<Producto>) getArguments().getSerializable("addedProducts");

            // Initialize your RecyclerView and set its adapter
            RecyclerView recyclerView = view.findViewById(R.id.my_recycler_view2);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new ProductAdapter(addedProducts, 2));
        }



        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if there are any added products
                if (!addedProducts.isEmpty()) {
                    // pass the addedProducts list
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("addedProducts", (Serializable) addedProducts);

                    NavHostFragment.findNavController(SecondFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FirstFragment, bundle);

                } else {
                    NavHostFragment.findNavController(SecondFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FirstFragment);
                }
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
