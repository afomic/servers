package com.afomic.servers.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by rechael on 9/15/2017.
 * this is the model class for Table
 */

public class Table implements Parcelable {
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

    protected Table(Parcel in) {
        mName = in.readString();
        mWaiterName = in.readString();
        mStatus = in.readInt();
    }

    public static final Creator<Table> CREATOR = new Creator<Table>() {
        @Override
        public Table createFromParcel(Parcel in) {
            return new Table(in);
        }

        @Override
        public Table[] newArray(int size) {
            return new Table[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mWaiterName);
        dest.writeInt(mStatus);
    }
}
