package net.larntech.loginregister.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.larntech.loginregister.R;

public class StudentHolder extends RecyclerView.ViewHolder {

        TextView lastname, name, patronymic, id;

        public StudentHolder(@NonNull View itemView) {
                super(itemView);
                lastname = itemView.findViewById(R.id.studentListItem_lastname);
                name = itemView.findViewById(R.id.studentListItem_name);
                patronymic = itemView.findViewById(R.id.studentListItem_patronymic);
                id = itemView.findViewById(R.id.studentListItem_id);
        }
}
