package org.alaigal.ratingsdataservice.controller;

import jakarta.validation.Valid;
import org.alaigal.ratingsdataservice.dto.RatingRequest;
import org.alaigal.ratingsdataservice.exception.MovieNotFoundException;
import org.alaigal.ratingsdataservice.model.GlobalRating;
import org.alaigal.ratingsdataservice.model.Rating;
import org.alaigal.ratingsdataservice.model.UserRating;
import org.alaigal.ratingsdataservice.service.RatingsService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rating")
public class RatingsController {

    private final RatingsService ratingsService;

    public RatingsController(RatingsService ratingsService) {
        this.ratingsService = ratingsService;
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Rating>> getRatingByMovieId(@PathVariable("movieId") String movieId) {
        return ResponseEntity.status(HttpStatus.OK).body(ratingsService.getRatingByMovieId(movieId));
    }

    @GetMapping("/users/{userId}")
    public UserRating getRatingsByUserId(@PathVariable("userId") String userId){
        UserRating userRatings = new UserRating();
        userRatings.setUserRatings(ratingsService.getRatingsByUserId(userId));
        return userRatings;
    }

    @GetMapping("/{userId}/{movieId}")
    public Rating getRatingByUserIdAndMovieId(@PathVariable("userId") String userId, @PathVariable("movieId") String movieId){
        return ratingsService.getRatingByUserIdAndMovieId(userId,movieId);
    }

    @GetMapping("/globalrating/{movieId}")
    public GlobalRating getGlobalRatingByMovieId(@PathVariable("movieId") String movieId){
        return ratingsService.getGlobalRatingByMovieId(movieId);
    }

//    @GetMapping("/globalrating/test/{movieId}")
//    public GlobalRating getGlobalRatingForAMovie(@PathVariable("movieId") String movieId){
//        return ratingsService.getGlobalRatingForAMovie(movieId);
//    }

    @GetMapping("/{ratingCount}")
    public List<String> getRatingsBasedOnRatingCountOnMovies(@PathVariable("ratingCount") String ratingCount){
        return ratingsService.getRatingsBasedOnRatingCountOnMovies(ratingCount);
    }

    @PostMapping
    public ResponseEntity<Rating> postRating(@RequestBody @Valid RatingRequest ratingRequest){
        return ResponseEntity.ok(ratingsService.postRating(ratingRequest));
    }

    @DeleteMapping("/movie/{movieId}")
    public ResponseEntity<String> deleteAllRatingByMovieId(@PathVariable("movieId") String movieId){
        ratingsService.deleteAllRatingByMovieId(movieId);
        return ResponseEntity.ok("Movie Ratings with specific Id has been deleted");
    }

}
