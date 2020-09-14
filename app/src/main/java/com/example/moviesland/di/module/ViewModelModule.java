package com.example.moviesland.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.moviesland.di.ViewModelKey;
import com.example.moviesland.view_model.PeopleViewModel;
import com.example.moviesland.view_model.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PeopleViewModel.class)
    abstract ViewModel bindViewModel(PeopleViewModel peopleViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindFactory(ViewModelFactory viewModelFactory);

}
