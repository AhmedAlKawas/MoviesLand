package com.example.moviesland;

import com.example.moviesland.network.model.GetPopularPeopleResponse;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    public static GetPopularPeopleResponse getPopularPeopleResponse() {

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

}
