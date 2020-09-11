package com.example.moviesland.repository;

import com.example.moviesland.network.model.GetPopularPeopleResponse;
import com.example.moviesland.network.services.PopularPeopleService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeopleRepository {

    private PopularPeopleService peopleService;

    private PeopleRepository() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        String BASE_URL = "https://api.themoviedb.org/3/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(client).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        peopleService = retrofit.create(PopularPeopleService.class);

    }

    public static PeopleRepository getInstance() {
        return Loader.peopleRepository;
    }

    private static class Loader {
        static PeopleRepository peopleRepository = new PeopleRepository();
    }

    public Observable<GetPopularPeopleResponse> getPopularPeopleRepo() {

        return Observable.create(emitter -> {

            peopleService.getPopularPeople().observeOn(Schedulers.io()).subscribeOn(Schedulers.io())
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

}
