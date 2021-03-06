package com.example.moviesland.network.services;

import com.example.moviesland.network.model.GetPersonImagesResponse;
import com.example.moviesland.network.model.GetPopularPeopleResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PopularPeopleService {

    @GET("person/popular?api_key=d1f3680639049343db71b7cfd1181103")
    Observable<GetPopularPeopleResponse> getPopularPeople(@Query("page") int page);

    @GET("person/{personId}/images?api_key=d1f3680639049343db71b7cfd1181103")
    Observable<GetPersonImagesResponse> getPersonImages(@Path("personId") int personId);

}
