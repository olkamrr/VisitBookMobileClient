package net.larntech.loginregister;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface StudentService {
    @GET("/api/student/{id}")
    Call<List<Student>> getStudent(@Path("id") int groupId, @Header("Authorization") String authHeader);

    @GET("/api/student/elder/{id}")
    Call<Student> getElder(@Path("id") int groupId, @Header("Authorization") String authHeader);
}
