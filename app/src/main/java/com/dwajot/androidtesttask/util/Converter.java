package com.dwajot.androidtesttask.util;

import android.content.Context;

import com.dwajot.androidtesttask.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Artem on 21.07.2017.
 */

public class Converter {
    private Context context;


    public Converter(Context context) {
        this.context = context;
    }

    public String makeTvTime(String time) {
        SimpleDateFormat dt = new SimpleDateFormat(context.getString(R.string.startTime), Locale.US);
        try {
            Date newDate = dt.parse(time);
            dt = new SimpleDateFormat(context.getString(R.string.convertedTime), Locale.US);
            return dt.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String makeTvDate(String date) {
        SimpleDateFormat dt = new SimpleDateFormat(context.getString(R.string.startDate), Locale.US);
        try {
            Date newDate = dt.parse(date);
            dt = new SimpleDateFormat(context.getString(R.string.convertedDate), Locale.US);
            return dt.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String makeTvFromCity(String fromDate, String fromTime, String fromCityName) {
        return context.getString(R.string.infoFromCity) + makeTvDate(fromDate)
                + context.getString(R.string.from) + fromCityName
                + context.getString(R.string.in) + makeTvTime(fromTime);
    }

    public String makeTvToCity(String toDate, String toTime, String toCityName) {
        return context.getString(R.string.infoToCity) + makeTvDate(toDate)
                + context.getString(R.string.in) + toCityName
                + context.getString(R.string.in) + makeTvTime(toTime);
    }

    public String makeTvCityId(String cityId, String highlight) {
        return context.getString(R.string.infoCityId) + cityId
                + context.getString(R.string.infoHighlight) + highlight;
    }

    public String makeTvCityInfo(String info) {
        return context.getString(R.string.cityInfo) + info;
    }

    public String makeTvStations(String stations) {
        return context.getString(R.string.infoStations) + stations;
    }
}
