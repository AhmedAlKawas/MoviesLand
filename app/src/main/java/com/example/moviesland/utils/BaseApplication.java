package com.example.moviesland.utils;

import android.app.Application;

import com.example.moviesland.component.ApplicationComponent;
import com.example.moviesland.component.DaggerApplicationComponent;

public class BaseApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.create();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
