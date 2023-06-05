package com.kinosearch.kinosearchua.controller;

import com.kinosearch.kinosearchua.dto.MovieDTO;
import com.kinosearch.kinosearchua.dto.ReviewDTO;
import com.kinosearch.kinosearchua.entity.Movie;
import com.kinosearch.kinosearchua.entity.Review;
import com.kinosearch.kinosearchua.entity.User;
import com.kinosearch.kinosearchua.service.MovieService;
import com.kinosearch.kinosearchua.service.ReviewService;
import com.kinosearch.kinosearchua.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Getter
@Setter
@CrossOrigin
public class MovieController {

    @Autowired
    private final MovieService movieService;

    @Autowired
    private final ReviewService reviewService;

    @Autowired
    private final UserService userService;

    @PostMapping(value = "/api/movies")
    public ResponseEntity<?> createMovie(@RequestBody @Validated MovieDTO movieDTO) {
        Movie movie = Movie.builder()
                .movieReleaseDate(movieDTO.getMovieReleaseDate())
                .movieActors(String.join(", ", movieDTO.getMovieActors()))
                .movieBudget(movieDTO.getMovieBudget())
                .movieCountry(movieDTO.getMovieCountry())
                .movieDescription(movieDTO.getMovieDescription())
                .movieDirectors(String.join(", ", movieDTO.getMovieDirectors()))
                .movieDuration(movieDTO.getMovieDuration())
                .movieGenres(String.join(", ", movieDTO.getMovieGenres()))
                .movieIncome(movieDTO.getMovieIncome())
                .movieMontage(String.join(", ", movieDTO.getMovieMontage()))
                .movieName(movieDTO.getMovieName())
                .movieOperators(String.join(", ", movieDTO.getMovieOperators()))
                .moviePictureUri(movieDTO.getMoviePictureUri())
                .movieRating(null)
                .movieYear(movieDTO.getMovieYear())
                .movieScreenwriters(String.join(", ", movieDTO.getMovieScreenwriters()))
                .movieTrailerUri(movieDTO.getMovieTrailerUri())
                .movieReviews(null)
                .movieReviewsCount(0)
                .build();
        movieService.saveMovie(movie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/movies")
    public ResponseEntity<?> getMovies() {
        return new ResponseEntity<List<Movie>>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping(value = "/api/movies/{id}")
    public ResponseEntity<?> getMovie(@PathVariable Integer id) {
        Optional<Movie> movie = movieService.getMovieById(id);
        if (movie.isPresent()) {
            return new ResponseEntity<Movie>(movie.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/api/movies/{movieId}/reviews")
    public ResponseEntity<?> createReview(@PathVariable Integer movieId, @RequestBody ReviewDTO reviewDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = userService.findUserByEmail(userEmail);
        Review review = Review.builder()
                .movieId(movieId)
                .reviewBody(reviewDTO.getReviewBody())
                .movieRate(reviewDTO.getMovieRate())
                .userNickname(user.getNickname())
                .build();
        this.reviewService.save(review);
        this.movieService.updateMovieReviewsInfo(movieId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/api/movies/{movieId}/reviews")
    public ResponseEntity<?> getReviewsOfMovie(@PathVariable Integer movieId) {
        return new ResponseEntity<List<Review>>(reviewService.getReviewsByMovieId(movieId), HttpStatus.OK);
    }


}
