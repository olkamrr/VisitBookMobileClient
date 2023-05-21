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
import net.larntech.loginregister.models.Student;
import net.larntech.loginregister.retrofit.ApiClient;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_visit);

        getIntentSM();
        initializeComponent();
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
        StudentVisitAdapter studentVisitAdapter = new StudentVisitAdapter(MarkVisitActivity.this, studentList);
        recyclerView.setAdapter(studentVisitAdapter);
    }
}