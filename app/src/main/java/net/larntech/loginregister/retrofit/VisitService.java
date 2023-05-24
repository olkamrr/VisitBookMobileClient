package net.larntech.loginregister.retrofit;

import net.larntech.loginregister.models.Visit;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface VisitService {
    @POST("/api/visit/save/{lessonId}/{studentId}")
    Call<Visit> save(@Body Visit visit, @Path("lessonId") int lessonId, @Path("studentId") int studentId, @Header("Authorization") String authHeader);

    @POST("/api/visit/update/{id}")
    Call<Visit> update(@Path("id") int id, @Body Visit visit, @Header("Authorization") String authHeader);

    @GET("/api/visit/find/{lessonId}/{studentId}")
    Call<List<Visit>> find(@Path("lessonId") int lessonId, @Path("studentId") int studentId, @Header("Authorization") String authHeader);

    @GET("/api/visit/find/{lessonId}/{studentId}/{data}")
    Call<Visit> findVisit(@Path("lessonId") int lessonId, @Path("studentId") int studentId, @Path("data") String data, @Header("Authorization") String authHeader);

    @GET("/api/visit/find/{lessonId}")
    Call<List<Date>> findDate(@Path("lessonId") int lessonId, @Header("Authorization") String authHeader);

    @GET("/api/visit/find/date/{lessonId}/{date}")
    Call<List<Visit>> getVisitsForTeacher(@Path("lessonId") int lessonId, @Path("date") String date, @Header("Authorization") String authHeader);
}
