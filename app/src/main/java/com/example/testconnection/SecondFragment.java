package com.example.testconnection;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testconnection.databinding.FragmentSecondBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    private TextView textPreuTotal;

    private static final String BASE_URL = "http://10.0.2.2:3001/";

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

        MainActivity3 activity3 = (MainActivity3) getActivity();
        User user = activity3.getUser();

        List<Producto> addedProducts;

        if(getArguments() == null) {
            addedProducts = new ArrayList<>();
        } else {
            // Retrieve the addedProducts list from the arguments or ViewModel
            addedProducts = (List<Producto>) getArguments().getSerializable("addedProducts");

            textPreuTotal = view.findViewById(R.id.textPreuTotal);

            ProductAdapter adapter = new ProductAdapter(addedProducts, 2, textPreuTotal);

            // Initialize your RecyclerView and set its adapter
            RecyclerView recyclerView = view.findViewById(R.id.my_recycler_view2);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);

            double totalPreu = adapter.calcularTotal();
            textPreuTotal.setText("Preu total: " + String.valueOf(totalPreu));
        }

        binding.botoData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mCalendar = Calendar.getInstance();
                int year = mCalendar.get(Calendar.YEAR);
                int month = mCalendar.get(Calendar.MONTH);
                int dayOfMonth = mCalendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                binding.textData.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        binding.botoHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mCalendar = Calendar.getInstance();
                int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
                int minute = mCalendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                binding.textHora.setText(String.format("%02d:%02d", hourOfDay, minute));
                            }
                        }, hour, minute, true);
                timePickerDialog.show();
            }
        });

        binding.botoFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Api api = retrofit.create(Api.class);

                String preuStr = textPreuTotal.getText().toString();
                String[] parts = preuStr.split(": ");

                double preu = 0;

                if (parts.length > 1) {
                    try {
                        preu = Double.parseDouble(parts[1]);
                    } catch (NumberFormatException e) {
                    }
                } else {
                }

                Comanda comanda = new Comanda(user.getId_usuari(), preu,
                        binding.textData.getText().toString(), binding.textHora.getText().toString(), addedProducts);
                Call<Void> call = api.sendComanda(comanda);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            // Les dades s'han enviat correctament
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // Hi ha hagut un error en enviar les dades
                    }
                });

            }
        });


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