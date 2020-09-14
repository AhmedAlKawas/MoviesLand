package com.example.moviesland.di.component;

import com.example.moviesland.BaseApplication;
import com.example.moviesland.di.ActivityBuilder;
import com.example.moviesland.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        NetworkModule.class,
        ActivityBuilder.class,
        AndroidSupportInjectionModule.class})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(BaseApplication application);

        ApplicationComponent build();
    }

    void inject(BaseApplication application);

}
