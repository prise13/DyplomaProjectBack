package com.kinosearch.kinosearchua.entity;

import javax.persistence.*;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer movieYear;

    private String movieName;

    private String movieCountry;

    private String movieGenres;

    private String movieScreenwriters;

    private String movieOperators;

    private String movieMontage;

    private String movieDirectors;

    private Date movieReleaseDate;

    private Integer movieBudget;

    private Integer movieIncome;

    private Float movieRating;

    @Column(name="movie_description", length = 3000)
    private String movieDescription;

    private String movieDuration;

    private String movieActors;

    @Column(name="movie_picture_uri", length = 500)
    private String moviePictureUri;

    @Column(name="movie_trailer_uri", length = 500)
    private String movieTrailerUri;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "movie_id")
    private List<Review> movieReviews;

    private Integer movieReviewsCount;

}
