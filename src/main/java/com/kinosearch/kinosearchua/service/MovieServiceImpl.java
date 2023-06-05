package com.kinosearch.kinosearchua.service;

import com.kinosearch.kinosearchua.entity.Movie;
import com.kinosearch.kinosearchua.entity.Review;
import com.kinosearch.kinosearchua.repository.MovieRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@AllArgsConstructor
@Setter
@Getter
public class MovieServiceImpl implements MovieService {

    @Autowired
    private final MovieRepo movieRepo;

    @Override
    public void saveMovie(Movie movie) {
        movieRepo.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    @Override
    public Optional<Movie> getMovieById(Integer id) {
        return movieRepo.findById(Integer.valueOf(id));
    }

    @Override
    public void updateMovieReviewsInfo(Integer movieId) {
        Optional<Movie> movieOptional = getMovieById(movieId);
        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();
            List<Review> movieReviews = movie.getMovieReviews();
            int totalReviewRateValue = 0;
            for (Review review : movieReviews) {
                totalReviewRateValue += review.getMovieRate();
                movie.setMovieReviewsCount(movieReviews.size());
                Float movieRating = (float) totalReviewRateValue / movie.getMovieReviewsCount();
                movie.setMovieRating(movieRating);
            }
            saveMovie(movie);
        }
    }


}
