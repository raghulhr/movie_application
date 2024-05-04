package org.alaigal.ratingsdataservice.service;

import org.alaigal.ratingsdataservice.dto.RatingRequest;
import org.alaigal.ratingsdataservice.exception.MovieNotFoundException;
import org.alaigal.ratingsdataservice.model.Rating;
import org.alaigal.ratingsdataservice.repository.RatingsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RatingsServiceTest {

    @Mock private RatingsRepository ratingsRepository;

    @InjectMocks private RatingsService ratingsService;

    @Test
    public void getRatingsByUserIdTest(){
        List<Rating> ratings = new ArrayList<>();

        Rating rating = Rating.build("M1","U1",4.5);
        Rating rating1 = Rating.build("M2","U1",3.5);

        ratings.add(rating);
        ratings.add(rating1);

        Mockito.when(ratingsRepository.findAllByUserId("U1")).thenReturn(ratings);
        List<Rating> userRatingsList = ratingsService.getRatingsByUserId("U1");
        assertEquals(2, userRatingsList.size());
    }

    @Test
    public void getRatingByMovieIdTest() throws MovieNotFoundException {
        Rating rating = Rating.build("M1","U1", 3.5);

        Mockito.when(ratingsRepository.findById("M1")).thenReturn(Optional.of(rating));

        Optional<Rating> optionalResult = ratingsService.getRatingByMovieId("M1");
        Rating result = new Rating();
        optionalResult.ifPresent(r -> result.setMovieId(r.getMovieId()));

        Assertions.assertThat(result.getMovieId()).isEqualTo("M1");
    }

    @Test
    public void postRating(){
        RatingRequest ratingRequest = RatingRequest.build("M1","U1", 3.5);
        ratingsService.postRating(ratingRequest);

        Rating rating = Rating.build(ratingRequest.getMovieId(), ratingRequest.getUserId(), ratingRequest.getRating());

        Mockito.verify(ratingsRepository, Mockito.times(1)).save(rating);
        Mockito.verifyNoMoreInteractions(ratingsRepository);
    }
}
