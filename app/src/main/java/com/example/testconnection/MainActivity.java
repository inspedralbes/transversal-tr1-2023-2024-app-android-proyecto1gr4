package com.example.testconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://10.0.2.2:3001/";

    User userFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);


        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom_usuari = usernameEditText.getText().toString();
                String contrasenya = passwordEditText.getText().toString();

                if (nom_usuari.isEmpty() || contrasenya.isEmpty()) {
                    // Mostrar mensaje si falta alguna entrada
                    Toast.makeText(MainActivity.this, "Sisplau, ompli les dades", Toast.LENGTH_SHORT).show();
                } else {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    UserService userService = retrofit.create(UserService.class);

                    User user = new User(-15, nom_usuari, null, contrasenya, null, null, null, null);

                    Call<ResponseBody> call = userService.login(user);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                try {
                                    String responseBody = response.body().string();
                                    JSONObject jsonObject = new JSONObject(responseBody);
                                    boolean verify = jsonObject.getBoolean("verify");

                                    if (verify) {
                                        Retrofit retrofit2 = new Retrofit.Builder()
                                                .baseUrl(BASE_URL)
                                                .addConverterFactory(GsonConverterFactory.create())
                                                .build();

                                        UserInfoService userInfoService = retrofit2.create(UserInfoService.class);
                                        Call<User> call2 = userInfoService.getUserInfo(user.getNom_usuari());

                                        call2.enqueue(new Callback<User>() {
                                            @Override
                                            public void onResponse(Call<User> call, Response<User> response) {
                                                userFinal = response.body();
                                                // Inicio de sesión exitoso
                                                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                                                intent.putExtra("user", userFinal); // Aquí pasamos el objeto User serializado
                                                Log.d("MainActivity", "Información de usuario que se pasa a Activity2: " + userFinal.getId_usuari() + " " + userFinal.getCorreuElectronic());
                                                startActivity(intent);
                                                Toast.makeText(MainActivity.this, "Login Correcte", Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onFailure(Call<User> call, Throwable t) {

                                            }
                                        });
                                    } else {
                                        // Login fallido
                                        Toast.makeText(MainActivity.this, "Inici de sessió incorrecte", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (IOException | JSONException e) {
                                    e.printStackTrace();
                                    // Manejar errores de lectura de la respuesta
                                    Toast.makeText(MainActivity.this, "Error en la resposta del servidor", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // Manejar respuestas no exitosas
                                Toast.makeText(MainActivity.this, "Inici de sessió incorrecte", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e("LoginActivity", "Error de xarxa", t);
                            Toast.makeText(MainActivity.this, "Error de xarxa", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}