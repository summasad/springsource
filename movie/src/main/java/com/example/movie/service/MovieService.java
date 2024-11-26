package com.example.movie.service;

import java.util.List;
import java.util.Map;
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

    default MovieDto entityToDto(Movie movie, List<MovieImage> movieImage, Long reviewCnt, Double reviewAvg) {
        // movie=> movieDto
        // MovieImage => MovieImageDto 변경후 리스트 작업

        MovieDto movieDto = MovieDto.builder()
                .mno(movie.getMno()).title(movie.getTitle())
                // .movieImageDtos(movieImage)
                .reviewCnt(reviewCnt).reviewAvg(reviewAvg != null ? reviewAvg : 0.0d)
                .regDate(movie.getRegDate())
                .updateDate(movie.getUpdateDate())
                .build();

        List<MovieImageDto> movieImageDtos = movieImage.stream().map(mi -> {
            return MovieImageDto.builder().inum(mi.getInum()).uuid(mi.getUuid())
                    .imgName(mi.getImgName()).path(mi.getPath()).build();
        }).collect(Collectors.toList());
        movieDto.setMovieImageDtos(movieImageDtos);
        return movieDto;

    };

    default Map<String, Object> dtoToEntituy(MovieDto movieDto) {
        Movie movie = Movie.builder().mno(movieDto.getMno()).title(movieDto.getTitle())
                .build();
        return null;
    };

}
