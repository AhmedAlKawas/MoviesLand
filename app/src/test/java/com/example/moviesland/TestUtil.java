package com.example.moviesland;

import com.example.moviesland.network.model.GetPersonImagesResponse;
import com.example.moviesland.network.model.GetPopularPeopleResponse;

import java.util.ArrayList;
import java.util.List;

class TestUtil {

    static GetPopularPeopleResponse getPopularPeopleResponse() {

        GetPopularPeopleResponse response = new GetPopularPeopleResponse();
        response.setTotalPages(500);
        response.setPage(1);
        response.setTotalResults(10000);

        List<GetPopularPeopleResponse.Result> results = new ArrayList<>();

        GetPopularPeopleResponse.Result result = new GetPopularPeopleResponse.Result();
        result.setName("Ahmed");
        result.setAdult(true);
        result.setId(1);
        result.setGender(2);
        result.setKnownForDepartment("acting");
        result.setPopularity((float) 99.999);
        result.setProfilePath("imagePath");
        result.setKnownFor(new ArrayList<>());
        results.add(result);

        GetPopularPeopleResponse.Result result1 = new GetPopularPeopleResponse.Result();
        result1.setName("Mohammed");
        result1.setAdult(true);
        result1.setId(1);
        result1.setGender(2);
        result1.setKnownForDepartment("acting");
        result1.setPopularity((float) 90.999);
        result1.setProfilePath("imagePath");
        result1.setKnownFor(new ArrayList<>());
        results.add(result1);

        response.setResults(results);

        return response;

    }

    static GetPersonImagesResponse getPersonImagesResponse() {

        GetPersonImagesResponse response = new GetPersonImagesResponse();
        response.setId(1);

        List<GetPersonImagesResponse.Profile> profiles = new ArrayList<>();
        GetPersonImagesResponse.Profile profile = new GetPersonImagesResponse.Profile();
        profile.setWidth(200);
        profile.setAspectRatio((float) 0.5);
        profile.setFilePath("path");
        profile.setHeight(500);
        profile.setIso6391(new Object());
        profile.setVoteAverage((float) 99);
        profile.setVoteCount(555);

        profiles.add(profile);

        GetPersonImagesResponse.Profile profile1 = new GetPersonImagesResponse.Profile();
        profile1.setWidth(200);
        profile1.setAspectRatio((float) 0.5);
        profile1.setFilePath("path");
        profile1.setHeight(500);
        profile1.setIso6391(new Object());
        profile1.setVoteAverage((float) 99);
        profile1.setVoteCount(555);

        profiles.add(profile1);

        response.setProfiles(profiles);

        return response;

    }

}
