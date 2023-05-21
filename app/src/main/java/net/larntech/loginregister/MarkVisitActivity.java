package net.larntech.loginregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import net.larntech.loginregister.adapter.StudentAdapter;
import net.larntech.loginregister.adapter.StudentVisitAdapter;
import net.larntech.loginregister.models.Schedule;
import net.larntech.loginregister.models.Student;
import net.larntech.loginregister.models.Visit;
import net.larntech.loginregister.retrofit.ApiClient;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarkVisitActivity extends AppCompatActivity {
    String mYear;
    String mDay;
    String mMonth;
    String token;
    String nameLesson;
    String idLesson;
    String idGroup;
    TextView nameText;
    RecyclerView recyclerView;
    String mDate;
    Date date;
    int student;
    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_visit);

        getIntentSM();
        initializeComponent();
        mDate = mYear + "-" + mMonth + "-" + mDay;
    }

    public void getIntentSM() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            token = extras.getString("token");
            mYear = extras.getString("year");
            mDay = extras.getString("day");
            mMonth = extras.getString("month");
            nameLesson = extras.getString("nameLesson");
            idLesson = extras.getString("idLesson");
            idGroup = extras.getString("idGroup");
        } else Toast.makeText(MarkVisitActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
    }

    public void initializeComponent() {
        nameText = findViewById(R.id.nameText);
        nameText.setText(nameLesson);
        recyclerView = findViewById(R.id.studentList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MarkVisitActivity.this, LinearLayoutManager.VERTICAL, false));
        getStudent();
    }

    public void getStudent() {
        Call<List<Student>> getStudentCall = ApiClient.getStudentService().getStudent(Integer.parseInt(idGroup), "Bearer " + token);
        getStudentCall.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                populateListView(response.body());
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Toast.makeText(MarkVisitActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateListView(List<Student> studentList) {
        StudentVisitAdapter studentVisitAdapter = new StudentVisitAdapter(MarkVisitActivity.this, studentList, token, idLesson, mDate);
        recyclerView.setAdapter(studentVisitAdapter);
    }

    public void saveVisit(int idStudent, String statusVisit, String lesson, String t, String dataString) {
        student = idStudent;
        status = statusVisit;
        idLesson = lesson;
        token = t;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = dateFormat.parse(dataString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mDate = dateFormat.format(date);
        if (String.valueOf(student) != null && status != null && idLesson != null) {
            save();
        }
    }

    public void save(){
        Visit visit = new Visit();
        visit.setStatus(status);
        visit.setDate(mDate);
        Call<Visit> getVisitCall = ApiClient.getVisitService().save(visit, Integer.parseInt(idLesson), student, "Bearer " + token);
        getVisitCall.enqueue(new Callback<Visit>() {
            @Override
            public void onResponse(Call<Visit> call, Response<Visit> response) {
                //Toast.makeText(MarkVisitActivity.this, "Save successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Visit> call, Throwable t) {
                //Toast.makeText(MarkVisitActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}