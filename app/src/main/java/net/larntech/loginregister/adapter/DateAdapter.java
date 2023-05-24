package net.larntech.loginregister.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.larntech.loginregister.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DateAdapter extends RecyclerView.Adapter<DateHolder> {

    private List<Date> dateList;

    public interface OnDateClickListener{
        void onDateClick(Date date, int position);
    }

    private final OnDateClickListener onClickListener;

    public DateAdapter(List<Date> dateList, OnDateClickListener onClickListener) {
        this.dateList = dateList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public DateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_visit_item, parent, false);
        return new DateHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DateHolder dateHolder, @SuppressLint("RecyclerView") int position) {
        Date date = dateList.get(position);
        String dateString = new SimpleDateFormat("EEE, d MMM yyyy").format(date);
        dateHolder.date.setText(dateString);
        dateHolder.itemView.setOnClickListener(v -> {
            // вызываем метод слушателя, передавая ему данные
            onClickListener.onDateClick(date, position);
        });
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }
}
