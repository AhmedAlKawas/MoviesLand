package com.example.moviesland.network;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public interface RxObservableSchedulers {

    RxObservableSchedulers DEFAULT = new RxObservableSchedulers() {
        @Override
        public <T> ObservableTransformer<T, T> applySchedulers() {
            return observable -> observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io());
        }
    };

    RxObservableSchedulers TEST_SCHEDULER = new RxObservableSchedulers() {
        @Override
        public <T> ObservableTransformer<T, T> applySchedulers() {
            return observable -> observable
                    .subscribeOn(Schedulers.trampoline())
                    .observeOn(Schedulers.trampoline());
        }
    };

    <T> ObservableTransformer<T, T> applySchedulers();

}
