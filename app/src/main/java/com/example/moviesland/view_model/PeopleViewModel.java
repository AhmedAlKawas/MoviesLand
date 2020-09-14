package com.example.moviesland.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviesland.model.Person;
import com.example.moviesland.model.PopularPersonsResponse;
import com.example.moviesland.network.model.GetPersonImagesResponse;
import com.example.moviesland.network.model.GetPopularPeopleResponse;
import com.example.moviesland.repository.PeopleRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PeopleViewModel extends ViewModel {

    private PeopleRepository peopleRepository;
    private PopularPersonsResponse popularPersonsResponse;

    @Inject
    PeopleViewModel(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    private MutableLiveData<PopularPersonsResponse> personsLiveData = new MutableLiveData<>();

    public MutableLiveData<PopularPersonsResponse> returnPersons() {
        return personsLiveData;
    }

    private MutableLiveData<List<String>> personImagesLiveData = new MutableLiveData<>();

    public MutableLiveData<List<String>> returnPersonImages() {
        return personImagesLiveData;
    }

    public void getPopularPeople(int page) {

        popularPersonsResponse = new PopularPersonsResponse();

        peopleRepository.getPopularPeopleRepo(page).subscribe(new Observer<GetPopularPeopleResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(GetPopularPeopleResponse response) {
                mapPersonsData(response);
            }

            @Override
            public void onError(Throwable e) {
                popularPersonsResponse.setCode(errorOccurred(e));
                popularPersonsResponse.setPersonList(null);
                personsLiveData.postValue(popularPersonsResponse);
            }

            @Override
            public void onComplete() {

            }
        });

    }

    private int errorOccurred(Throwable e) {

        if (e.getMessage().equals("HTTP 401 "))
            return 401;
        else if (e.getMessage().equals("HTTP 404 "))
            return 404;
        else if (e.getMessage().equals("Unable to resolve host \"api.themoviedb.org\": " +
                "No address associated with hostname"))
            return 500;
        else if (e.getMessage().equals("java.net.SocketTimeoutException"))
            return 501;
        else
            return 0;

    }

    private void mapPersonsData(GetPopularPeopleResponse response) {

        popularPersonsResponse.setCode(200);
        List<Person> personsList = new ArrayList<>();

        for (GetPopularPeopleResponse.Result person : response.getResults()) {

            Person personItem = new Person();
            personItem.setGender(person.getGender());
            personItem.setKnownForDepartment(person.getKnownForDepartment());
            personItem.setName(person.getName());
            personItem.setPersonId(person.getId());
            personItem.setPopularity(person.getPopularity());
            personItem.setProfilePic(person.getProfilePath());

            personsList.add(personItem);

        }

        popularPersonsResponse.setPersonList(personsList);

        personsLiveData.postValue(popularPersonsResponse);

    }

    public void getPersonImages(int personId) {

        peopleRepository.getPersonImagesRepo(personId).subscribe(new Observer<GetPersonImagesResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(GetPersonImagesResponse response) {
                if (response != null) {

                    List<String> personImages = new ArrayList<>();

                    for (GetPersonImagesResponse.Profile profile : response.getProfiles()) {

                        personImages.add(profile.getFilePath());

                    }

                    personImagesLiveData.postValue(personImages);

                } else
                    personImagesLiveData.postValue(null);
            }

            @Override
            public void onError(Throwable e) {
                personImagesLiveData.postValue(null);
            }

            @Override
            public void onComplete() {

            }
        });

    }

}
