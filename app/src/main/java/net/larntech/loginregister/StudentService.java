package net.larntech.loginregister;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface StudentService {
    @GET("/api/student/{id}")
    Call<Student> getStudent(@Path("id") int groupId, @Header("Authorization") String authHeader);
}
