package com.example.guestbook.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class GuestBookDto {

    private Long gno;

    @NotBlank(message = "작성자는 필수 입력 요소입니다.")
    private String writer;

    @NotEmpty(message = "타이틀은 필수 입력 요소입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력 요소입니다.")
    private String content;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

}
