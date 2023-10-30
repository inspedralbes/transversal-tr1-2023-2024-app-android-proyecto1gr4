package com.example.testconnection;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserInfoService {
    @GET("getUsuarios")
    Call<List<User>> getUserInfo(@Query("user") String username);
}