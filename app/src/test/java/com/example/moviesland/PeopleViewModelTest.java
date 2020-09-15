package com.example.moviesland;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.moviesland.model.PopularPersonsResponse;
import com.example.moviesland.network.model.GetPersonImagesResponse;
import com.example.moviesland.network.model.GetPopularPeopleResponse;
import com.example.moviesland.repository.PeopleRepository;
import com.example.moviesland.view_model.PeopleViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Observable;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PeopleViewModelTest {

    @Mock
    PeopleRepository peopleRepository;

    private PeopleViewModel peopleViewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() throws Exception {
        peopleViewModel = new PeopleViewModel(peopleRepository);
    }

    @Test
    public void observePersonData() {

        GetPopularPeopleResponse getPopularPeopleResponse = TestUtil.getPopularPeopleResponse();
        when(peopleRepository.getPopularPeopleRepo(1)).thenReturn(Observable
                .just(getPopularPeopleResponse));

        peopleViewModel.getPopularPeople(1);

        PopularPersonsResponse popularPersonsResponse = peopleViewModel.returnPersons().getValue();

        Assert.assertNotNull(popularPersonsResponse);
        Assert.assertEquals(200, popularPersonsResponse.getCode());

    }

    @Test
    public void GetPopularPeopleReturnInValidApi() {

        when(peopleRepository.getPopularPeopleRepo(1)).thenReturn(Observable.error(
                new Throwable("HTTP 401 ")));

        peopleViewModel.getPopularPeople(1);

        PopularPersonsResponse popularPersonsResponse = peopleViewModel.returnPersons().getValue();

        Assert.assertNotNull(popularPersonsResponse);
        Assert.assertEquals(401, popularPersonsResponse.getCode());

    }

    @Test
    public void GetPopularPeopleReturnSourceNotFound() {

        when(peopleRepository.getPopularPeopleRepo(1)).thenReturn(Observable.error(
                new Throwable("HTTP 404 ")));

        peopleViewModel.getPopularPeople(1);

        PopularPersonsResponse popularPersonsResponse = peopleViewModel.returnPersons().getValue();

        Assert.assertNotNull(popularPersonsResponse);
        Assert.assertEquals(404, popularPersonsResponse.getCode());

    }

    @Test
    public void GetPopularPeopleReturnUnKnownHost() {

        when(peopleRepository.getPopularPeopleRepo(1)).thenReturn(Observable.error(
                new Throwable("Unable to resolve host \"api.themoviedb.org\": No address " +
                        "associated with hostname")));

        peopleViewModel.getPopularPeople(1);

        PopularPersonsResponse popularPersonsResponse = peopleViewModel.returnPersons().getValue();

        Assert.assertNotNull(popularPersonsResponse);
        Assert.assertEquals(500, popularPersonsResponse.getCode());

    }

    @Test
    public void GetPopularPeopleReturnSocketTimeOutException() {

        when(peopleRepository.getPopularPeopleRepo(1)).thenReturn(Observable.error(
                new Throwable("java.net.SocketTimeoutException")));

        peopleViewModel.getPopularPeople(1);

        PopularPersonsResponse popularPersonsResponse = peopleViewModel.returnPersons().getValue();

        Assert.assertNotNull(popularPersonsResponse);
        Assert.assertEquals(501, popularPersonsResponse.getCode());

    }

    @Test
    public void observePersonImagesData() {

        GetPersonImagesResponse getPersonImagesResponse = TestUtil.getPersonImagesResponse();
        when(peopleRepository.getPersonImagesRepo(1)).thenReturn(Observable
                .just(getPersonImagesResponse));

        peopleViewModel.getPersonImages(1);

        List<String> personImages = peopleViewModel.returnPersonImages().getValue();

        Assert.assertNotNull(personImages);
        Assert.assertEquals(2, personImages.size());

    }

    @Test
    public void getPersonImagesDataFailure() {

        when(peopleRepository.getPersonImagesRepo(1)).thenReturn(Observable
                .error(new Throwable("HTTP 404 ")));

        peopleViewModel.getPersonImages(1);

        List<String> personImages = peopleViewModel.returnPersonImages().getValue();

        Assert.assertNull(personImages);

    }

}
