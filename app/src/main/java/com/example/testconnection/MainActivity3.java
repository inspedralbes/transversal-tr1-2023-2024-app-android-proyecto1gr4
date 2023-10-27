package com.example.testconnection;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.List;

public class MainActivity3 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.Perfil));
        setContentView(R.layout.activity_main3); // El diseño de MainActivity3
        // Obtén una referencia al Spinner desde el diseño
        Spinner spinner = findViewById(R.id.spinner);

        // Obtén las opciones desde strings.xml
        String[] opciones = getResources().getStringArray(R.array.opciones_spinner);

        // Configura un ArrayAdapter con las opciones y establece el adaptador en el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Agrega un listener para manejar la selección del usuario
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Evitar que se realicen acciones si la opción seleccionada es "Seleccione una opción"
                if (position == 0) {
                    return;
                }

                String opcionSeleccionada = opciones[position];

                // Realiza acciones basadas en la opción seleccionada
                if (opcionSeleccionada.equals("Perfil usuari")) {
                    // Iniciar MainActivity3
                    Intent intent = new Intent(MainActivity3.this, MainActivity3.class);
                    startActivity(intent);
                } else if (opcionSeleccionada.equals("Tancar sessió")) {
                    // Iniciar Activity1
                    Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(MainActivity3.this, "No se ha seleccionado ninguna opción", Toast.LENGTH_SHORT).show();
            }


    });
    }
}
