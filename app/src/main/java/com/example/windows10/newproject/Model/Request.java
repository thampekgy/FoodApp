package com.example.windows10.newproject.Model;

import com.example.windows10.newproject.OrderList;

import java.util.List;

public class Request {
    private String phone;
    private String name;
    private String address;
    //private String total;
    OrderList foods; //list of food order

    public Request(){

    }

    public Request(String phone, String name, String address, OrderList foods) {
        this.phone = phone;
        this.name = name;
        this.address = address;
        //this.total = total;
        this.foods = foods;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /*public String getTotal() {
        return total;
    }*/

    /*public void setTotal(String total) {
        this.total = total;
    }*/

    public OrderList getFoods() {
        return foods;
    }

    public void setFoods(OrderList foods) {
        this.foods = foods;
    }
}
