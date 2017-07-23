package com.dwajot.androidtesttask.db;

import com.dwajot.androidtesttask.model.Info;

import java.util.List;

import io.realm.Realm;

/**
 * Created by Artem on 21.07.2017.
 */

public class DBHelper {
    private Realm realm;

    public DBHelper(Realm realm) {
        this.realm = realm;
    }

    public void insertListInfo(List<Info> list) {
        realm.beginTransaction();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setPosition(String.valueOf(i));
            realm.copyToRealmOrUpdate(list.get(i));
        }
        realm.commitTransaction();
        realm.close();
    }

    public Info getInfoObject(int id) {
        return realm.where(Info.class).equalTo("position", String.valueOf(id)).findFirst();
    }

    public List<Info> getInfoObjects() {
        return realm.where(Info.class).findAll();
    }

    public boolean isDBEmpty() {
        Info result = realm.where(Info.class).findFirst();
        return result == null;
    }

}
