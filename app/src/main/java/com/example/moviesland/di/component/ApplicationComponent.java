package com.example.moviesland.di.component;

import com.example.moviesland.di.module.ContextModule;
import com.example.moviesland.di.module.NetworkModule;
import com.example.moviesland.view.splash_screen.SplashScreenActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, ContextModule.class})
public abstract interface ApplicationComponent {

    void inject(SplashScreenActivity splashScreenActivity);

}
