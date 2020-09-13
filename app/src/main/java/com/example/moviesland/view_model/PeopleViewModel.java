package com.example.moviesland.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviesland.model.Person;
import com.example.moviesland.network.model.GetPersonImagesResponse;
import com.example.moviesland.network.model.GetPopularPeopleResponse;
import com.example.moviesland.repository.PeopleRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PeopleViewModel extends ViewModel {

    private PeopleRepository peopleRepository;

    public PeopleViewModel(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    private MutableLiveData<List<Person>> personsLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Person>> returnPersons() {
        return personsLiveData;
    }

    private MutableLiveData<List<String>> personImagesLiveData = new MutableLiveData<>();

    public MutableLiveData<List<String>> returnPersonImages() {
        return personImagesLiveData;
    }

    public void getPopularPeople(int page) {

        peopleRepository.getPopularPeopleRepo(page).subscribe(new Observer<GetPopularPeopleResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(GetPopularPeopleResponse response) {
                if (response != null)
                    mapPersonsData(response);
                else
                    personsLiveData.postValue(null);
            }

            @Override
            public void onError(Throwable e) {
                personsLiveData.postValue(null);
            }

            @Override
            public void onComplete() {

            }
        });

    }

    private void mapPersonsData(GetPopularPeopleResponse response) {

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

        personsLiveData.postValue(personsList);

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
