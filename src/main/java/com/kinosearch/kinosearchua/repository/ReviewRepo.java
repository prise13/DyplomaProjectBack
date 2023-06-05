package com.kinosearch.kinosearchua.repository;

import com.kinosearch.kinosearchua.entity.Movie;
import com.kinosearch.kinosearchua.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer> {
    @Query(value = "SELECT * FROM review r WHERE r.movie_id = ?1", nativeQuery = true)
    public List<Review> findAllByMovieId(Integer movieId);
}
