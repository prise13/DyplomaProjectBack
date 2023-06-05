package com.kinosearch.kinosearchua.service;

import com.kinosearch.kinosearchua.entity.Review;

import java.util.List;

public interface ReviewService {
    public void save(Review review);

    public List<Review> getReviewsByMovieId(Integer movieId);
}
