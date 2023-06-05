package com.kinosearch.kinosearchua.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class MovieDTO {

    private Integer movieYear;

    private String movieName;

    private String movieCountry;

    private List<String> movieGenres;

    private List<String> movieScreenwriters;

    private List<String> movieOperators;

    private List<String> movieMontage;

    private List<String> movieDirectors;

    private Date movieReleaseDate;

    private Integer movieBudget;

    private Integer movieIncome;

    private String movieDescription;

    private String movieDuration;

    private List<String> movieActors;

    private String moviePictureUri;

    private String movieTrailerUri;

}
