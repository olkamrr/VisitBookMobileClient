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
import android.widget.Button;
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

public class ScheduleFragment extends Fragment {

    Button scheduleAdd;
    String idGroup;
    String token;
    Spinner semester;
    Spinner weekday;
    private RecyclerView recyclerView;
    int semesterSchedule;
    String weekdaySchedule;
    TextView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        recyclerView = view.findViewById(R.id.scheduleList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getArguments();
        if (bundle != null) {
            idGroup = bundle.getString("idGroup");
            token = bundle.getString("token");
        }

        scheduleAdd = view.findViewById(R.id.scheduleList);
        scheduleAdd.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), ScheduleFormActivity.class);
            intent.putExtra("token", token);
            intent.putExtra("idGroup", idGroup);
            startActivity(intent);
        });

        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final View v = view;

        semester = view.findViewById(R.id.semester);
        weekday = view.findViewById(R.id.weekday);
        list = view.findViewById(R.id.list);
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
        Call<List<Schedule>> getScheduleCall = ApiClient.getScheduleService().getLessons(Integer.parseInt(idGroup), semesterSchedule, weekdaySchedule, "Bearer " + token);
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
        // определяем слушателя нажатия элемента в списке
        ScheduleAdapter.OnScheduleClickListener scheduleClickListener = new ScheduleAdapter.OnScheduleClickListener() {
            @Override
            public void onScheduleClick(Schedule schedule, int position) {
                Intent intent = new Intent(getActivity(), ScheduleFormActivity.class);
                intent.putExtra("token", token);
                intent.putExtra("idLesson", String.valueOf(schedule.getId()));
                startActivity(intent);
            }
        };
        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(scheduleList, scheduleClickListener);
        recyclerView.setAdapter(scheduleAdapter);
    }
}