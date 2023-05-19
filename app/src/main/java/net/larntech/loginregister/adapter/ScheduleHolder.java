package net.larntech.loginregister.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.larntech.loginregister.R;

public class ScheduleHolder extends RecyclerView.ViewHolder {

    TextView name, week, teacher, type;

    public ScheduleHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.listItem_name);
        week = itemView.findViewById(R.id.listItem_week);
        teacher = itemView.findViewById(R.id.listItem_teacher);
        type = itemView.findViewById(R.id.listItem_type);
    }
}
