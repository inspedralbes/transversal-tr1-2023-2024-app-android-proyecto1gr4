package com.example.testconnection;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface UserService {
    @POST("verify")
    Call<ResponseBody> login(@Body User user);
}
