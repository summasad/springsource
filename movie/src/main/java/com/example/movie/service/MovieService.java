package com.example.movie.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.movie.dto.MovieDto;
import com.example.movie.dto.MovieImageDto;
import com.example.movie.dto.PageRequestDto;
import com.example.movie.dto.PageResultDto;
import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;

public interface MovieService {
    // 영화 목록(페이지 나누기 + 검색)
    PageResultDto<MovieDto, Object[]> getList(PageRequestDto pageRequestDto);

    // 영화 등록
    Long register(MovieDto movieDto);

    // 영화 수정
    Long modify(MovieDto movieDto);

    // 영화 삭제
    void delete(Long mno);

    // 영화 상세 조회
    MovieDto get(Long mno);

    default MovieDto entityToDto(Movie movie, List<MovieImage> movieImages, Long reviewCnt, Double reviewAvg) {
        // movie=> movieDto
        // movieImages => MovieImageDto 변경후 리스트 작업

        MovieDto movieDto = MovieDto.builder()
                .mno(movie.getMno()).title(movie.getTitle())
                // .movieImageDtos(movieImages)
                .reviewCnt(reviewCnt).reviewAvg(reviewAvg != null ? reviewAvg : 0.0d)
                .regDate(movie.getRegDate())
                .updateDate(movie.getUpdateDate())
                .build();

        List<MovieImageDto> movieImageDtos = movieImages.stream().map(movieImage -> {
            return MovieImageDto.builder().inum(movieImage.getInum()).uuid(movieImage.getUuid())
                    .imgName(movieImage.getImgName()).path(movieImage.getPath()).build();
        }).collect(Collectors.toList());
        movieDto.setMovieImageDtos(movieImageDtos);
        return movieDto;

    };

    default Map<String, Object> dtoToEntity(MovieDto movieDto) {
        Map<String, Object> resultMap = new HashMap<>();
        Movie movie = Movie.builder().mno(movieDto.getMno()).title(movieDto.getTitle())
                .build();
        resultMap.put("movie", movie);

        // MovieImageDto => MovieImage 변경후 MovieImage List 형태로 리턴
        // List<MovieImageDto> movieImageDtos = movieDto.getMovieImageDtos();
        // List<MovieImage> movieImages = new ArrayList<>();
        // if (movieImageDtos != null && movieImageDtos.size() > 0) {
        // movieImageDtos.forEach(dto -> {
        // MovieImage movieImage = MovieImage.builder()
        // .uuid(dto.getUuid())
        // .imgName(dto.getImgName())
        // .path(dto.getPath())
        // .movie(movie)
        // .build();
        // movieImages.add(movieImage);
        // });
        // }
        List<MovieImageDto> movieImageDtos = movieDto.getMovieImageDtos();
        if (movieImageDtos != null && movieImageDtos.size() > 0) {
            List<MovieImage> movieImages = movieImageDtos.stream().map(dto -> {
                MovieImage movieImage = MovieImage.builder()
                        .uuid(dto.getUuid())
                        .imgName(dto.getImgName())
                        .path(dto.getPath())
                        .movie(movie)
                        .build();
                return movieImage;
            }).collect(Collectors.toList());
            resultMap.put("movieImages", movieImages);
        }
        return resultMap;
    }

}
