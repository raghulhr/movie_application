package org.alaigal.ratingsdataservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class GlobalRating {
    private Double ratingSum;
    private Long count;

    public GlobalRating(Double ratingSum, Long count){
        this.ratingSum = ratingSum;
        this.count = count;
    }
}
