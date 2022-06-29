package com.GenSpark.MovieInfoService.controller;

import com.GenSpark.MovieInfoService.entity.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {

    @RequestMapping("/{movieId}")
    public Movie getMovie(@PathVariable String movieId){return  new Movie(movieId, "Transformer");};
}
