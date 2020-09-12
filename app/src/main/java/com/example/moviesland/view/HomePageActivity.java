package com.example.moviesland.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.moviesland.R;
import com.example.moviesland.model.Person;
import com.example.moviesland.view.adapters.PeopleAdapter;

import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    private List<Person> personsList;
    private LottieAnimationView animationView;
    private PeopleAdapter peopleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        getIntentExtras();
        initView();

    }

    private void initView() {
        RecyclerView peopleRv = findViewById(R.id.rv_people_list);
        animationView = findViewById(R.id.lottie);
        peopleAdapter = new PeopleAdapter(personsList, () -> {
            animationView.setVisibility(View.VISIBLE);
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
