package com.example.project;

public class MyCredential {
    String Login;
    String password;

    public MyCredential(String login, String password) {
        Login = login;
        this.password = password;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
