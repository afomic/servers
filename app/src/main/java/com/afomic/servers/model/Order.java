package com.afomic.servers.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

/**
 * Created by rechael on 9/15/2017.
 */

public class Order implements Parcelable {
    public static final int NEW = 0;
    public static final int SERVED = 1;
    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
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

    protected Order(Parcel in) {
        mStatus = in.readInt();
        mName = in.readString();
        mQuantity = in.readInt();
        mUnitName = in.readString();
        mKey = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mStatus);
        dest.writeString(mName);
        dest.writeInt(mQuantity);
        dest.writeString(mUnitName);
        dest.writeString(mKey);
    }
}
