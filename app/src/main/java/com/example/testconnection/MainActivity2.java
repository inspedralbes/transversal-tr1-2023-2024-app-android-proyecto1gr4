package com.example.testconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Aquí obtienes el usuario autenticado (pasado como Serializable en el Intent)
        User user = (User) getIntent().getSerializableExtra("user");

        if (user != null) {
            TextView usernameTextView = findViewById(R.id.nombreTextView);
            TextView cognomsTextView = findViewById(R.id.apellidosTextView);
            TextView emailTextView = findViewById(R.id.correoTextView);
            TextView numero_targetaTextView = findViewById(R.id.tarjetaTextView);
            TextView data_caducitatTextView = findViewById(R.id.caducidadTextView);
            TextView cvvTextView = findViewById(R.id.cvvTextView);

            usernameTextView.setText("Nombre: " + user.getNom_usuari());
            cognomsTextView.setText("Apellidos: " + user.getCognoms_usuari());
            emailTextView.setText("Correo Electrónico: " + user.getCorreuElectronic());
            numero_targetaTextView.setText("Tarjeta: " + user.getNumeroTargeta());
            data_caducitatTextView.setText("Data caducitat: " + user.getDataCaducitat());
            cvvTextView.setText("CVV: " + user.getCvv());
        } else {
            Log.e("MainActivity2", "User info is null");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflar el menú desde el archivo menu.xml
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            // Crea un Intent para abrir MainActivity2
            Intent intent = new Intent(this, MainActivity2.class);

            // Aquí puedes obtener el objeto User que desees pasar a MainActivity2
            User user = (User) getIntent().getSerializableExtra("user");

            if (user != null) {
                // Pasa el objeto User serializado a MainActivity2
                intent.putExtra("user", user);

                // Inicia MainActivity2
                startActivity(intent);
            } else {
                Log.e("MainActivity3", "El objeto User es nulo");
            }
            return true;
        }
        if (id == R.id.cerrarSesion) {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}