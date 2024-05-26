package practice.bulletinboard.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import practice.bulletinboard.entity.Board;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;

    public static BoardDTO toBoardDTO(Board board) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.id = board.getId();
        boardDTO.boardWriter = board.getBoardWriter();
        boardDTO.boardPass = board.getBoardPass();
        boardDTO.boardTitle = board.getBoardTitle();
        boardDTO.boardContents = board.getBoardContents();
        boardDTO.boardHits = board.getBoardHits();
        boardDTO.boardCreatedTime = board.getCreatedTime();
        boardDTO.boardUpdatedTime = board.getUpdatedTime();

        return boardDTO;
    }

    public static BoardDTO toPageBoardDTO(Board board) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.id = board.getId();
        boardDTO.boardWriter = board.getBoardWriter();
        boardDTO.boardTitle = board.getBoardTitle();
        boardDTO.boardHits = board.getBoardHits();
        boardDTO.boardCreatedTime = board.getCreatedTime();

        return boardDTO;
    }
}
