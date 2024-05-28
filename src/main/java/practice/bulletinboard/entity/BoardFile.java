package practice.bulletinboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.File;
import java.io.IOException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Getter @Setter
public class BoardFile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_file_id")
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public static BoardFile toBoardFile(MultipartFile multipartFile) throws IOException {
        BoardFile boardFile = new BoardFile();

        boardFile.originalFileName = multipartFile.getOriginalFilename();
        boardFile.storedFileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();

        String savePath = "/Users/chyun/chyun/project/2024/bulletin-board/springboot-img/" + boardFile.storedFileName;
        multipartFile.transferTo(new File(savePath));

        return boardFile;
    }
}
