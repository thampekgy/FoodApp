package com.example.windows10.newproject.Model;

public class Member {

    private String Name;
    private String Email;
    private String Password;
    private String Contact;
    private int memPoint;

    public Member(String name, String email, String password, String contact, int memPoint) {
        Name = name;
        Email = email;
        Password = password;
        Contact = contact;
        this.memPoint = memPoint;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public int getMemPoint() {
        return memPoint;
    }

    public void setMemPoint(int memPoint) {
        this.memPoint = memPoint;
    }
}
