package com.example.moviesland.view.splash_screen;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.moviesland.R;
import com.example.moviesland.model.Person;
import com.example.moviesland.view.home_page.HomePageActivity;
import com.example.moviesland.view_model.PeopleViewModel;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class SplashScreenActivity extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private PeopleViewModel peopleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_splash_screen);

        initListeners();
        peopleViewModel.getPopularPeople(1);

    }

    private void initListeners() {

        peopleViewModel = new ViewModelProvider(this, viewModelFactory)
                .get(PeopleViewModel.class);

        peopleViewModel.returnPersons().observe(SplashScreenActivity.this, people -> {

            if (people != null) {
                goToHomePage(people);
            }

        });

    }

    private void goToHomePage(List<Person> people) {

        Intent intent = new Intent(SplashScreenActivity.this, HomePageActivity.class);
        intent.putExtra(getResources().getString(R.string.people), (Serializable) people);
        startActivity(intent);

    }
}
