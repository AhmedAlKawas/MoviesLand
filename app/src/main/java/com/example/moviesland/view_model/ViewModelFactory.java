package com.example.moviesland.view_model;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

import javax.inject.Provider;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
        this.creators = creators;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        Provider<? extends ViewModel> creator = creators.get(modelClass);

        for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry :
                creators.entrySet()) {

            if (modelClass.isAssignableFrom(entry.getKey())) {
                creator = entry.getValue();
                break;
            }

        }

        if (creator == null)
            throw new IllegalArgumentException("No class found " + modelClass);

        try {
            return (T) creator.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}