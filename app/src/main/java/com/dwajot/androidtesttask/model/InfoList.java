package com.dwajot.androidtesttask.model;

import java.util.List;

/**
 * Created by Artem on 21.07.2017.
 */

public class InfoList {
    private Boolean success;
    private List<Info> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Info> getData() {
        return data;
    }

    public void setData(List<Info> data) {
        this.data = data;
    }
}
