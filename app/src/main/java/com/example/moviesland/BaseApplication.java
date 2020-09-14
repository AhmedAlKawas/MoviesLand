package com.example.moviesland;

import android.app.Application;

import com.example.moviesland.di.component.ApplicationComponent;
import com.example.moviesland.di.component.DaggerApplicationComponent;

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
