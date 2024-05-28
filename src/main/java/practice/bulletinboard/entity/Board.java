package practice.bulletinboard.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import practice.bulletinboard.dto.BoardDTO;

@Entity
@Getter @Setter
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
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

    @Column
    private int fileAttached; // 1 or 0

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardFile> boardFiles = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public static Board toSaveEntity(BoardDTO boardDTO) {
        Board board = new Board();
        board.boardWriter = boardDTO.getBoardWriter();
        board.boardPass = boardDTO.getBoardPass();
        board.boardTitle = boardDTO.getBoardTitle();
        board.boardContents = boardDTO.getBoardContents();
        board.boardHits = 0;
        board.fileAttached = 0;

        return board;
    }

    public static Board toUpdateEntity(BoardDTO boardDTO) throws IOException {
        Board board = new Board();
        board.id = boardDTO.getId();
        board.boardWriter = boardDTO.getBoardWriter();
        board.boardPass = boardDTO.getBoardPass();
        board.boardTitle = boardDTO.getBoardTitle();
        board.boardContents = boardDTO.getBoardContents();
        board.boardHits = boardDTO.getBoardHits();
        board.fileAttached = boardDTO.getFileAttached();

        if (board.fileAttached == 1) {
            for (MultipartFile boardFile : boardDTO.getBoardFiles()) {
                board.boardFiles.add(BoardFile.toBoardFile(boardFile));
            }
        }

        return board;
    }

    public static Board toSaveFileEntity(BoardDTO boardDTO) throws IOException {
        Board board = new Board();
        board.boardWriter = boardDTO.getBoardWriter();
        board.boardPass = boardDTO.getBoardPass();
        board.boardTitle = boardDTO.getBoardTitle();
        board.boardContents = boardDTO.getBoardContents();
        board.boardHits = 0;
        board.fileAttached = 1;

        for (MultipartFile boardFile : boardDTO.getBoardFiles()) {
            board.addBoardFile(BoardFile.toBoardFile(boardFile));
        }

        return board;
    }

    /* ==연관 관계 편의 메소드== */
    public void addBoardFile(BoardFile boardFile) {
        this.boardFiles.add(boardFile);
        boardFile.setBoard(this);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setBoard(this);
    }
}
