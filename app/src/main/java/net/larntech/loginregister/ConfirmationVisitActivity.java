package net.larntech.loginregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.larntech.loginregister.adapter.DateAdapter;
import net.larntech.loginregister.adapter.VisitAdapter;
import net.larntech.loginregister.models.Visit;
import net.larntech.loginregister.retrofit.ApiClient;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmationVisitActivity extends AppCompatActivity {
    String token, idLesson, name, date, dateRequest;
    TextView nameText, dateText;
    RecyclerView recyclerView;
    Button confirmation;
    Date date1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_visit);

        getIntentSM();
        try {
            getDates();
        } catch (ParseException e) {
            Log.println(Log.ASSERT,"Error: " , String.valueOf(e));
        }
        initializeComponent();
    }

    public void getIntentSM() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            token = extras.getString("token");
            idLesson = extras.getString("idLesson");
            name = extras.getString("name");
            date = extras.getString("date");
        } else Toast.makeText(ConfirmationVisitActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SimpleDateFormat")
    public void getDates() throws ParseException {
        DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        date1 = format.parse(date);
        date = new SimpleDateFormat("d MMM yyyy, EEEE").format(date1);
        dateRequest = new SimpleDateFormat("yyyy-MM-dd").format(date1);
    }

    public void initializeComponent() {
        nameText = findViewById(R.id.nameText);
        dateText = findViewById(R.id.dateText);
        confirmation = findViewById(R.id.confirm_button);
        recyclerView = findViewById(R.id.visit_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(ConfirmationVisitActivity.this, LinearLayoutManager.VERTICAL, false));

        nameText.setText(name);
        dateText.setText(date);

        getVisits();
    }

    public void getVisits() {
        Call<List<Visit>> getVisitCall = ApiClient.getVisitService().getVisitsForTeacher(Integer.parseInt(idLesson), dateRequest, "Bearer " + token);
        getVisitCall.enqueue(new Callback<List<Visit>>() {
            @Override
            public void onResponse(Call<List<Visit>> call, Response<List<Visit>> response) {
                populateListView(response.body());
                confirmation(response.body());
            }

            @Override
            public void onFailure(Call<List<Visit>> call, Throwable t) {
                Toast.makeText(ConfirmationVisitActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void populateListView(List<Visit> visitList) {
        VisitAdapter visitAdapter = new VisitAdapter(visitList);
        recyclerView.setAdapter(visitAdapter);
    }

    public void confirmation(List<Visit> visitList) {
        confirmation.setOnClickListener(view -> {
            for (Visit visit: visitList){
                visit.setConfirmation(true);
                Call<Visit> getVisitCall = ApiClient.getVisitService().update(visit.getId(), visit, "Bearer " + token);
                getVisitCall.enqueue(new Callback<Visit>() {
                    @Override
                    public void onResponse(Call<Visit> call, Response<Visit> response) {
                        Toast.makeText(ConfirmationVisitActivity.this, "Confirmation successful", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Visit> call, Throwable t) {
                        Toast.makeText(ConfirmationVisitActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}