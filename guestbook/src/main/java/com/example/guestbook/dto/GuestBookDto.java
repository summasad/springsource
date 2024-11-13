package com.example.guestbook.dto;

import java.time.LocalDateTime;

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

    private String writer;
    private String title;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

}
