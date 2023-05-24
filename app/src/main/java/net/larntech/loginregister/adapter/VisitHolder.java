package net.larntech.loginregister.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.larntech.loginregister.R;

public class VisitHolder extends RecyclerView.ViewHolder {

    TextView lastname, firstname, patronymic, status;

public VisitHolder(@NonNull View itemView) {
    super(itemView);
        lastname = itemView.findViewById(R.id.studentListItem_lastname);
        firstname = itemView.findViewById(R.id.studentListItem_name);
        patronymic = itemView.findViewById(R.id.studentListItem_patronymic);
        status = itemView.findViewById(R.id.studentListItem_status);
    }
}