package net.larntech.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.larntech.loginregister.models.Schedule;
import net.larntech.loginregister.models.Teacher;
import net.larntech.loginregister.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleFormActivity extends AppCompatActivity {

    List<String> teacherLastNameList = new ArrayList<String>();
    List<Teacher> teachers;
    Spinner teacher;
    Spinner week;
    Spinner weekday;
    Spinner semester;
    Spinner type;
    EditText name;
    TextView list;
    Button save;
    Button delete;
    String token;
    String idGroup;
    String idLesson;
    String weekSchedule;
    String nameSchedule;
    String weekdaySchedule;
    String typeSchedule;
    int semesterSchedule;
    String teacherSchedule;
    String FIO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_form);

        getIntentSM();
        initializeComponent();
    }

    public void getLesson() {
        Call<Schedule> getScheduleCall = ApiClient.getScheduleService().getLesson(Integer.parseInt(idLesson),"Bearer " + token);
        getScheduleCall.enqueue(new Callback<Schedule>() {
            @Override
            public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                if(response.isSuccessful()){
                    Schedule schedule = response.body();
                    new Handler().postDelayed(() -> {
                        assert schedule != null;
                        ArrayAdapter adapterSemester = (ArrayAdapter) semester.getAdapter();
                        ArrayAdapter adapterWeek = (ArrayAdapter) week.getAdapter();
                        ArrayAdapter adapterTeacher = (ArrayAdapter) teacher.getAdapter();
                        ArrayAdapter adapterWeekday = (ArrayAdapter) weekday.getAdapter();
                        ArrayAdapter adapterType = (ArrayAdapter) type.getAdapter();

                        FIO = schedule.getTeacher().getLastName() + " " + schedule.getTeacher().getFirstName() + " " + schedule.getTeacher().getPatronymic();
                        list.setText("Редактировать пару");
                        name.setText(schedule.getName());
                        week.setSelection(adapterWeek.getPosition(schedule.getWeek()));
                        semester.setSelection(adapterSemester.getPosition(schedule.getSemester()));
                        teacher.setSelection(adapterTeacher.getPosition(FIO));
                        weekday.setSelection(adapterWeekday.getPosition(schedule.getWeekday()));
                        type.setSelection(adapterType.getPosition(schedule.getType()));
                    }, 700);
                }
            }

            @Override
            public void onFailure(Call<Schedule> call, Throwable t) {
                Toast.makeText(ScheduleFormActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
            }
        });
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
        teachers = teacherList;
        for (Teacher teacher: teacherList) {
            teacherLastNameList.add(teacher.getLastName() + " " + teacher.getFirstName() + " " + teacher.getPatronymic());
        }
        teacherAdapter();
    }

    public void getIntentSM() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            token = extras.getString("token");
            idGroup = extras.getString("idGroup");
            idLesson = extras.getString("idLesson");
            getTeacher();
            if (idLesson != null) {
                getLesson();
            }
        } else Toast.makeText(ScheduleFormActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
    }

    public void teacherAdapter() {
        String[] teacherLastName = new String[teacherLastNameList.size()];
        teacherLastNameList.toArray(teacherLastName);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, teacherLastName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teacher.setAdapter(adapter);
    }

    public void initializeComponent() {
        week = findViewById(R.id.week);
        weekday = findViewById(R.id.weekday);
        teacher = findViewById(R.id.teacher);
        semester = findViewById(R.id.semester);
        type = findViewById(R.id.type);
        name = findViewById(R.id.nameSchedule);
        save = findViewById(R.id.scheduleSave);
        delete = findViewById(R.id.scheduleDelete);
        list = findViewById(R.id.list);

        Schedule schedule = new Schedule();

        if (idLesson == null) delete.setVisibility(View.GONE);

        week.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.week);
                weekSchedule = choose[selectedItemPosition];
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        weekday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.weekday);
                weekdaySchedule = choose[selectedItemPosition];
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.type);
                typeSchedule = choose[selectedItemPosition];
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.semester);
                semesterSchedule = Integer.parseInt(choose[selectedItemPosition]);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        teacher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                teacherSchedule = (String) parent.getItemAtPosition(selectedItemPosition);
                for (Teacher teacher: teachers) {
                    FIO = teacher.getLastName() + " " + teacher.getFirstName() + " " + teacher.getPatronymic();
                    if (FIO.equals(teacherSchedule)) {
                        schedule.setTeacher(teacher);
                    }
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        delete.setOnClickListener(view -> {
            Call<Schedule> getScheduleCall = ApiClient.getScheduleService().delete(Integer.parseInt(idLesson),"Bearer " + token);
            getScheduleCall.enqueue(new Callback<Schedule>() {
                @Override
                public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                }

                @Override
                public void onFailure(Call<Schedule> call, Throwable t) {
                }
            });
            Intent intent = new Intent(ScheduleFormActivity.this,StudentActivity.class);
            startActivity(intent);
        });
        save.setOnClickListener(view -> {

            nameSchedule = String.valueOf(name.getText());

            schedule.setName(nameSchedule);
            schedule.setWeek(weekSchedule);
            schedule.setSemester(semesterSchedule);
            schedule.setType(typeSchedule);
            schedule.setWeekday(weekdaySchedule);

            if (idLesson != null) {
                Call<Schedule> getScheduleCall = ApiClient.getScheduleService().update(Integer.parseInt(idLesson), schedule,"Bearer " + token);
                getScheduleCall.enqueue(new Callback<Schedule>() {
                    @Override
                    public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                        Toast.makeText(ScheduleFormActivity.this, "Update successful", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Schedule> call, Throwable t) {
                        Toast.makeText(ScheduleFormActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Call<Schedule> getScheduleCall = ApiClient.getScheduleService().save(schedule, Integer.parseInt(idGroup),"Bearer " + token);
                getScheduleCall.enqueue(new Callback<Schedule>() {
                    @Override
                    public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                        Toast.makeText(ScheduleFormActivity.this, "Save successful", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Schedule> call, Throwable t) {
                        Toast.makeText(ScheduleFormActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}