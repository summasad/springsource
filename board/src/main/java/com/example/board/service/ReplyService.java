package com.example.board.service;

import java.util.List;

import com.example.board.dto.ReplyDto;
import com.example.board.entity.Board;
import com.example.board.entity.Reply;

public interface ReplyService {
    Long register(ReplyDto dto);

    List<ReplyDto> list(Long bno);

    ReplyDto read(Long rno);

    Long modify(ReplyDto replyDto);

    void remove(Long rno);

    // entity=> dto
    public default ReplyDto entityToDto(Reply reply) {
        return ReplyDto.builder()
                .rno(reply.getRno()).replyer(reply.getReplyer()).text(reply.getText())
                .bno(reply.getBoard().getBno()).regDate(reply.getRegDate())
                .updateDate(reply.getUpdateDate())
                .build();
    }

    // dto=> entity
    public default Reply dtoToEntity(ReplyDto dto) {
        Board board = Board.builder().bno(dto.getBno()).build();
        return Reply.builder()
                .rno(dto.getRno()).replyer(dto.getReplyer())
                .text(dto.getText()).board(board)
                .build();
    }
}
