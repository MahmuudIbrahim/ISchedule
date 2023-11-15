package com.example.i_schedule;

public interface UserApi {
    @POST("/register")
    Call<UserResponse> registerUser(@Body User user);
}
