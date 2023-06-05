package com.kinosearch.kinosearchua.service;

import com.kinosearch.kinosearchua.entity.Review;
import com.kinosearch.kinosearchua.repository.ReviewRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Setter
@Getter
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepo reviewRepo;

    @Override
    public void save(Review review) {
        this.reviewRepo.save(review);
    }

    @Override
    public List<Review> getReviewsByMovieId(Integer movieId) {
        return reviewRepo.findAllByMovieId(movieId);
    }
}
