package com.example.windows10.newproject.Model;

public class Complain {

    String name;
    String phone;
    String email;
    String complain;

    public Complain(String name, String phone, String email, String complain) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.complain = complain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }
}
