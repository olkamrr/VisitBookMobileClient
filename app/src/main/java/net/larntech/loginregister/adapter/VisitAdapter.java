package net.larntech.loginregister.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.larntech.loginregister.R;
import net.larntech.loginregister.models.Student;
import net.larntech.loginregister.models.Visit;

import java.util.List;

public class VisitAdapter extends RecyclerView.Adapter<VisitHolder> {

    private List<Visit> visitList;

    public VisitAdapter(List<Visit> visitList) {
        this.visitList = visitList;
    }

    @NonNull
    @Override
    public VisitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_student_visit_item, parent, false);
        return new VisitHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitHolder holder, int position) {
        Visit visit = visitList.get(position);
        holder.lastname.setText(visit.getStudentId().getLastName());
        holder.firstname.setText(visit.getStudentId().getFirstName());
        holder.patronymic.setText(visit.getStudentId().getPatronymic());
        if (visit.getStatus().equals("П")) {
            holder.status.setText(" ");
        } else holder.status.setText(visit.getStatus());
    }

    @Override
    public int getItemCount() {
        return visitList.size();
    }
}
