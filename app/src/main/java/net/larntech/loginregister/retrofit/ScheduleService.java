package net.larntech.loginregister.retrofit;

import net.larntech.loginregister.models.Schedule;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ScheduleService {
    @POST("/api/schedule/save/{groupId}")
    Call<Schedule> save(@Body Schedule schedule, @Path("groupId") int groupId, @Header("Authorization") String authHeader);

    @GET("/api/schedule/{groupId}/{semester}/{weekday}")
    Call<List<Schedule>> getLessons(@Path("groupId") int groupId, @Path("semester") int semester, @Path("weekday") String weekday, @Header("Authorization") String authHeader);

    @GET("/api/schedule/edit/{id}")
    Call<Schedule> getLesson(@Path("id") int id, @Header("Authorization") String authHeader);

    @POST("/api/schedule/update/{id}")
    Call<Schedule> update(@Path("id") int id, @Body Schedule schedule, @Header("Authorization") String authHeader);

    @GET("/api/schedule/delete/{id}")
    Call<Schedule> delete(@Path("id") int id, @Header("Authorization") String authHeader);

    @GET("/api/schedule/{groupId}/{semester}/{weekday}/{week}")
    Call<List<Schedule>> getLessonsWeek(@Path("groupId") int groupId, @Path("semester") int semester, @Path("weekday") String weekday, @Path("week") String week, @Header("Authorization") String authHeader);

    @GET("/api/schedule/teacher/{teacherId}/{weekday}")
    Call <List<Schedule>> getSchedulesByTeacher(@Path("teacherId") int teacherId, @Path("weekday") String weekday, @Header("Authorization") String authHeader);
}
