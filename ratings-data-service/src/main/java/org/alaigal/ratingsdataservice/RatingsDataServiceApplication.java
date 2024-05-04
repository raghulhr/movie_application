package org.alaigal.ratingsdataservice;

import org.alaigal.ratingsdataservice.model.Rating;
import org.alaigal.ratingsdataservice.repository.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.Arrays;

@SpringBootApplication
@EnableEurekaClient
public class RatingsDataServiceApplication {

	@Autowired RatingsRepository ratingsRepository;

	public static void main(String[] args) {
		SpringApplication.run(RatingsDataServiceApplication.class, args);
	}

//	@PostConstruct
//	public void init(){
//		for(int i=0;i<5;i++){
//			int max=4, min=0;
//			Rating rating = new Rating();
//			rating.setMovieId("M"+i);
//			rating.setUserId("U1");
//			rating.setRating(Double.parseDouble(String.format("%.1f",Math.random() * (max - min + 1) + min)));
//			ratingsRepository.save(rating);
//		}
//	}

}
