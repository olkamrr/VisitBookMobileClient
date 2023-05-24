package net.larntech.loginregister.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.larntech.loginregister.R;
import net.larntech.loginregister.models.Schedule;

import java.util.List;

public class ScheduleTeacherAdapter extends RecyclerView.Adapter<ScheduleTeacherHolder> {

    private List<Schedule> scheduleList;

    public interface OnScheduleClickListener{
        void onScheduleClick(Schedule schedule, int position);
    }

    private final ScheduleTeacherAdapter.OnScheduleClickListener onClickListener;

    public ScheduleTeacherAdapter(List<Schedule> scheduleList, ScheduleTeacherAdapter.OnScheduleClickListener onClickListener) {
        this.scheduleList = scheduleList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ScheduleTeacherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_schedule_teacher, parent, false);
        return new ScheduleTeacherHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ScheduleTeacherHolder holder, @SuppressLint("RecyclerView") int position) {
        Schedule schedule = scheduleList.get(position);
        holder.name.setText(schedule.getName());
        holder.week.setText(schedule.getWeek());
        holder.type.setText(schedule.getType());
        holder.group.setText(schedule.getGroupId().getCode());
        holder.semester.setText(schedule.getSemester() + " семестр");
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
