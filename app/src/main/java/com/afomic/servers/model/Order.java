package com.afomic.servers.model;

/**
 * Created by rechael on 9/15/2017.
 */

public class Order {
    private int mStatus;
    private String mName;
    private int mQuantity;
    public static final int NEW=0;
    public static final int SERVED=1;

    public Order(String name,int quantity,int status){
        mStatus=status;
        mName=name;
        mQuantity=quantity;
    }

    public Order(String name,int quantity){
        mName=name;
        mQuantity=quantity;
        mStatus=NEW;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }
}
