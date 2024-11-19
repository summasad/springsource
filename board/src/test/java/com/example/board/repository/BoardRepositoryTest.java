package com.example.board.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

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
        IntStream.rangeClosed(1, 100).forEach(i -> {
            int num = (int) (Math.random() * 30) + 1;
            Member member = Member.builder().email("abcdef" + num + "@gmail.com").build();
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

    @Transactional
    @Test
    public void testReadBoard() {
        Board board = boardRepository.findById(100L).get();
        System.out.println(board);
    }

    @Transactional
    @Test
    public void testReadReply() {
        Reply reply = replyRepository.findById(100L).get();
        System.out.println(reply);
        System.out.println(reply.getBoard());
    }

    @Transactional
    @Test
    public void testReadBoardReply() {
        Board board = boardRepository.findById(100L).get();
        System.out.println(board);
        System.out.println(board.getReplies());
    }

    @Transactional
    @Test
    public void testJoin() {
        List<Object[]> result = boardRepository.list();
        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
            // Board board = (Board)objects[0];
            // Member member = (Member) objects[1];
            Long replyCnt = (Long) objects[2];
        }
    }

    @Test
    public void testJoinList() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        // Pageable pageable = PageRequest.of(0, 10,
        // Sort.by("bno").descending().and(Sort.by("title").descending())); 정렬 기준 추가 가능
        Page<Object[]> result = boardRepository.list("tc", "content", pageable);
        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));

        }
    }

    @Test
    public void testJoinRow() {
        Object[] object = boardRepository.getBoardByBno(100L);
        System.out.println(Arrays.toString(object));
    }

    // test 로는 삭제 안됨. commit
    @Commit
    @Transactional
    @Test
    public void replyRemove() {
        replyRepository.deleteByBno(5L);
        boardRepository.deleteById(5L);
    }

    @Test
    public void replyRemove2() {
        // 부모 제거시 자식(Reply) 제거
        boardRepository.deleteById(7L);
    }

    @Test
    public void testReplyList() {
        Board board = Board.builder().bno(61L).build();
        List<Reply> list = replyRepository.findByBoardOrderByRno(board);
        list.forEach(b -> System.out.println(b));
    }

    @Test
    public void testReplyUpdate() {
        // 댓글 수정
        Reply reply = replyRepository.findById(107L).get();
        System.out.println("reply " + reply);

        // 내용 수정
        reply.setText("내용 수정");
        System.out.println(replyRepository.save(reply));

    }

}
