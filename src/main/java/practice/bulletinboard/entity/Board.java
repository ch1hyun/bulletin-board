package practice.bulletinboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import practice.bulletinboard.dto.BoardDTO;

@Entity
@Getter @Setter
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String boardWriter;

    @Column
    private String boardPass;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int boardHits;

    public static Board toSaveEntity(BoardDTO boardDTO) {
        Board board = new Board();
        board.boardWriter = boardDTO.getBoardWriter();
        board.boardPass = boardDTO.getBoardPass();
        board.boardTitle = boardDTO.getBoardTitle();
        board.boardContents = boardDTO.getBoardContents();
        board.boardHits = 0;

        return board;
    }

    public static Board toUpdateEntity(BoardDTO boardDTO) {
        Board board = new Board();
        board.id = boardDTO.getId();
        board.boardWriter = boardDTO.getBoardWriter();
        board.boardPass = boardDTO.getBoardPass();
        board.boardTitle = boardDTO.getBoardTitle();
        board.boardContents = boardDTO.getBoardContents();
        board.boardHits = boardDTO.getBoardHits();

        return board;
    }
}
