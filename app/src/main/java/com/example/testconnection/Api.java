package com.example.testconnection;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("getProductes")
    Call<List<Producto>> getProductes();
}
