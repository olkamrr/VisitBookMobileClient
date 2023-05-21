package net.larntech.loginregister.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.larntech.loginregister.R;

public class StudentVisitHolder extends RecyclerView.ViewHolder {

    TextView lastname, name, patronymic, id;
    RadioButton n, nb, be;

    public StudentVisitHolder(@NonNull View itemView) {
        super(itemView);
        lastname = itemView.findViewById(R.id.studentListItem_lastname);
        name = itemView.findViewById(R.id.studentListItem_name);
        patronymic = itemView.findViewById(R.id.studentListItem_patronymic);
        id = itemView.findViewById(R.id.studentListItem_id);
        n = itemView.findViewById(R.id.n);
        nb = itemView.findViewById(R.id.nb);
        be = itemView.findViewById(R.id.be);
    }
}
