package com.example.moviesland.view_model;

import androidx.lifecycle.ViewModel;

import com.example.moviesland.network.model.GetPopularPeopleResponse;
import com.example.moviesland.repository.PeopleRepository;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PeopleViewModel extends ViewModel {

    private PeopleRepository peopleRepository = PeopleRepository.getInstance();

    public void getPopularPeople() {

        peopleRepository.getPopularPeopleRepo().subscribe(new Observer<GetPopularPeopleResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(GetPopularPeopleResponse getPopularPeopleResponse) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

}
