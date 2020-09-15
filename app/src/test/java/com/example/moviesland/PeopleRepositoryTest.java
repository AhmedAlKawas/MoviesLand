package com.example.moviesland;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.moviesland.network.RxObservableSchedulers;
import com.example.moviesland.network.model.GetPersonImagesResponse;
import com.example.moviesland.network.model.GetPopularPeopleResponse;
import com.example.moviesland.network.services.PopularPeopleService;
import com.example.moviesland.repository.PeopleRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PeopleRepositoryTest {


    private PeopleRepository peopleRepository;
    @Mock
    PopularPeopleService service;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() throws Exception {
        peopleRepository = new PeopleRepository(service, RxObservableSchedulers.TEST_SCHEDULER);
    }

    @Test
    public void GetPopularPeopleSuccess() {

        Observable<GetPopularPeopleResponse> response = peopleRepository
                .getPopularPeopleRepo(1);

        Assert.assertNotNull(response);

    }

    @Test
    public void GetPersonImagesSuccess() {

        Observable<GetPersonImagesResponse> response = peopleRepository
                .getPersonImagesRepo(1);

        Assert.assertNotNull(response);

    }

}
