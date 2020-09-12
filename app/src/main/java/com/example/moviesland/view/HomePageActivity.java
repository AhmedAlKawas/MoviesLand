package com.example.moviesland.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.moviesland.R;
import com.example.moviesland.model.Person;
import com.example.moviesland.view.adapters.PeopleAdapter;
import com.example.moviesland.view_model.PeopleViewModel;

import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    private List<Person> personsList;
    private LottieAnimationView animationView;
    private PeopleAdapter peopleAdapter;
    private PeopleViewModel viewModel;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        initListeners();
        getIntentExtras();
        initView();

    }

    private void initListeners() {

        viewModel = new ViewModelProvider(this).get(PeopleViewModel.class);

        viewModel.returnPersons().observe(HomePageActivity.this, people -> {
            animationView.setVisibility(View.GONE);
            if (people != null){
                personsList.addAll(people);
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
