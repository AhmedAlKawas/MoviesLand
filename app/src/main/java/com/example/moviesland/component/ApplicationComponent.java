package com.example.moviesland.component;

import com.example.moviesland.module.ContextModule;
import com.example.moviesland.module.NetworkModule;
import com.example.moviesland.view.splash_screen.SplashScreenActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, ContextModule.class})
public abstract interface ApplicationComponent {

    void inject(SplashScreenActivity splashScreenActivity);

}
