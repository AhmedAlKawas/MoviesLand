 package com.example.moviesland.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.moviesland.R;
import com.example.moviesland.view_model.PeopleViewModel;

 public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        PeopleViewModel peopleViewModel = new ViewModelProvider(this).get(PeopleViewModel.class);

        peopleViewModel.getPopularPeople();

    }
}
