package practice.bulletinboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.bulletinboard.dto.BoardDTO;
import practice.bulletinboard.entity.Board;
import practice.bulletinboard.repository.BoardRepository;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) {
        Board board = Board.toSaveEntity(boardDTO);
        boardRepository.save(board);
    }
}
