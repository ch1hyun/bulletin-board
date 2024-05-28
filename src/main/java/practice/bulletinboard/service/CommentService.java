package practice.bulletinboard.service;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.bulletinboard.dto.CommentDTO;
import practice.bulletinboard.entity.Board;
import practice.bulletinboard.entity.Comment;
import practice.bulletinboard.repository.BoardRepository;
import practice.bulletinboard.repository.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final EntityManager em;

    @Transactional
    public Long save(CommentDTO commentDTO) {
        /* 부모 엔티티 (Board) 조회 */
        Optional<Board> temporalBoard = boardRepository.findById(commentDTO.getBoardId());
        if (temporalBoard.isPresent()) {
            Board board = temporalBoard.get();
            board.addComment(Comment.toSaveEntity(commentDTO));
            return board.getId();
        } else {
            return null;
        }
    }

    @Transactional
    public List<CommentDTO> findAll(Long boardId) {
        Optional<Board> temporalBoard = boardRepository.findById(boardId);

        if (temporalBoard.isPresent()) {
            Board board = temporalBoard.get();
            return commentRepository
                    .findAllByBoardOrderByIdDesc(board).stream()
                    .map(c -> CommentDTO.toSaveEntity(c, boardId))
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }
}
