package com.afomic.servers.model;

import java.util.Locale;

/**
 * Created by rechael on 9/15/2017.
 */

public class Order {
    private int mStatus;
    private String mName;
    private int mQuantity;
    private String mUnitName;
    public static final int NEW=0;
    public static final int SERVED=1;

    public Order(String name,int quantity,String unitName,int status){
        mStatus=status;
        mName=name;
        mUnitName=unitName;
        mQuantity=quantity;
    }

    public Order(String name,int quantity,String unitName){
        mName=name;
        mUnitName=unitName;
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

    public String getUnitName() {
        return mUnitName;
    }

    public void setUnitName(String unitName) {
        mUnitName = unitName;
    }
    public String getQuantityString(){
        return String.format(Locale.ENGLISH,"%d %s",mQuantity,mUnitName);
    }
}
