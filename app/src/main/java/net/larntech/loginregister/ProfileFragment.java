package net.larntech.loginregister;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
    TextView name;
    String token;
    String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        name = view.findViewById(R.id.username);

        Bundle bundle = getArguments();
        if (bundle != null) {
            username = bundle.getString("username");
            token = bundle.getString("token");
        }


        name.setText(username);

        return view;
    }
}