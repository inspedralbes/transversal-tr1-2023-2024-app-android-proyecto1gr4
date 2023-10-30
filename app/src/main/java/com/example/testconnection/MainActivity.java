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
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://10.0.2.2:3001/";

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
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Crear un objeto User con la información del usuario


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                UserService userService = retrofit.create(UserService.class);

                Call<ResponseBody> call = userService.login(username, password);
                User user = new User(username, null, password, null, null, null, null);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            // Login exitoso
                            // Aquí puedes realizar acciones adicionales si es necesario
                            // Luego, inicia MainActivity2 y pasa el nombre de usuario
                            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                            intent.putExtra("user", user); // Aquí pasamos el objeto User serializado
                            Log.d("MainActivity", "Información de usuario que se pasa a Activity2: " + username);

                            startActivity(intent);
                            Toast.makeText(MainActivity.this, "Login Exitoso", Toast.LENGTH_SHORT).show();
                        } else {
                            // Login fallido
                            Toast.makeText(MainActivity.this, "Inicio de sesión fallido", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("LoginActivity", "Error de red", t);
                        Toast.makeText(MainActivity.this, "Error de red", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

