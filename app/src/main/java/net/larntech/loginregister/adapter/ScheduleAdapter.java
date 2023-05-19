package net.larntech.loginregister.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.larntech.loginregister.R;
import net.larntech.loginregister.models.Schedule;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleHolder> {

    private List<Schedule> scheduleList;

    public interface OnScheduleClickListener{
        void onScheduleClick(Schedule schedule, int position);
    }

    private final OnScheduleClickListener onClickListener;

    public ScheduleAdapter(List<Schedule> scheduleList, OnScheduleClickListener onClickListener) {
        this.scheduleList = scheduleList;
        this.onClickListener = onClickListener;
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
        // обработка нажатия
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                // вызываем метод слушателя, передавая ему данные
                onClickListener.onScheduleClick(schedule, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }
}
