package net.larntech.loginregister;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class VisitFragment extends Fragment {

    public String code;
    public String fullName;
    public TextView group;
    public TextView elder;

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
        }
        group.setText(code);
        elder.setText(fullName);
        return view;
    }
}