package com.example.rathana.sharpreference;

public class User {

    private int id;
    private String name;
    private String password;
    private boolean isLogin;

    public User() { }

    public User(int id,String name, String password,boolean isLogin) {
        this.id=id;
        this.name = name;
        this.password = password;
        this.isLogin=isLogin;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
