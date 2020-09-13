package com.example.moviesland.module;

import com.example.moviesland.network.services.PopularPeopleService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public abstract class NetworkModule {

    @Provides
    @Singleton
    static Retrofit provideRetrofit() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        String BASE_URL = "https://api.themoviedb.org/3/";
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .client(client).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

    }


    @Provides
    @Singleton
    static PopularPeopleService providePopularPeopleService(Retrofit retrofit) {

        return retrofit.create(PopularPeopleService.class);

    }

}
