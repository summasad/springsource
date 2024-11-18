package com.example.board.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDto {
    private Long bno;

    @NotBlank(message = "필수 입력 요소입니다")
    private String content;
    @NotBlank(message = "필수 입력 요소입니다")
    private String title;

    // private Member writer;

    @NotBlank(message = "필수 입력 요소입니다")
    @Email(message = "이메일 형식을 확인하세요")
    private String writerEmail;

    private String writerName;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    // 댓글 개수
    private Long replyCnt;

}
