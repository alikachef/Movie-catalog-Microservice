package com.GenSpark.MovieCatalogService.controller;

import com.GenSpark.MovieCatalogService.entity.CatalogItem;
import com.GenSpark.MovieCatalogService.entity.Movie;
import com.GenSpark.MovieCatalogService.entity.Rating;
import com.GenSpark.MovieCatalogService.entity.UserRating;
import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId){
        UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class );

      return ratings.getUserRating().stream().map(rating -> {
              Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
              return new CatalogItem(movie.getName(), "movie", rating.getRating() );})
              .collect(Collectors.toList());

    }
}
