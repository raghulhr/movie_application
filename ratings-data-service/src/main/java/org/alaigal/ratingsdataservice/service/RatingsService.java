package org.alaigal.ratingsdataservice.service;

import org.alaigal.ratingsdataservice.dto.RatingRequest;
import org.alaigal.ratingsdataservice.model.GlobalRating;
import org.alaigal.ratingsdataservice.model.Rating;
import org.alaigal.ratingsdataservice.repository.RatingsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RatingsService {

    private final RatingsRepository ratingsRepository;

    public RatingsService(RatingsRepository ratingsRepository) {
        this.ratingsRepository = ratingsRepository;
    }

    public List<Rating> getRatingByMovieId(String movieId) {
        return ratingsRepository.findAllByMovieId(movieId);
    }
    public List<Rating> getRatingsByUserId(String userId){
        return ratingsRepository.findAllByUserId(userId);
    }

    public Rating getRatingByUserIdAndMovieId(String userId, String movieId) {
        return ratingsRepository.findByUserIdAndMovieId(userId, movieId);
    }

    public GlobalRating getGlobalRatingByMovieId(String movieId){
        return ratingsRepository.getGlobalRatingByMovieId(movieId);
    }

//    public GlobalRating getGlobalRatingForAMovie(String movieId) {
//        return GlobalRating.builder().ratingSum(ratingsRepository.sumOfRatingByMovieId(movieId)).count(ratingsRepository.countOfRatingByMovieId(movieId)).build();
//    }

    public List<String> getRatingsBasedOnRatingCountOnMovies(String ratingCount) {
        return ratingsRepository.getRatingsBasedOnRatingCountOnMovies(ratingCount);
    }

    public Rating postRating(RatingRequest ratingRequest){
        Rating rating = new Rating();
        rating.setMovieId(ratingRequest.getMovieId());
        rating.setUserId(ratingRequest.getUserId());
        rating.setRating(ratingRequest.getRating());
        return ratingsRepository.save(rating);
    }

    @Transactional
    public void deleteAllRatingByMovieId(String movieId) {
        ratingsRepository.deleteByMovieId(movieId);
    }


}
