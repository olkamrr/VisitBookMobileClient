package net.larntech.loginregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.larntech.loginregister.adapter.ScheduleAdapter;
import net.larntech.loginregister.models.Schedule;
import net.larntech.loginregister.retrofit.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitActivity extends AppCompatActivity {
    String mYear;
    String mDay;
    String mMonth;
    String token;
    String idGroup;
    String weekday;
    int semesterSchedule;
    String weekSchedule;
    TextView dateText;
    TextView weekdayText;
    Spinner semester;
    Spinner week;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);

        getIntentSM();
        initializeComponent();
    }

    public void getIntentSM() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            token = extras.getString("token");
            idGroup = extras.getString("idGroup");
            mYear = extras.getString("year");
            mDay = extras.getString("day");
            mMonth = extras.getString("month");
            weekday = extras.getString("weekday");
        } else Toast.makeText(VisitActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    public void initializeComponent() {
        dateText = findViewById(R.id.dateText);
        weekdayText = findViewById(R.id.weekdayText);
        semester = findViewById(R.id.semester);
        week = findViewById(R.id.weekVisit);
        recyclerView = findViewById(R.id.scheduleList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(VisitActivity.this));

        switch (mMonth) {
            case "1":
                dateText.setText(mDay + " января " + mYear);
                break;
            case "2":
                dateText.setText(mDay + " февраля " + mYear);
                break;
            case "3":
                dateText.setText(mDay + " марта " + mYear);
                break;
            case "4":
                dateText.setText(mDay + " апреля " + mYear);
                break;
            case "5":
                dateText.setText(mDay + " мая " + mYear);
                break;
            case "6":
                dateText.setText(mDay + " июня " + mYear);
                break;
            case "7":
                dateText.setText(mDay + " июля " + mYear);
                break;
            case "8":
                dateText.setText(mDay + " августа " + mYear);
                break;
            case "9":
                dateText.setText(mDay + " сентября " + mYear);
                break;
            case "10":
                dateText.setText(mDay + " октября " + mYear);
                break;
            case "11":
                dateText.setText(mDay + " ноября " + mYear);
                break;
            case "12":
                dateText.setText(mDay + " декабря " + mYear);
                break;
        }
        weekdayText.setText(weekday);

        semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.semester);
                semesterSchedule = Integer.parseInt(choose[selectedItemPosition]);
                getLessons();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        week.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.weekVisit);
                weekSchedule = choose[selectedItemPosition];
                getLessons();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void getLessons() {
        Call<List<Schedule>> getScheduleCall = ApiClient.getScheduleService().getLessonsWeek(Integer.parseInt(idGroup), semesterSchedule, weekday, weekSchedule, "Bearer " + token);
        getScheduleCall.enqueue(new Callback<List<Schedule>>() {
            @Override
            public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                populateListView(response.body());
            }

            @Override
            public void onFailure(Call<List<Schedule>> call, Throwable t) {
                //Toast.makeText(VisitActivity.this, "Failed " + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateListView(List<Schedule> scheduleList) {
        // определяем слушателя нажатия элемента в списке
        ScheduleAdapter.OnScheduleClickListener scheduleClickListener = (schedule, position) -> {
            Intent intent = new Intent(VisitActivity.this, MarkVisitActivity.class);
            intent.putExtra("token", token);
            intent.putExtra("idGroup", idGroup);
            intent.putExtra("idLesson", String.valueOf(schedule.getId()));
            intent.putExtra("nameLesson", String.valueOf(schedule.getName()));
            intent.putExtra("day", mDay);
            intent.putExtra("month", mMonth);
            intent.putExtra("year", mYear);
            startActivity(intent);
        };
        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(scheduleList, scheduleClickListener);
        recyclerView.setAdapter(scheduleAdapter);
    }
}