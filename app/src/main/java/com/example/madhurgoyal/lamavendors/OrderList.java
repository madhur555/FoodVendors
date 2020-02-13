package com.example.madhurgoyal.lamavendors;

public class OrderList {
    String total;
    String customerid;
    String status;
    String key;
    String vid;

    public OrderList() {
    }

    public OrderList(String customerid, String total, String status) {
        this.total = total;
        this.customerid = customerid;
        this.status = status;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }
}
