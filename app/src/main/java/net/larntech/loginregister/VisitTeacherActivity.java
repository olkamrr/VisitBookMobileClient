package net.larntech.loginregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import net.larntech.loginregister.adapter.DateAdapter;
import net.larntech.loginregister.retrofit.ApiClient;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitTeacherActivity extends AppCompatActivity {
    String token;
    String idLesson;
    String code;
    String name;
    TextView nameText, groupText;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_teacher);

        getIntentSM();
        initializeComponent();
    }

    public void getIntentSM() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            token = extras.getString("token");
            idLesson = extras.getString("idLesson");
            code = extras.getString("code");
            name = extras.getString("name");
        } else Toast.makeText(VisitTeacherActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
    }

    public void initializeComponent() {
        nameText = findViewById(R.id.nameText);
        groupText = findViewById(R.id.groupText);
        recyclerView = findViewById(R.id.date_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(VisitTeacherActivity.this, LinearLayoutManager.VERTICAL, false));

        nameText.setText(name);
        groupText.setText(code);

        findDate();
    }

    public void findDate() {
        Call<List<Date>> getVisitCall = ApiClient.getVisitService().findDate(Integer.parseInt(idLesson), "Bearer " + token);
        getVisitCall.enqueue(new Callback<List<Date>>() {
            @Override
            public void onResponse(Call<List<Date>> call, Response<List<Date>> response) {
                populateListView(response.body());
            }

            @Override
            public void onFailure(Call<List<Date>> call, Throwable t) {
                Toast.makeText(VisitTeacherActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void populateListView(List<Date> dateList) {
        DateAdapter.OnDateClickListener dateClickListener = (date, position) -> {
            Intent intent = new Intent(VisitTeacherActivity.this, ConfirmationVisitActivity.class);
            intent.putExtra("token", token);
            intent.putExtra("idLesson", idLesson);
            intent.putExtra("date", date.toString());
            intent.putExtra("name", name);
            startActivity(intent);
        };
        DateAdapter dateAdapter = new DateAdapter(dateList, dateClickListener);
        recyclerView.setAdapter(dateAdapter);
    }
}