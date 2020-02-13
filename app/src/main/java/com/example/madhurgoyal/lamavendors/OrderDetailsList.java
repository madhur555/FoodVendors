package com.example.madhurgoyal.lamavendors;

public class OrderDetailsList {
    String item;
    String qty;

    public OrderDetailsList() {
    }

    public OrderDetailsList(String item, String qty) {
        this.item = item;
        this.qty = qty;
    }

    public String getItem() {
        return item;
    }

    public String getQty() {
        return qty;
    }
}
