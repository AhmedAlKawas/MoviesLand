package com.example.moviesland.view.person_details;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.moviesland.R;
import com.example.moviesland.databinding.ActivityPersonDetailsBinding;
import com.example.moviesland.model.Person;
import com.example.moviesland.view_model.PeopleViewModel;

public class PersonDetailsActivity extends AppCompatActivity {

    private ActivityPersonDetailsBinding personDetailsBinding;
    private Person person;
    private PeopleViewModel peopleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personDetailsBinding = DataBindingUtil.setContentView(PersonDetailsActivity.this,
                R.layout.activity_person_details);

        getIntentExtras();

        personDetailsBinding.btnBack.setOnClickListener(view -> super.onBackPressed());

    }

    private void getIntentExtras() {
        if (getIntent().getSerializableExtra(getString(R.string.person)) != null) {
            person = (Person) getIntent().getSerializableExtra(getString(R.string.person));
            personDetailsBinding.setPerson(person);
            initListeners();
            peopleViewModel.getPersonImages(person.getPersonId());
        }
    }

    private void initListeners() {

        peopleViewModel = new ViewModelProvider(PersonDetailsActivity.this)
                .get(PeopleViewModel.class);

        peopleViewModel.returnPersonImages().observe(PersonDetailsActivity.this, strings -> {
            if (strings != null){
                PersonImagesAdapter imagesAdapter = new PersonImagesAdapter(strings);
                personDetailsBinding.rvPersonImages.setAdapter(imagesAdapter);
            }
        });

    }
}
