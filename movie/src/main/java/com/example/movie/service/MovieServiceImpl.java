package com.example.movie.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.movie.dto.MovieDto;
import com.example.movie.dto.PageRequestDto;
import com.example.movie.dto.PageResultDto;
import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;
import com.example.movie.repository.MovieImageRepository;
import com.example.movie.repository.MovieRepository;
import com.example.movie.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieImageRepository movieImageRepository;
    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public PageResultDto<MovieDto, Object[]> getList(PageRequestDto pageRequestDto) {
        Pageable pageable = pageRequestDto.getPageable(Sort.by("mno").descending());
        Page<Object[]> resut = movieImageRepository.getTotalList(null, null, pageable);
        Function<Object[], MovieDto> function = (en -> entityToDto((Movie) en[0],
                (List<MovieImage>) Arrays.asList((MovieImage) en[1]),
                (Long) en[2], (Double) en[3]));

        return new PageResultDto<>(resut, function);
    }

    @Override
    public Long register(MovieDto movieDto) {
        Map<String, Object> entityMap = dtoToEntituy(movieDto);
        Movie movie = (Movie) entityMap.get("movie");
        List<MovieImage> movieImages = (List<MovieImage>) entityMap.get("movieImages");
        movieRepository.save(movie);
        movieImages.forEach(movieImage -> movieImageRepository.save(movieImage));
        return movie.getMno();
    }

    @Override
    public Long modify(MovieDto movieDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modify'");
    }

    @Transactional
    @Override
    public void delete(Long mno) {
        Movie movie = Movie.builder().mno(mno).build();
        movieImageRepository.deleteByMovie(movie);
        reviewRepository.deleteByMovie(movie);
        movieRepository.delete(movie);
    }

    @Override
    public MovieDto get(Long mno) {
        List<Object[]> result = movieImageRepository.getMovieRow(mno);
        Movie movie = (Movie) result.get(0)[0];
        Long reviewCnt = (Long) result.get(0)[2];
        Double reviewAvg = (Double) result.get(0)[3];

        // 영화 이미지
        List<MovieImage> movieImages = new ArrayList<>();
        result.forEach(row -> {
            MovieImage movieImage = (MovieImage) row[1];
            movieImages.add(movieImage);
        });
        return entityToDto(movie, movieImages, reviewCnt, reviewAvg);
    }

}
