package net.larntech.loginregister;

public class Group {
    private int id;
    private String name;
    private String code;
    public LoginResponse user;

    public LoginResponse getUser() {
        return user;
    }

    public void setUser(LoginResponse user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", user=" + user +
                '}';
    }
}
