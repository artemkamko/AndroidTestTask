package com.dwajot.androidtesttask.service;

import com.dwajot.androidtesttask.model.InfoList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Artem on 21.07.2017.
 */

public interface RestApi {

    @GET("?from_date=20140101&to_date=20180301")
    Call<InfoList> getInfo();
}
