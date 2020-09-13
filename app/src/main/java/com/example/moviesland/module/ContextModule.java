package com.example.moviesland.module;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ContextModule {

    private final Context context;

    protected ContextModule(Context context) {
        this.context = context;
    }

    @Binds
    abstract Context bindContext(Application application);

}
