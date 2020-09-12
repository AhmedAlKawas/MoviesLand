package com.example.moviesland.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesland.R;
import com.example.moviesland.model.Person;
import com.example.moviesland.view.adapters.PeopleAdapter;

import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    List<Person> personsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        getIntentExtras();
        initView();

    }

    private void initView() {
        RecyclerView peopleRv = findViewById(R.id.rv_people_list);
        PeopleAdapter peopleAdapter = new PeopleAdapter(personsList, () -> {

        });
        peopleRv.setAdapter(peopleAdapter);
    }

    @SuppressWarnings("unchecked")
    private void getIntentExtras() {
        if (getIntent().getSerializableExtra(getResources().getString(R.string.people)) != null){
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
