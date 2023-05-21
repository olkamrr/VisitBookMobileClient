package net.larntech.loginregister;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;

public class VisitFragment extends Fragment {

    public String code;
    public String fullName;
    public TextView group;
    public TextView elder;
    String mYear;
    String mDay;
    String mMonth;
    String token;
    String idGroup;
    String weekday;

    public VisitFragment() {
        // Required empty public constructor\

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_visit, container, false);
        group = view.findViewById(R.id.group);
        elder = view.findViewById(R.id.elder);
        Bundle bundle = getArguments();
        if (bundle != null) {
            code = bundle.getString("code");
            fullName = bundle.getString("fullName");
            token = bundle.getString("token");
            idGroup = bundle.getString("idGroup");
        }
        group.setText(code);
        elder.setText(fullName);

        CalendarView calendarView = view.findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            switch (dayOfWeek ) {
                case Calendar.MONDAY:
                    weekday = "Понедельник";
                    break;
                case Calendar.TUESDAY:
                    weekday = "Вторник";
                    break;
                case Calendar.WEDNESDAY:
                    weekday = "Среда";
                    break;
                case Calendar.THURSDAY:
                    weekday = "Четверг";
                    break;
                case Calendar.FRIDAY:
                    weekday = "Пятница";
                    break;
                case Calendar.SATURDAY:
                    weekday = "Суббота";
                    break;
                case Calendar.SUNDAY:
                    weekday = "Воскресенье";
                    break;
            }
            mYear = String.valueOf(year);
            mMonth = String.valueOf(month + 1);
            mDay = String.valueOf(dayOfMonth);
            Intent intent = new Intent(getActivity(), VisitActivity.class);
            intent.putExtra("token", token);
            intent.putExtra("day", mDay);
            intent.putExtra("month", mMonth);
            intent.putExtra("year", mYear);
            intent.putExtra("weekday", weekday);
            intent.putExtra("idGroup", idGroup);
            startActivity(intent);
        });
        return view;
    }
}