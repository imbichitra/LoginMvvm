package com.bichi.interfaces;

import com.bichi.model.Result;
import com.bichi.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserAuth {
    @POST("mvvm.php")
    Call<Result> authUser(@Body User user);

    @GET("getUserList.php")
    Call<List<User>> getUser();
}
