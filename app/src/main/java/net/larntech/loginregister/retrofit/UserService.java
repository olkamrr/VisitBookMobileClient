package net.larntech.loginregister.retrofit;

import net.larntech.loginregister.models.LoginRequest;
import net.larntech.loginregister.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("/api/auth")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);


}
