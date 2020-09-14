package com.example.moviesland.view.home_page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.moviesland.R;
import com.example.moviesland.di.BaseActivity;
import com.example.moviesland.model.Person;
import com.example.moviesland.view_model.PeopleViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class HomePageActivity extends BaseActivity {

    private List<Person> personsList;
    private LottieAnimationView animationView;
    private PeopleAdapter peopleAdapter;
    private PeopleViewModel viewModel;
    private int page = 1;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        initListeners();
        getIntentExtras();
        initView();

    }

    private void initListeners() {

        viewModel = new ViewModelProvider(this, viewModelFactory).get(PeopleViewModel.class);

        viewModel.returnPersons().observe(HomePageActivity.this, response -> {
            animationView.setVisibility(View.GONE);
            if (response != null) {
                personsList.addAll(response.getPersonList());
                peopleAdapter.notifyDataSetChanged();
            }
        });

    }

    private void initView() {
        RecyclerView peopleRv = findViewById(R.id.rv_people_list);
        animationView = findViewById(R.id.lottie);
        peopleAdapter = new PeopleAdapter(personsList, () -> {
            animationView.setVisibility(View.VISIBLE);
            viewModel.getPopularPeople(++page);
        });
        peopleRv.setAdapter(peopleAdapter);
    }

    @SuppressWarnings("unchecked")
    private void getIntentExtras() {
        if (getIntent().getSerializableExtra(getResources().getString(R.string.people)) != null) {
            personsList = (List<Person>) getIntent().getSerializableExtra(getResources()
                    .getString(R.string.people));
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
