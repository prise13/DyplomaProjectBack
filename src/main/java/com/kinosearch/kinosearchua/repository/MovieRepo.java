package com.kinosearch.kinosearchua.repository;

import com.kinosearch.kinosearchua.dto.MovieDTO;
import com.kinosearch.kinosearchua.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {

}
