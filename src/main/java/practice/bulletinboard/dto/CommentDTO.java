package practice.bulletinboard.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import practice.bulletinboard.entity.Comment;

@Getter @Setter
@ToString
public class CommentDTO {
    private Long id;
    private String commentWriter;
    private String commentContents;
    private Long boardId;
    private LocalDateTime commentCreatedTime;

    public static CommentDTO toSaveEntity(Comment comment, Long boardId) {
        CommentDTO commentDTO = new CommentDTO();

        commentDTO.id = comment.getId();
        commentDTO.commentWriter = comment.getCommentWriter();
        commentDTO.commentContents = comment.getCommentContents();
        commentDTO.boardId = boardId;
        commentDTO.commentCreatedTime = comment.getCreatedTime();

        return commentDTO;
    }
}
