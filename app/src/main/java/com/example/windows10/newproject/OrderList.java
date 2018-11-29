package com.example.windows10.newproject;

import com.example.windows10.newproject.Model.OrderRecord;

import java.util.ArrayList;
import java.util.List;

public class OrderList {

    List<OrderRecord> cart;

    public OrderList(List<OrderRecord> cart) {
        this.cart = cart;
    }

    public OrderList() {
        new ArrayList<>();
    }

    public List<OrderRecord> getCart() {
        return cart;
    }

    public void setCart(List<OrderRecord> cart) {
        this.cart = cart;
    }
}
