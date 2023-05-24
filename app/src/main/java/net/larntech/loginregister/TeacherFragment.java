package net.larntech.loginregister;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import net.larntech.loginregister.adapter.ScheduleAdapter;
import net.larntech.loginregister.adapter.ScheduleTeacherAdapter;
import net.larntech.loginregister.models.Schedule;
import net.larntech.loginregister.retrofit.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TeacherFragment extends Fragment {
    String idTeacher;
    String token;
    String weekdaySchedule;
    RecyclerView recyclerView;
    Spinner weekday;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher, container, false);

        recyclerView = view.findViewById(R.id.scheduleList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getArguments();
        if (bundle != null) {
            idTeacher = bundle.getString("idTeacher");
            token = bundle.getString("token");
        }
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final View v = view;

        weekday = view.findViewById(R.id.weekday);
        weekday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.weekday);
                weekdaySchedule = choose[selectedItemPosition];
                getLessons();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void getLessons() {
        Call<List<Schedule>> getScheduleCall = ApiClient.getScheduleService().getSchedulesByTeacher(Integer.parseInt(idTeacher), weekdaySchedule, "Bearer " + token);
        getScheduleCall.enqueue(new Callback<List<Schedule>>() {
            @Override
            public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                populateListView(response.body());
            }

            @Override
            public void onFailure(Call<List<Schedule>> call, Throwable t) {
                //Toast.makeText(getActivity(), "Failed " + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateListView(List<Schedule> scheduleList) {
        ScheduleTeacherAdapter.OnScheduleClickListener scheduleClickListener = new ScheduleTeacherAdapter.OnScheduleClickListener() {
            @Override
            public void onScheduleClick(Schedule schedule, int position) {

            }
        };
        ScheduleTeacherAdapter scheduleTeacherAdapter = new ScheduleTeacherAdapter(scheduleList, scheduleClickListener);
        recyclerView.setAdapter(scheduleTeacherAdapter);
    }
}