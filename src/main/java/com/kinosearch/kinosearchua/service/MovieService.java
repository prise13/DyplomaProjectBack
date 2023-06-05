package com.kinosearch.kinosearchua.service;


import com.kinosearch.kinosearchua.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    public void saveMovie(Movie movie);
    public List<Movie> getAllMovies();
    public Optional<Movie> getMovieById(Integer id);
    public void updateMovieReviewsInfo(Integer movieId);
}
