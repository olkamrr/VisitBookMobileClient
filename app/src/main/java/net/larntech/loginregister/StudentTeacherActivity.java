package net.larntech.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import net.larntech.loginregister.models.Group;
import net.larntech.loginregister.models.Student;
import net.larntech.loginregister.retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentTeacherActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    Bundle bundle = new Bundle();
    String username;
    String token;
    String id;
    String code;
    String idGroup;
    String fullName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_teacher);
        getIntentSM();
        getGroup();

        bottomNavigationView = findViewById(R.id.navStudentTeacher);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        bundle.putString("username", username);
        bundle.putString("token", token);
    }

    VisitFragment visitFragment = new VisitFragment();
    ScheduleFragment scheduleFragment = new ScheduleFragment();
    GroupFragment groupFragment = new GroupFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    TeacherFragment teacherFragment = new TeacherFragment();

    @Override
    public boolean
    onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.calendar:
                visitFragment.setArguments(bundle);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, visitFragment)
                        .commit();
                return true;

            case R.id.schedule:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, scheduleFragment)
                        .commit();
                return true;

            case R.id.group:
                bundle.putString("idGroup", idGroup);
                groupFragment.setArguments(bundle);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, groupFragment)
                        .commit();
                return true;

            case R.id.teacher:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, teacherFragment)
                        .commit();
                return true;

            case R.id.profile:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, profileFragment)
                        .commit();
                return true;
        }
        return false;
    }

    public void getIntentSM() {
        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            username = intent.getStringExtra("username");
            token = intent.getStringExtra("token");
            id = intent.getStringExtra("id");
        }
    }

    public void getGroup() {
        Call<Group> getGroupCall = ApiClient.getGroupService().getGroup(Integer.parseInt(id), "Bearer " + token);
        getGroupCall.enqueue(new Callback<Group>() {
            @Override
            public void onResponse(Call<Group> call, Response<Group> response) {
                if(response.isSuccessful()){
                    Group group = response.body();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            assert group != null;
                            code = group.getCode();
                            idGroup = String.valueOf(group.getId());
                            bundle.putString("code", code);
                            getElder();
                        }
                    }, 700);
                }
            }
            @Override
            public void onFailure(Call<Group> call, Throwable t) {
                Toast.makeText(StudentTeacherActivity.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getElder() {
        Call<Student> getStudentCall = ApiClient.getStudentService().getElder(Integer.parseInt(idGroup), "Bearer " + token);
        getStudentCall.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if(response.isSuccessful()){
                    Student student = response.body();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            assert student != null;
                            fullName = student.getLastName() + " " + student.getFirstName() + " " + student.getPatronymic();
                            bundle.putString("fullName", fullName);
                        }
                    }, 700);
                }
            }
            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                Toast.makeText(StudentTeacherActivity.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}