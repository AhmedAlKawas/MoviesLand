package com.example.moviesland.view.splash_screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;
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
    TextView errorTv;
    ImageView refreshIv;
    LottieAnimationView lottieAv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_splash_screen);

        initListeners();
        initViews();
        peopleViewModel.getPopularPeople(1);

    }

    private void initViews() {
        errorTv = findViewById(R.id.tv_error);
        refreshIv = findViewById(R.id.iv_refresh);
        lottieAv = findViewById(R.id.lottie);

        refreshIv.setOnClickListener(view -> {
            lottieAv.setVisibility(View.VISIBLE);
            errorTv.setVisibility(View.GONE);
            refreshIv.setVisibility(View.GONE);
            peopleViewModel.getPopularPeople(1);
        });

    }

    private void initListeners() {

        peopleViewModel = new ViewModelProvider(this, viewModelFactory)
                .get(PeopleViewModel.class);

        peopleViewModel.returnPersons().observe(SplashScreenActivity.this, response -> {

            if (response != null) {
//                Response success
                if (response.getCode() == 200)
                    goToHomePage(response.getPersonList());
//                SocketTimeoutException or No connection
                else if (response.getCode() == 501 || response.getCode() == 500) {
                    lottieAv.setVisibility(View.GONE);
                    errorTv.setVisibility(View.VISIBLE);
                    refreshIv.setVisibility(View.VISIBLE);
                    errorTv.setText(getString(R.string.connection_error));
//                    Server Error
                } else {
                    lottieAv.setVisibility(View.GONE);
                    errorTv.setVisibility(View.VISIBLE);
                    errorTv.setText(getString(R.string.server_error));
                }
            }

        });

    }

    private void goToHomePage(List<Person> people) {

        Intent intent = new Intent(SplashScreenActivity.this, HomePageActivity.class);
        intent.putExtra(getResources().getString(R.string.people), (Serializable) people);
        startActivity(intent);

    }
}
