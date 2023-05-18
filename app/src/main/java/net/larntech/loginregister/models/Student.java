package net.larntech.loginregister.models;

import net.larntech.loginregister.models.Group;

public class Student {
    private int id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private boolean elder;
    private Group groupId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public boolean isElder() {
        return elder;
    }

    public void setElder(boolean elder) {
        this.elder = elder;
    }

    public Group getGroupId() {
        return groupId;
    }

    public void setGroupId(Group groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", elder=" + elder +
                ", groupId=" + groupId +
                '}';
    }
}
