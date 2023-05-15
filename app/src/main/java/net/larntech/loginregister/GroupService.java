package net.larntech.loginregister;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface GroupService {
    @GET("/api/group/{id}")
    Call<Group> getGroup(@Path("id") int userId, @Header("Authorization") String authHeader);
}
