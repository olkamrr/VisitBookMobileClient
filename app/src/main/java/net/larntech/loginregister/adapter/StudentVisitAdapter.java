package net.larntech.loginregister.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
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
    int selectedPosition = -1;
    AdapterView.OnItemClickListener onItemClickListener;

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
        holder.n.setOnClickListener(view -> {
            Toast.makeText(context, studentsList.get(position).getLastName() + " Н", Toast.LENGTH_SHORT).show();
        });
        holder.nb.setOnClickListener(view -> {
            Toast.makeText(context, studentsList.get(position).getLastName() + " НБ", Toast.LENGTH_SHORT).show();
        });
        holder.be.setOnClickListener(view -> {
            Toast.makeText(context, studentsList.get(position).getLastName() + " есть", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }
}