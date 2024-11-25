package com.example.movie.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.movie.entity.Member;
import com.example.movie.entity.Movie;
import com.example.movie.entity.Review;

@SpringBootTest
public class ReviewRepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void testReviewInsert() {
        IntStream.rangeClosed(1, 200).forEach(r -> {
            Long mid = (long) (Math.random() * 50) + 1;
            Long mno = (long) (Math.random() * 50) + 1;
            Member member = Member.builder().mid(mid).build();
            Movie movie = Movie.builder().mno(mno).build();

            Review review = Review.builder().text("리뷰" + r).grade((int) (Math.random() * 5) + 1)
                    .member(member)
                    .movie(movie).build();

            reviewRepository.save(review);

        });
    }
}
