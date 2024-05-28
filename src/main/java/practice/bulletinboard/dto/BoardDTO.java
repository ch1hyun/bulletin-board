package practice.bulletinboard.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
import practice.bulletinboard.entity.Board;
import practice.bulletinboard.entity.BoardFile;

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

    private List<MultipartFile> boardFiles = new ArrayList<>();
    private List<String> originalFileNames = new ArrayList<>(); // 원본 파일 이름
    private List<String> storedFileNames = new ArrayList<>(); // 서버 저장용 파일 이름
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
            for (BoardFile boardFile : board.getBoardFiles()) {
                boardDTO.originalFileNames.add(boardFile.getOriginalFileName());
                boardDTO.storedFileNames.add(boardFile.getStoredFileName());
            }
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
