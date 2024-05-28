package practice.bulletinboard.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import practice.bulletinboard.entity.Board;
import practice.bulletinboard.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // select * from comment where board_id=? order baaaay id desc;
    List<Comment> findAllByBoardOrderByIdDesc(Board board);
}
