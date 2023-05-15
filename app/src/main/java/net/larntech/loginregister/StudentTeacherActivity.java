package net.larntech.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StudentTeacherActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_teacher);
        bottomNavigationView = findViewById(R.id.navStudentTeacher);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.calendar);
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
}