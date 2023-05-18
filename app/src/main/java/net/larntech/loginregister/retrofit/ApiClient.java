package net.larntech.loginregister.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.1.47:9999")
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static UserService getUserService(){
        UserService userService = getRetrofit().create(UserService.class);
        return userService;
    }

    public static GroupService getGroupService(){
        GroupService groupService = getRetrofit().create(GroupService.class);
        return groupService;
    }

    public static StudentService getStudentService(){
        StudentService studentService = getRetrofit().create(StudentService.class);
        return studentService;
    }

    public static ScheduleService getScheduleService(){
        ScheduleService scheduleService = getRetrofit().create(ScheduleService.class);
        return scheduleService;
    }

    public static TeacherService getTeacherService(){
        TeacherService teacherService = getRetrofit().create(TeacherService.class);
        return teacherService;
    }
}
