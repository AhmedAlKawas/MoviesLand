package com.example.moviesland.repository;

import com.example.moviesland.network.model.GetPersonImagesResponse;
import com.example.moviesland.network.model.GetPopularPeopleResponse;
import com.example.moviesland.network.services.PopularPeopleService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PeopleRepository {

    private PopularPeopleService peopleService;

    public PeopleRepository(PopularPeopleService peopleService) {
        this.peopleService = peopleService;
    }

    public Observable<GetPopularPeopleResponse> getPopularPeopleRepo(int page) {

        return Observable.create(emitter -> {

            peopleService.getPopularPeople(page).observeOn(Schedulers.io()).subscribeOn(Schedulers.io())
                    .subscribe(new Observer<GetPopularPeopleResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(GetPopularPeopleResponse getPopularPeopleResponse) {
                            emitter.onNext(getPopularPeopleResponse);
                        }

                        @Override
                        public void onError(Throwable e) {
                            emitter.onError(e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        });

    }

    public Observable<GetPersonImagesResponse> getPersonImagesRepo(int personId) {

        return Observable.create(emitter -> {

            peopleService.getPersonImages(personId).subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io()).subscribe(new Observer<GetPersonImagesResponse>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(GetPersonImagesResponse response) {
                    emitter.onNext(response);
                }

                @Override
                public void onError(Throwable e) {
                    emitter.onError(e);
                }

                @Override
                public void onComplete() {

                }
            });

        });

    }

}
