package net.larntech.loginregister.models;

import java.util.Date;

public class Visit {
    private int id;
    private String date;
    private String status;
    private Schedule lessonId;
    private Student studentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Schedule getLessonId() {
        return lessonId;
    }

    public void setLessonId(Schedule lessonId) {
        this.lessonId = lessonId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", lessonId=" + lessonId +
                ", studentId=" + studentId +
                '}';
    }
}
