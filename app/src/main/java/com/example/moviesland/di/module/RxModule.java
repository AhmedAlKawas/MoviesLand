package com.example.moviesland.di.module;

import com.example.moviesland.network.RxObservableSchedulers;

import dagger.Module;
import dagger.Provides;

@Module
public class RxModule {

    @Provides
    public RxObservableSchedulers providesScheduler() {
        return RxObservableSchedulers.DEFAULT;
    }

}
