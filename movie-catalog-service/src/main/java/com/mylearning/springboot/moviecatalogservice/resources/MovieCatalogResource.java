package com.mylearning.springboot.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.mylearning.springboot.moviecatalogservice.models.CatalogItem;
import com.mylearning.springboot.moviecatalogservice.models.Movie;
import com.mylearning.springboot.moviecatalogservice.models.Rating;
import com.mylearning.springboot.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		
		UserRating ratings = restTemplate.getForObject("http://rating-data-service/ratingsdata/users/" + userId, UserRating.class);
	
		// For each movie ID, call movie info service and get details
		return ratings.getUserRating().stream().map(rating -> {
				Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
				
				/*
				Movie movie = (Movie) webClientBuilder.build()
													  .get()
													  .uri("http://localhost:8082/movies/" + rating.getMovieId())
													  .retrieve()
													  .bodyToMono(Movie.class)
													  .block();
				*/
				return new CatalogItem(movie.getName(), "Desc", rating.getRating());
			})
		    .collect(Collectors.toList());
		}

}
