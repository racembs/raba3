package com.tonikamitv.loginregister;

/**
 * Created by RBS on 30-Jan-18.
 */

public class User {
    private int id;
    private  String username;
    private  String mail;
    private  String password;
    private  String university;
    private  String remember;

    public User() {
    }

    public User(int id, String username, String mail, String password, String university,String remember) {
        this.id = id;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.university = university;
        this.remember=remember;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getRemember() {
        return remember;
    }

    public void setRemember(String remember) {
        this.remember = remember;
    }
}
