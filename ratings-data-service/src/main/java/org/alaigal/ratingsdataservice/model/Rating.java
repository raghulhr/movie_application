package org.alaigal.ratingsdataservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Entity
@Table(name = "ratings_by_user")
@DynamicInsert
@DynamicUpdate
public class Rating {

    @Id
    @GeneratedValue
    @Column(name = "rating_id")
    private Long ratingId;
    @Column(name = "movie_id")
    private String movieId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "rating")
    private double rating;
}
