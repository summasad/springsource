package com.example.board.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.entity.Reply;

@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testInsertMember() {
        // 30명
        IntStream.rangeClosed(1, 30).forEach(i -> {
            Member member = Member.builder().email("abcdef" + i + "@gmail.com").name("이름" + i).password("password")
                    .build();
            memberRepository.save(member);

        });

    }

    @Test
    public void testInsertBoard() {
        // 100개
        int num = (int) (Math.random() * 30) + 1;
        Member member = Member.builder().email("abcdef" + num + "@gmail.com").build();
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Board board = Board.builder().title("title" + i).content("내용......" + i).writer(member).build();
            boardRepository.save(board);

        });

    }

    @Test
    public void testInsertRelpy() {
        // 100개(임의의 bno)

        IntStream.rangeClosed(1, 100).forEach(i -> {
            long bno = (long) (Math.random() * 100) + 1;
            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder().replyer("guest" + i).text("text....." + i).board(board).build();
            replyRepository.save(reply);
        });

    }

}
