package net.larntech.loginregister.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.larntech.loginregister.R;
import net.larntech.loginregister.models.Schedule;
import net.larntech.loginregister.models.Student;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleHolder> {

    private List<Schedule> scheduleList;

    public ScheduleAdapter(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    @NonNull
    @Override
    public ScheduleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_schedule_item, parent, false);
        return new ScheduleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleHolder holder, int position) {
        Schedule schedule = scheduleList.get(position);
        holder.name.setText(schedule.getName());
        holder.week.setText(schedule.getWeek());
        holder.type.setText(schedule.getType());
        holder.teacher.setText(schedule.getTeacher().getLastName() + " " + schedule.getTeacher().getFirstName() + " " + schedule.getTeacher().getPatronymic());
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }
}
