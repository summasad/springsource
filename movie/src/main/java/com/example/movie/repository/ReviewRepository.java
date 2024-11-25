package com.example.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movie.entity.Review;
import com.example.movie.repository.total.MovieImageReviewRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, MovieImageReviewRepository {

}
