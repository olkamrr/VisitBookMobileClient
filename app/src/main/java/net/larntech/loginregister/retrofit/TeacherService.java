package net.larntech.loginregister.retrofit;

import net.larntech.loginregister.models.Teacher;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface TeacherService {
    @GET("/api/teacher/all")
    Call<List<Teacher>> getTeacher(@Header("Authorization") String authHeader);

    @GET("/api/teacher/{id}")
    Call<Teacher> getTeacher(@Path("id") int userId, @Header("Authorization") String authHeader);
}
