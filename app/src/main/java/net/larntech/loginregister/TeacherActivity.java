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
import net.larntech.loginregister.models.Teacher;
import net.larntech.loginregister.retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    Bundle bundle = new Bundle();
    String username;
    String token;
    String id;
    String idTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        getIntentSM();
        getTeacher();

        bottomNavigationView = findViewById(R.id.navTeacher);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        bundle.putString("username", username);
        bundle.putString("token", token);
    }

    TeacherFragment teacherFragment = new TeacherFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    public boolean
    onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.teacher:
                bundle.putString("idTeacher", idTeacher);
                teacherFragment.setArguments(bundle);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, teacherFragment)
                        .commit();
                return true;
            case R.id.profile:
                profileFragment.setArguments(bundle);
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

    public void getTeacher() {
        Call<Teacher> getTeacherCall = ApiClient.getTeacherService().getTeacher(Integer.parseInt(id), "Bearer " + token);
        getTeacherCall.enqueue(new Callback<Teacher>() {
            @Override
            public void onResponse(Call<Teacher> call, Response<Teacher> response) {
                if(response.isSuccessful()){
                    Teacher teacher = response.body();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            assert teacher != null;
                            idTeacher = String.valueOf(teacher.getId());
                            bundle.putString("idTeacher", idTeacher);
                        }
                    }, 700);
                }
            }
            @Override
            public void onFailure(Call<Teacher> call, Throwable t) {
                Toast.makeText(TeacherActivity.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}