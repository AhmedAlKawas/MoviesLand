package com.example.moviesland.di;

import com.example.moviesland.view.home_page.HomePageActivity;
import com.example.moviesland.view.image_view.ImageViewActivity;
import com.example.moviesland.view.person_details.PersonDetailsActivity;
import com.example.moviesland.view.splash_screen.SplashScreenActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract SplashScreenActivity bindSplashActivity();

    @ContributesAndroidInjector
    abstract HomePageActivity bindHomePageActivity();

    @ContributesAndroidInjector
    abstract PersonDetailsActivity bindPersonDetailsActivity();

    @ContributesAndroidInjector
    abstract ImageViewActivity bindImageViewActivity();

}
