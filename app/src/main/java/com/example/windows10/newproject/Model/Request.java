package com.example.windows10.newproject.Model;

import com.example.windows10.newproject.OrderList;

import java.util.List;

public class Request {
    private String phone;
    private String name;
    private String address;
    private String total;
    OrderList foods; //list of food order
    private String foodStatus;
    private String deliveredTime;

    public Request(){

    }

    public Request(String phone, String name, String address, String total, OrderList foods, String foodStatus, String deliveredTime) {
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.total = total;
        this.foods = foods;
        this.foodStatus = foodStatus;
        this.deliveredTime = deliveredTime;
    }

    public String getDeliveredTime() {
        return deliveredTime;
    }

    public void setDeliveredTime(String deliveredTime) {
        this.deliveredTime = deliveredTime;
    }

    public String getFoodStatus() {
        return foodStatus;
    }

    public void setFoodStatus(String foodStatus) {
        this.foodStatus = foodStatus;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public OrderList getFoods() {
        return foods;
    }

    public void setFoods(OrderList foods) {
        this.foods = foods;
    }
}
