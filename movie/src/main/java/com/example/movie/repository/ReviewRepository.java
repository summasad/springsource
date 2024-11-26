package com.example.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.movie.entity.Movie;
import com.example.movie.entity.Review;
import com.example.movie.repository.total.MovieImageReviewRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, MovieImageReviewRepository {

    // movie_mno 이용해서 review 제거 메소드 생성
    @Modifying
    @Query("DELETE FROM Review r WHERE r.movie = :movie")
    void deleteByMovie(Movie movie);
}
