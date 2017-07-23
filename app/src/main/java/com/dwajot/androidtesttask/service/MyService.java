package com.dwajot.androidtesttask.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.dwajot.androidtesttask.R;
import com.dwajot.androidtesttask.db.DBHelper;
import com.dwajot.androidtesttask.model.InfoList;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Artem on 21.07.2017.
 */

public class MyService extends Service {

    private ExecutorService es;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        es = Executors.newFixedThreadPool(1);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ServiceTask serviceTask = new ServiceTask(startId);
        es.execute(serviceTask);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private class ServiceTask implements Runnable {
        private int startId;
        private DBHelper dbHelper;

        ServiceTask(int startId) {
            this.startId = startId;
            dbHelper = new DBHelper(Realm.getDefaultInstance());
        }

        @Override
        public void run() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(getString(R.string.routeUrl))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RestApi service = retrofit.create(RestApi.class);
            Call<InfoList> call = service.getInfo();

            call.enqueue(new Callback<InfoList>() {
                @Override
                public void onResponse(Call<InfoList> call, Response<InfoList> response) {
                    if (response.isSuccessful() && response.body().getData().size() != 0) {
                        InfoList list = response.body();
                        dbHelper.insertListInfo(list.getData());
                        EventBus.getDefault().post(list.getData());
                    } else if (response.isSuccessful() && response.body().getData().size() == 0) {
                        EventBus.getDefault().post(getString(R.string.listIsEmpty));
                    }
                    stopSelfResult(startId);
                }

                @Override
                public void onFailure(Call<InfoList> call, Throwable t) {
                    EventBus.getDefault().post(t.getMessage());
                    stopSelfResult(startId);
                }
            });
        }
    }
}
