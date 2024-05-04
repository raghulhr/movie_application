package org.alaigal.ratingsdataservice.repository;

import org.alaigal.ratingsdataservice.model.GlobalRating;
import org.alaigal.ratingsdataservice.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingsRepository extends JpaRepository<Rating, Long> {

    List<Rating> findAllByUserId(String userId);

    List<Rating> findAllByMovieId(String movieId);

    Rating findByUserIdAndMovieId(String userId, String movieId);

    @Query(value = "SELECT new org.alaigal.ratingsdataservice.model.GlobalRating(SUM(rating), COUNT(rating)) FROM Rating WHERE movieId = :movieId")
    GlobalRating getGlobalRatingByMovieId(@Param("movieId") String movieId);

//    Double sumOfRatingByMovieId(String movieId);
//    Long countOfRatingByMovieId(String movieId);

    void deleteByMovieId(String movieId);

    @Query(value = "SELECT movie_id FROM ratings_by_user GROUP BY movie_id HAVING SUM(rating)/COUNT(rating) >= :ratingCount", nativeQuery = true)
    List<String> getRatingsBasedOnRatingCountOnMovies(@Param("ratingCount") String ratingCount);
}
