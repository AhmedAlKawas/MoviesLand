package com.example.moviesland.view.image_view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.moviesland.R;
import com.example.moviesland.databinding.ActivityImageViewBinding;
import com.example.moviesland.model.Person;

public class ImageViewActivity extends AppCompatActivity {

    ActivityImageViewBinding imageViewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageViewBinding = DataBindingUtil.setContentView(ImageViewActivity.this,
                R.layout.activity_image_view);

        getIntentExtras();

    }

    private void getIntentExtras() {

        if (getIntent().getSerializableExtra(getString(R.string.image_path)) != null) {
            imageViewBinding.setImagePath((String) getIntent()
                    .getSerializableExtra(getString(R.string.image_path)));
        }

    }
}
