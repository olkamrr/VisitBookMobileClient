package net.larntech.loginregister.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.larntech.loginregister.R;
import net.larntech.loginregister.models.Student;

import java.util.List;

public class StudentVisitAdapter extends RecyclerView.Adapter<StudentVisitHolder> {

    private List<Student> studentsList;
    private LayoutInflater inflater;
    private Context context;

    public StudentVisitAdapter(Context context, List<Student> studentsList) {
        inflater = LayoutInflater.from(context);
        this.studentsList = studentsList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentVisitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_student_visit, parent, false);*/
        View view = inflater.inflate(R.layout.list_student_visit, parent, false);
        return new StudentVisitHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentVisitHolder holder, int position) {
        Student student = studentsList.get(position);
        holder.lastname.setText(student.getLastName());
        holder.name.setText(student.getFirstName());
        holder.patronymic.setText(student.getPatronymic());
        holder.id.setText(String.valueOf(student.getId()));
        /*//holder.n.setTag(position);
        holder.n.setOnClickListener(view -> {
            //Integer pos = (Integer) holder.n.getTag();
            Toast.makeText(context, studentsList.get(position).getLastName(), Toast.LENGTH_SHORT).show();
        });*/
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }
}