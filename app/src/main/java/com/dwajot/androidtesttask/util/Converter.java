package com.dwajot.androidtesttask.util;

import android.content.Context;

import com.dwajot.androidtesttask.R;

/**
 * Created by Artem on 21.07.2017.
 */

public class Converter {
    private Context context;

    public Converter(Context context) {
        this.context = context;
    }

    public String makeTvFromCity(String fromDate, String fromTime, String fromCityName) {
        return context.getString(R.string.infoFromCity) + fromDate
                + context.getString(R.string.from) + fromCityName
                + context.getString(R.string.in) + fromTime;
    }

    public String makeTvToCity (String toDate, String toTime, String toCityName) {
        return context.getString(R.string.infoToCity) + toDate
                + context.getString(R.string.in) + toCityName
                + context.getString(R.string.in) + toTime;
    }

    public String makeTvCityId (String cityId, String highlight) {
        return context.getString(R.string.infoCityId) + cityId
                + context.getString(R.string.infoHighlight) + highlight;
    }

    public String makeTvCityInfo(String info) {
        return context.getString(R.string.cityInfo) + info;
    }

    public String makeTvStations (String stations) {
        return context.getString(R.string.infoStations) + stations;
    }

    //Main IU methods

    public String makeTvTimeDispatch(String date, String time) {
        return context.getString(R.string.timeDispatch) + date
                + context.getString(R.string.in) + time;
    }

    public String makeTvTimeArrival(String date, String time) {
        return context.getString(R.string.timeArrival) + date
                + context.getString(R.string.in) + time;
    }

    public String makeTvItemIdAndCity(int id, String fromCity, String toCity) {
        return String.format("%s%s", id, ". ") + fromCity + context.getString(R.string.slash) + toCity;
    }
}
