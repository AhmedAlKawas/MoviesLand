package com.example.moviesland.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviesland.R;

import java.io.Serializable;

public class Person implements Serializable {

    private Integer personId;
    private String profilePic;
    private String name;
    private Integer gender;
    private String knownForDepartment;
    private Float popularity;

    @BindingAdapter("loadImage")
    public static void loadImageByGlide(ImageView imageView, String imgUrl) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_user)
                .error(R.drawable.ic_user)
                .centerCrop();

        Glide.with(imageView.getContext()).setDefaultRequestOptions(requestOptions)
                .load(imageView.getContext().getString(R.string.image_base_url) + imgUrl)
                .into(imageView);
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    public void setKnownForDepartment(String knownForDepartment) {
        this.knownForDepartment = knownForDepartment;
    }

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }
}
