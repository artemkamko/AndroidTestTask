package com.dwajot.androidtesttask.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Artem on 21.07.2017.
 */

@RealmClass
public class Info extends RealmObject implements Parcelable {

    @PrimaryKey
    private String id;
    private String position;
    @SerializedName("from_city")
    private City fromCity;
    @SerializedName("to_city")
    private City toCity;
    private String price;
    @SerializedName("bus_id")
    private String busId;
    @SerializedName("reservation_count")
    private String reservation;
    @SerializedName("from_date")
    private String fromDate;
    @SerializedName("from_time")
    private String fromTime;
    @SerializedName("from_info")
    private String fromInfo;
    @SerializedName("to_date")
    private String toDate;
    @SerializedName("to_time")
    private String toTime;
    @SerializedName("to_info")
    private String toInfo;
    private String info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public City getFromCity() {
        return fromCity;
    }

    public void setFromCity(City fromCity) {
        this.fromCity = fromCity;
    }

    public City getToCity() {
        return toCity;
    }

    public void setToCity(City toCity) {
        this.toCity = toCity;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getReservation() {
        return reservation;
    }

    public void setReservation(String reservation) {
        this.reservation = reservation;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getFromInfo() {
        return fromInfo;
    }

    public void setFromInfo(String fromInfo) {
        this.fromInfo = fromInfo;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getToInfo() {
        return toInfo;
    }

    public void setToInfo(String toInfo) {
        this.toInfo = toInfo;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.position);
        dest.writeParcelable(this.fromCity, flags);
        dest.writeParcelable(this.toCity, flags);
        dest.writeString(this.price);
        dest.writeString(this.busId);
        dest.writeString(this.reservation);
        dest.writeString(this.fromDate);
        dest.writeString(this.fromTime);
        dest.writeString(this.fromInfo);
        dest.writeString(this.toDate);
        dest.writeString(this.toTime);
        dest.writeString(this.toInfo);
        dest.writeString(this.info);
    }

    public Info() {
    }

    protected Info(Parcel in) {
        this.id = in.readString();
        this.position = in.readString();
        this.fromCity = in.readParcelable(City.class.getClassLoader());
        this.toCity = in.readParcelable(City.class.getClassLoader());
        this.price = in.readString();
        this.busId = in.readString();
        this.reservation = in.readString();
        this.fromDate = in.readString();
        this.fromTime = in.readString();
        this.fromInfo = in.readString();
        this.toDate = in.readString();
        this.toTime = in.readString();
        this.toInfo = in.readString();
        this.info = in.readString();
    }

    public static final Parcelable.Creator<Info> CREATOR = new Parcelable.Creator<Info>() {
        @Override
        public Info createFromParcel(Parcel source) {
            return new Info(source);
        }

        @Override
        public Info[] newArray(int size) {
            return new Info[size];
        }
    };
}
