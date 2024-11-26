package com.example.movie.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;

@SpringBootTest
public class MovieImageRepositoryTest {

    @Autowired
    private MovieImageRepository movieImageRepository;

    @Test
    public void testTotalListPage() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("mno").descending());
        Page<Object[]> result = movieImageRepository.getTotalList(null, null, pageRequest);
        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
            Movie movie = (Movie) objects[0];
            MovieImage movieImage = (MovieImage) objects[1];
            Long count = (Long) objects[2];
            Double avg = (Double) objects[3];
            System.out.println(movie);
            System.out.println(movieImage);
            System.out.println(count);
            System.out.println(avg);
        }
    }

    @Test
    public void testRow() {
        List<Object[]> object = movieImageRepository.getMovieRow(50L);
        for (Object[] objects : object) {
            Movie movie = (Movie) objects[0];
            System.out.println(Arrays.toString(objects));
            System.out.println(movie);
        }
        // [Movie(mno=50, title=Movie49), MovieImage(inum=151,
        // uuid=e22a4442-1069-45ec-9936-93f894a3b04b, imgName=test2.jpg, path=null), 2,
        // 2.0]
        // [Movie(mno=50, title=Movie49), MovieImage(inum=150,
        // uuid=4eb577d7-a209-40ea-ada0-72ee24ccbe03, imgName=test1.jpg, path=null), 2,
        // 2.0]
        // [Movie(mno=50, title=Movie49), MovieImage(inum=149,
        // uuid=40ac294c-90ac-4870-aee7-08bf12a03a96, imgName=test0.jpg, path=null), 2,
        // 2.0]
    }

}
