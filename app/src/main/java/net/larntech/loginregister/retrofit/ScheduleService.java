package net.larntech.loginregister.retrofit;

import net.larntech.loginregister.models.Schedule;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ScheduleService {
    @POST("/api/schedule/save/{groupId}")
    Call<Schedule> save(@Body Schedule schedule, @Path("groupId") int groupId, @Header("Authorization") String authHeader);
}
