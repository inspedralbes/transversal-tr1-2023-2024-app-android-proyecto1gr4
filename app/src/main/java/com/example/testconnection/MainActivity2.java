package com.example.testconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {

    private static final String BASE_URL = "http://10.0.2.2:3001/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Realiza una solicitud para obtener la información completa del usuario
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Aquí obtienes el usuario autenticado (pasado como Serializable en el Intent)
        User user = (User) getIntent().getSerializableExtra("user");

        if (user != null) {
            UserInfoService userInfoService = retrofit.create(UserInfoService.class);
            Call<List<User>> call = userInfoService.getUserInfo(user.getNom_usuari());

            Log.d("MainActivity2", "User info received: " + user.getNom_usuari());
            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    if (response.isSuccessful()) {
                        List<User> userList = response.body();

                        if (!userList.isEmpty()) {
                            // Supongamos que deseas mostrar el primer usuario de la lista en tu interfaz
                            User fullUserInfo = userList.get(0);

                            Log.d("MainActivity2", "User info retrieved successfully");

                            // Actualiza la interfaz de usuario con la información completa del usuario
                            TextView usernameTextView = findViewById(R.id.nombreTextView);
                            TextView cognomsTextView = findViewById(R.id.apellidosTextView);
                            TextView emailTextView = findViewById(R.id.correoTextView);
                            TextView numero_targetaTextView = findViewById(R.id.tarjetaTextView);
                            TextView data_caducitatTextView = findViewById(R.id.caducidadTextView);
                            TextView cvvTextView = findViewById(R.id.cvvTextView);

                            usernameTextView.setText("Nombre: " + fullUserInfo.getNom_usuari());
                            cognomsTextView.setText("Apellidos: " + fullUserInfo.getCognoms_usuari());
                            emailTextView.setText("Correo Electrónico: " + fullUserInfo.getCorreuElectronic());
                            numero_targetaTextView.setText("Tarjeta: " + fullUserInfo.getNumeroTargeta());
                            data_caducitatTextView.setText("Data caducitat: " + fullUserInfo.getDataCaducitat());
                            cvvTextView.setText("CVV: " + fullUserInfo.getCvv());
                        } else {
                            Log.e("MainActivity2", "La lista de usuarios está vacía");
                        }
                    } else {
                        int statusCode = response.code(); // Obtiene el código de estado HTTP
                        String errorMessage = "Error al obtener la información del usuario. Código de estado: " + statusCode;
                        Log.e("MainActivity2", errorMessage);
                        Toast.makeText(MainActivity2.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    Log.e("MainActivity2", "Error de red", t);
                    Toast.makeText(MainActivity2.this, "Error de red", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Log.e("MainActivity2", "User info is null");
        }
    }
}