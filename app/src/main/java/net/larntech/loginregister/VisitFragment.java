package net.larntech.loginregister;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class VisitFragment extends Fragment {

    public String name;
    public TextView group;

    public VisitFragment() {
        // Required empty public constructor\

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_visit, container, false);
        group = view.findViewById(R.id.group);
        Bundle bundle = getArguments();
        if (bundle != null) {
            name = bundle.getString("username");
        }
        group.setText(name);
        return view;
    }
}