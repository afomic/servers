package com.afomic.servers.waiters;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by r4sh33d on 9/18/17.
 */

public class FoodModel implements Parcelable {
    public static final Creator<FoodModel> CREATOR = new Creator<FoodModel>() {
        @Override
        public FoodModel createFromParcel(Parcel in) {
            return new FoodModel(in);
        }

        @Override
        public FoodModel[] newArray(int size) {
            return new FoodModel[size];
        }
    };
    public String name;
    public int qauntity;
    public String unitName;

    public FoodModel(String name, int qauntity, String unitName) {
        this.name = name;
        this.qauntity = qauntity;
        this.unitName = unitName;
    }

    protected FoodModel(Parcel in) {
        name = in.readString();
        qauntity = in.readInt();
        unitName = in.readString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQauntity(int qauntity) {
        this.qauntity = qauntity;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(qauntity);
        dest.writeString(unitName);

    }
}
