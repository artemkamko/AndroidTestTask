package com.dwajot.androidtesttask.model;

import android.os.Parcel;
import android.os.Parcelable;


import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by Artem on 21.07.2017.
 */

@RealmClass
public class City extends RealmObject implements Parcelable {

    @SerializedName("highlight")
    private String highLight;
    private String id;
    private String name;

    public String getHighLight() {
        return highLight;
    }

    public void setHighLight(String highLight) {
        this.highLight = highLight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.highLight);
        dest.writeString(this.id);
        dest.writeString(this.name);
    }

    public City() {
    }

    protected City(Parcel in) {
        this.highLight = in.readString();
        this.id = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() {
        @Override
        public City createFromParcel(Parcel source) {
            return new City(source);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };
}
