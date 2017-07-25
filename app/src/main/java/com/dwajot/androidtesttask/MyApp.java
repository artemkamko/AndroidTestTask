package com.dwajot.androidtesttask;

import android.app.Application;

import com.dwajot.androidtesttask.util.MySharedPreference;

import io.realm.Realm;

/**
 * Created by Artem on 21.07.2017.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        MySharedPreference mySharedPreference = new MySharedPreference(getApplicationContext());
        mySharedPreference.setPosition(MySharedPreference.DEFAULT_POSITION);
    }
}
