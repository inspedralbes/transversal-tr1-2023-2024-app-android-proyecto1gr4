package com.example.testconnection;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @POST("verify/{nom_usuari}/{contrasenya}")
    Call<ResponseBody> login(@Path("nom_usuari") String nom_usuari, @Path("contrasenya") String contrasenya);
}


