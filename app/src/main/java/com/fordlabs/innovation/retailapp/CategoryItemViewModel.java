package com.fordlabs.innovation.retailapp;

import android.databinding.BaseObservable;

public class CategoryItemViewModel extends BaseObservable {

    private String title;

    public CategoryItemViewModel(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
