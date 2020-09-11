package com.example.moviesland.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviesland.model.Person;
import com.example.moviesland.network.model.GetPopularPeopleResponse;
import com.example.moviesland.repository.PeopleRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PeopleViewModel extends ViewModel {

    private PeopleRepository peopleRepository = PeopleRepository.getInstance();

    private MutableLiveData<List<Person>> personsLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Person>> getPersons() {
        return personsLiveData;
    }

    public void getPopularPeople() {

        peopleRepository.getPopularPeopleRepo().subscribe(new Observer<GetPopularPeopleResponse>() {
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

}
