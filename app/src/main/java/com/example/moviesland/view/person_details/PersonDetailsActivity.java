package com.example.moviesland.view.person_details;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.moviesland.R;
import com.example.moviesland.databinding.ActivityPersonDetailsBinding;
import com.example.moviesland.model.Person;

public class PersonDetailsActivity extends AppCompatActivity {

    ActivityPersonDetailsBinding personDetailsBinding;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personDetailsBinding = DataBindingUtil.setContentView(PersonDetailsActivity.this,
                R.layout.activity_person_details);

        getIntentExtras();

    }

    private void getIntentExtras() {
        if (getIntent().getSerializableExtra(getString(R.string.person)) != null){
            person = (Person) getIntent().getSerializableExtra(getString(R.string.person));
            personDetailsBinding.setPerson(person);
        }
    }
}
