package com.example.testconnection;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    @GET("getProductes")
    Call<List<Producto>> getProductes();

    @POST("sendComanda")
    Call<Void> sendComanda(@Body Comanda comanda);
}
