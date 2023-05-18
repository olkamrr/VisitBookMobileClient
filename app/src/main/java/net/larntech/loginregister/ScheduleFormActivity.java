package net.larntech.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import net.larntech.loginregister.adapter.StudentAdapter;
import net.larntech.loginregister.models.Student;
import net.larntech.loginregister.models.Teacher;
import net.larntech.loginregister.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleFormActivity extends AppCompatActivity {

    List<String> teacherLastNameList = new ArrayList<String>();
    Spinner teacher;
    Spinner week;
    Spinner weekday;
    Spinner semester;
    Spinner type;
    EditText name;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_form);

        getIntentSM();
    }

    public void getTeacher() {
        Call<List<Teacher>> getSTeacherCall = ApiClient.getTeacherService().getTeacher("Bearer " + token);
        getSTeacherCall.enqueue(new Callback<List<Teacher>>() {
            @Override
            public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
                populateListView(response.body());
            }

            @Override
            public void onFailure(Call<List<Teacher>> call, Throwable t) {
                Toast.makeText(ScheduleFormActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateListView(List<Teacher> teacherList) {
        for (Teacher teacher: teacherList) {
            teacherLastNameList.add(teacher.getLastName());
        }
        teacherAdapter();
    }

    public void getIntentSM() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            token = extras.getString("token");
            getTeacher();
        } else Toast.makeText(ScheduleFormActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
    }

    public void teacherAdapter() {
        String[] teacherLastName = new String[teacherLastNameList.size()];
        teacherLastNameList.toArray(teacherLastName);
        teacher = findViewById(R.id.teacher);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, teacherLastName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teacher.setAdapter(adapter);
    }
}