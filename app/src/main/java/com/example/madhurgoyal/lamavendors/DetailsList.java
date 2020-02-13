package com.example.madhurgoyal.lamavendors;

public class DetailsList {
    String qitem;
    String qty;

    public DetailsList() {
    }

    public DetailsList(String qitem, String qty) {
        this.qitem = qitem;
        this.qty = qty;
    }

    public String getQitem() {
        return qitem;
    }

    public void setQitem(String qitem) {
        this.qitem = qitem;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
