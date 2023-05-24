package net.larntech.loginregister.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.larntech.loginregister.R;

public class ScheduleTeacherHolder extends RecyclerView.ViewHolder {

    TextView name, week, group, type, semester;

    public ScheduleTeacherHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.listItem_name);
        week = itemView.findViewById(R.id.listItem_week);
        group = itemView.findViewById(R.id.listItem_group);
        type = itemView.findViewById(R.id.listItem_type);
        semester = itemView.findViewById(R.id.listItem_semester);
    }
}
