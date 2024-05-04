package org.alaigal.ratingsdataservice.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class RatingRequest {

    @NotBlank(message = "Movie Id should not be Empty")
    private String movieId;

    @NotBlank(message = "User Id should not be Empty")
    private String userId;

    @DecimalMin(value = "0.0", message = "Rating to a movie cannot be less than 0")
    @DecimalMax(value = "5.0", message = "Rating to a movie cannot be more than 5")
    private double rating;
}
