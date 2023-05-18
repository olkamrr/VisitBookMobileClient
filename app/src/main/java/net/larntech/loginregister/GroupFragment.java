package net.larntech.loginregister;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.larntech.loginregister.adapter.StudentAdapter;
import net.larntech.loginregister.models.Student;
import net.larntech.loginregister.retrofit.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupFragment extends Fragment {

    private RecyclerView recyclerView;
    String idGroup;
    String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group, container, false);
        recyclerView = view.findViewById(R.id.studentList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Bundle bundle = getArguments();
        if (bundle != null) {
            idGroup = bundle.getString("idGroup");
            token = bundle.getString("token");
            getStudent();
        }
        // Inflate the layout for this fragment
        return view;
    }

    public void getStudent() {
        Call<List<Student>> getStudentCall = ApiClient.getStudentService().getStudent(Integer.parseInt(idGroup), "Bearer " + token);
        getStudentCall.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                populateListView(response.body());
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
//                Toast.makeText(GroupFragment.this, "Failed ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateListView(List<Student> studentList) {
        StudentAdapter studentAdapter = new StudentAdapter(studentList);
        recyclerView.setAdapter(studentAdapter);
    }
}