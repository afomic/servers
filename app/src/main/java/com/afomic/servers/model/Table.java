package com.afomic.servers.model;

import java.util.ArrayList;

/**
 * Created by rechael on 9/15/2017.
 * this is the model class for Table
 */

public class Table {
    public static final int NEW=0;
    public static final int ORDER_TAKEN=1;
    public static final int SERVED=1;
    private ArrayList<Order> mTableOrders;
    private String mName;
    private String mWaiterName;
    private int mStatus;

    public Table(String name,ArrayList<Order> tableOrders,String waiterName,int status){
        mName=name;
        mTableOrders=tableOrders;
        mWaiterName=waiterName;
        mStatus=status;

    }
    public Table(String name){
        mStatus=NEW;
        mTableOrders=new ArrayList<>();
        mName=name;
        mWaiterName=null;
    }

    public ArrayList<Order> getTableOrders() {
        return mTableOrders;
    }

    public void setTableOrders(ArrayList<Order> tableOrders) {
        mTableOrders = tableOrders;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getWaiterName() {
        return mWaiterName;
    }

    public void setWaiterName(String waiterName) {
        mWaiterName = waiterName;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }
}
