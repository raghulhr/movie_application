package org.alaigal.ratingsdataservice.controller;

import org.alaigal.ratingsdataservice.dto.RatingRequest;
import org.alaigal.ratingsdataservice.exception.MovieNotFoundException;
import org.alaigal.ratingsdataservice.model.Rating;
import org.alaigal.ratingsdataservice.model.UserRating;
import org.alaigal.ratingsdataservice.service.RatingsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RatingsControllerTest {

    @Mock RatingsService ratingsService;

    @InjectMocks RatingsController ratingsController;

    @Test
    public void getRatingsByUserIdTest(){
        List<Rating> ratings = new ArrayList<>();

        Rating rating = Rating.build(1L,"M1","U1",4.5);
        Rating rating1 = Rating.build(2L,"M2","U1",3.5);

        ratings.add(rating);
        ratings.add(rating1);

        Mockito.when(ratingsService.getRatingsByUserId("U1")).thenReturn(ratings);
        UserRating userRating = ratingsController.getRatingsByUserId("U1");
        Assertions.assertEquals(2, userRating.getUserRatings().size() );
    }

    @Test
    public void getRatingByMovieIdTest() throws MovieNotFoundException {
        Rating rating = Rating.build(1L, "M1","U1",3.5);
        Mockito.when(ratingsService.getRatingByMovieId("M1")).thenReturn(List.of(rating));

        ResponseEntity<List<Rating>> ratingList = ratingsController.getRatingByMovieId("M1");

        Assertions.assertEquals(1, Objects.requireNonNull(ratingList.getBody()).size());
    }

    @Test
    public void postRating(){
        RatingRequest ratingRequest = RatingRequest.build("M1","U1", 3.5);
        ratingsController.postRating(ratingRequest);

        Mockito.verify(ratingsService, Mockito.times(1)).postRating(ratingRequest);
        Mockito.verifyNoMoreInteractions(ratingsService);
    }

}
