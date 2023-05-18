package net.larntech.loginregister.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.larntech.loginregister.R;
import net.larntech.loginregister.models.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentHolder> {

    private List<Student> studentsList;
    int count;

    public StudentAdapter(List<Student> studentsList) {
        this.studentsList = studentsList;
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_student_item, parent, false);
        return new StudentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {
        Student student = studentsList.get(position);
        count = studentsList.indexOf(student) + 1;
        holder.lastname.setText(student.getLastName());
        holder.name.setText(student.getFirstName());
        holder.patronymic.setText(student.getPatronymic());
        holder.id.setText(String.valueOf(count));
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }
}
