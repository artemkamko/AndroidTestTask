package com.dwajot.androidtesttask.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.dwajot.androidtesttask.R;

/**
 * Created by Artem on 24.07.2017.
 */

public class MySharedPreference {
    public static final int DEFAULT_POSITION = -1;

    private SharedPreferences preferences;
    private Context context;

    public MySharedPreference(Context context) {
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setPosition(int position) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(context.getString(R.string.position), position).apply();
    }

    public int getPosition() {
        return preferences.getInt(context.getString(R.string.position), DEFAULT_POSITION);
    }
}
