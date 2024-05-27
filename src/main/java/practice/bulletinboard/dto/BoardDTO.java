package practice.bulletinboard.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
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

    private MultipartFile boardFile;
    private String originalFileName; // 원본 파일 이름
    private String storedFileName; // 서버 저장용 파일 이름
    private int fileAttached; // 파일 첨부 여부 (첨부 1, 미첨부 0) -> 숫자로 하는 것이 데이터베이스에서 관리하기 좋음

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
        boardDTO.fileAttached = board.getFileAttached();

        if (boardDTO.fileAttached == 1) {
            boardDTO.originalFileName = board.getBoardFiles().get(0).getOriginalFileName();
            boardDTO.storedFileName = board.getBoardFiles().get(0).getStoredFileName();
        }

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
