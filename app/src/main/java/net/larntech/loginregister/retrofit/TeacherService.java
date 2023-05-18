package net.larntech.loginregister.retrofit;

import net.larntech.loginregister.models.Teacher;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface TeacherService {
    @GET("/api/teacher/all")
    Call<List<Teacher>> getTeacher(@Header("Authorization") String authHeader);
}
