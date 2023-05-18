package net.larntech.loginregister;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ScheduleFragment extends Fragment {

    Button scheduleAdd;
    String idGroup;
    String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if (bundle != null) {
            idGroup = bundle.getString("idGroup");
            token = bundle.getString("token");
        }

        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        scheduleAdd = view.findViewById(R.id.scheduleList);
        scheduleAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ScheduleFormActivity.class);
                intent.putExtra("token", token);
                intent.putExtra("idGroup", idGroup);
                startActivity(intent);
            }
        });

        return view;
    }
}