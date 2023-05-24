package net.larntech.loginregister.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.larntech.loginregister.R;

public class DateHolder extends RecyclerView.ViewHolder {

    TextView date;

    public DateHolder(@NonNull View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.visitListItem_data);
    }
}
