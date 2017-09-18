package com.afomic.servers.model;

import java.util.Locale;

/**
 * Created by rechael on 9/15/2017.
 */

public class Order {
    public static final int NEW = 0;
    public static final int SERVED = 1;
    private int mStatus;
    private String mName;
    private int mQuantity;
    private String mUnitName;
    private String mKey;

    public Order(){
    }

    public Order(String key,String name,int quantity,String unitName){
        mName=name;
        mUnitName=unitName;
        mQuantity=quantity;
        mKey=key;
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

    public String getUnitName() {
        return mUnitName;
    }

    public void setUnitName(String unitName) {
        mUnitName = unitName;
    }
    public String getQuantityString(){
        return String.format(Locale.ENGLISH,"%d %s",mQuantity,mUnitName);
    }


    @Override
    public String toString() {
        return "Order{" +
                "mStatus=" + mStatus +
                ",\n mName='" + mName + '\'' +
                ", \nmQuantity=" + mQuantity +
                ", \nmUnitName='" + mUnitName + '\'' +
                ",\n mKey='" + mKey + '\'' +
                '}';
    }
}
