package net.larntech.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class StudentActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        bottomNavigationView = findViewById(R.id.nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.calendar);

        Intent intent = getIntent();


        if(intent.getExtras() != null){
            String username = intent.getStringExtra("username");
            String token = intent.getStringExtra("token");

            bundle.putString("username", username);
            bundle.putString("token", token);
        }
    }


    VisitFragment visitFragment = new VisitFragment();
    ScheduleFragment scheduleFragment = new ScheduleFragment();
    GroupFragment groupFragment = new GroupFragment();
    ProfileFragment profileFragment = new ProfileFragment();

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
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, groupFragment)
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