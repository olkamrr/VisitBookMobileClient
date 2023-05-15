package net.larntech.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TeacherActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        bottomNavigationView = findViewById(R.id.navTeacher);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.teacher);
    }

    TeacherFragment teacherFragment = new TeacherFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    public boolean
    onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId()) {
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